/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Win10
 */
@Repository
public class WeekScheduleDAOImpl implements WeekScheduleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WeekScheduleDAOImpl() {
    }

    public WeekScheduleDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public boolean isWeekScheduleExists(Date startDate, Date endDate) {
        String sql = "SELECT COUNT(*) FROM WeekSchedule WHERE "
                + "(WeekStartDate <= ? AND WeekEndDate >= ?)";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{endDate, startDate}, Integer.class);
        return count > 0;
    }

    @Override
    public void generateSalariesForWeek(int weekScheduleID) {
        // 1. Get all WeekDetails for the specified WeekScheduleID
        String weekDetailsSql = "SELECT wd.EmployeeID, wd.ShiftID, wd.OvertimeHours "
                + "FROM WeekDetails wd WHERE wd.WeekScheduleID = ?";

        List<WeekDetails> weekDetailsList = jdbcTemplate.query(weekDetailsSql, new Object[]{weekScheduleID}, (rs, rowNum) -> {
            WeekDetails details = new WeekDetails();
            details.setEmployeeID(rs.getInt("EmployeeID"));
            details.setShiftID(rs.getInt("ShiftID"));
            details.setOvertimeHours(rs.getBigDecimal("OvertimeHours"));
            return details;
        });

        // 2. Get hourly rate for each employee
        String hourlyRateSql = "SELECT AccountID, HourlyRate FROM Accounts WHERE AccountID = ?";
        Map<Integer, BigDecimal> hourlyRateMap = new HashMap<>();

        for (WeekDetails details : weekDetailsList) {
            BigDecimal hourlyRate = jdbcTemplate.queryForObject(hourlyRateSql, new Object[]{details.getEmployeeID()}, BigDecimal.class);
            hourlyRateMap.put(details.getEmployeeID(), hourlyRate);
        }

        // 3. Insert salary records
        String insertSalarySql = "INSERT INTO Salary (AccountID, WeekScheduleID, TotalHours, HourlyRate, TotalSalary, SalaryPaymentDate) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        for (WeekDetails details : weekDetailsList) {
            BigDecimal hourlyRate = hourlyRateMap.get(details.getEmployeeID());

            // Calculate total hours worked (assuming each shift is 6 hours and each employee works one shift per day)
            int totalShifts = 7; // One shift per day for a week
            int totalHours = totalShifts * 6;

            // Calculate total salary
            BigDecimal totalSalary = hourlyRate.multiply(BigDecimal.valueOf(totalHours))
                    .add(details.getOvertimeHours().multiply(hourlyRate));

            // Insert the salary record
            jdbcTemplate.update(insertSalarySql, details.getEmployeeID(), weekScheduleID,
                    BigDecimal.valueOf(totalHours), hourlyRate, totalSalary, new Date(System.currentTimeMillis()));
        }
    }

    @Override
    public List<WeekSchedule> getAllWeekSchedules() {
        String sql = "SELECT * FROM WeekSchedule";
        return jdbcTemplate.query(sql, new RowMapper<WeekSchedule>() {
            @Override
            public WeekSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                WeekSchedule weekSchedule = new WeekSchedule();
                weekSchedule.setWeekScheduleID(rs.getInt("WeekScheduleID"));
                weekSchedule.setWeekStartDate(rs.getDate("WeekStartDate"));
                weekSchedule.setWeekEndDate(rs.getDate("WeekEndDate"));
                return weekSchedule;
            }
        });
    }

    //WeekDetails collection view
    @Override
    public LinkedHashMap<String, Map<String, List<WeekDetails>>> getWeekDetailsGroupedByDay(int weekScheduleID) {
        String sql = "SELECT wd.weekDetailID, wd.weekScheduleID, wd.dayID, d.dayName, wd.shiftID, s.shiftName, s.StartTime, s.EndTime, wd.overtimeHours, wd.status, wd.isUpdated, wd.updateDate, wd.notes, a.FullName AS employeeName, a.Role AS employeeRole "
                + "FROM WeekDetails wd "
                + "JOIN DayOfWeek d ON wd.dayID = d.dayID "
                + "JOIN Shift s ON wd.shiftID = s.shiftID "
                + "JOIN Accounts a ON wd.employeeID = a.AccountID "
                + "WHERE wd.weekScheduleID = ? "
                + "ORDER BY d.dayID, s.StartTime"; // Order by dayID to get correct order

        List<WeekDetails> weekDetailsList = jdbcTemplate.query(sql, new Object[]{weekScheduleID}, new RowMapper<WeekDetails>() {
            @Override
            public WeekDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                WeekDetails weekDetails = new WeekDetails();
                weekDetails.setWeekDetailID(rs.getInt("weekDetailID"));
                weekDetails.setWeekScheduleID(rs.getInt("weekScheduleID"));
                weekDetails.setDayID(rs.getInt("dayID"));
                weekDetails.setDayName(rs.getString("dayName"));
                weekDetails.setShiftID(rs.getInt("shiftID"));
                weekDetails.setShiftName(rs.getString("shiftName"));
                weekDetails.setShiftStartTime(rs.getTime("StartTime"));
                weekDetails.setShiftEndTime(rs.getTime("EndTime"));
                weekDetails.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
                weekDetails.setStatus(rs.getString("status"));
                weekDetails.setIsUpdated(rs.getBoolean("isUpdated"));
                weekDetails.setUpdateDate(rs.getDate("updateDate"));
                weekDetails.setNotes(rs.getString("notes"));

                // Convert numerical role to descriptive role
                String roleNumber = rs.getString("employeeRole");
                String roleDescription;
                switch (roleNumber) {
                    case "1":
                        roleDescription = "Admin";
                        break;
                    case "2":
                        roleDescription = "Employee";
                        break;
                    case "3":
                        roleDescription = "Shipper";
                        break;
                    default:
                        roleDescription = "Unknown";
                        break;
                }
                weekDetails.setRole(roleDescription);

                weekDetails.setEmployeeName(rs.getString("employeeName"));
                return weekDetails;
            }
        });

        // Create a LinkedHashMap to maintain the order of days of the week
        LinkedHashMap<String, Map<String, List<WeekDetails>>> weekDetailsGroupedByDayAndShift = new LinkedHashMap<>();
        List<String> daysOfWeek = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

        // Initialize map with empty maps for each day
        for (String day : daysOfWeek) {
            weekDetailsGroupedByDayAndShift.put(day, new LinkedHashMap<>());
        }

        // Group week details by dayName and shiftName
        for (WeekDetails details : weekDetailsList) {
            String dayName = details.getDayName();
            String shiftName = details.getShiftName();

            // Ensure the day exists in the map
            if (weekDetailsGroupedByDayAndShift.containsKey(dayName)) {
                Map<String, List<WeekDetails>> shifts = weekDetailsGroupedByDayAndShift.get(dayName);

                // Ensure the shift exists for the day
                if (!shifts.containsKey(shiftName)) {
                    shifts.put(shiftName, new ArrayList<>());
                }

                // Add week details to the corresponding shift
                shifts.get(shiftName).add(details);
            }
        }

        return weekDetailsGroupedByDayAndShift;
    }

    @Override
    public WeekSchedule getWeekScheduleById(int weekScheduleID) {
        String sql = "SELECT WeekStartDate, WeekEndDate FROM WeekSchedule WHERE WeekScheduleID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{weekScheduleID}, new RowMapper<WeekSchedule>() {
            @Override
            public WeekSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                WeekSchedule weekSchedule = new WeekSchedule();
                weekSchedule.setWeekStartDate(rs.getDate("weekStartDate"));
                weekSchedule.setWeekEndDate(rs.getDate("weekEndDate"));
                return weekSchedule;
            }
        });
    }

    //create, delete weekschedule
    @Override
    public void createWeekSchedule(WeekSchedule weekSchedule) {
        String sql = "INSERT INTO WeekSchedule (weekStartDate, weekEndDate) VALUES (?, ?)";
        jdbcTemplate.update(sql, weekSchedule.getWeekStartDate(), weekSchedule.getWeekEndDate());
    }

    @Override
    public boolean hasDetailsForWeek(int weekScheduleID) {
        String sql = "SELECT COUNT(*) FROM WeekDetails WHERE WeekScheduleID = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{weekScheduleID}, Integer.class);
        return count > 0;
    }

    @Override
    public void deleteWeekSchedule(int WeekScheduleID) {
        String sql = "DELETE FROM WeekSchedule WHERE WeekScheduleID = ?";
        jdbcTemplate.update(sql, WeekScheduleID);
    }

    //add edit delete work day/shift
    @Override
    public List<DayOfWeek> getDaysOfWeek() {
        String sql = "SELECT * FROM DayOfWeek ORDER BY dayID";
        return jdbcTemplate.query(sql, new RowMapper<DayOfWeek>() {
            @Override
            public DayOfWeek mapRow(ResultSet rs, int rowNum) throws SQLException {
                DayOfWeek day = new DayOfWeek();
                day.setDayID(rs.getInt("dayID"));
                day.setDayName(rs.getString("dayName"));
                return day;
            }
        });
    }

    @Override
    public List<Shift> getAllShifts() {
        String sql = "SELECT * FROM Shift";
        return jdbcTemplate.query(sql, new RowMapper<Shift>() {
            @Override
            public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
                Shift shift = new Shift();
                shift.setShiftID(rs.getInt("ShiftID"));
                shift.setShiftName(rs.getString("ShiftName"));
                shift.setStartTime(rs.getTime("StartTime"));
                shift.setEndTime(rs.getTime("EndTime"));
                shift.setDescription(rs.getString("Description"));
                return shift;
            }
        });
    }

    @Override
    public List<Accounts> getAllEmployees() {
        String sql = "SELECT * FROM Accounts WHERE Role IN (2, 3)";
        return jdbcTemplate.query(sql, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                Accounts account = new Accounts();
                account.setAccountID(rs.getInt("AccountID"));
                account.setFullName(rs.getString("FullName"));
                account.setRole(rs.getInt("Role"));
                return account;
            }
        });
    }

    @Override
    public boolean isWeekScheduleExists(int weekScheduleID) {
        String sql = "SELECT COUNT(*) FROM WeekSchedule WHERE WeekScheduleID = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{weekScheduleID}, Integer.class);
        return count > 0;
    }

    @Override
    public void insertWeekDetails(int weekScheduleID, int dayID, int shiftID, int employeeID, BigDecimal overtimeHours, String status, String notes) {
        String sql = "INSERT INTO WeekDetails (WeekScheduleID, DayID, ShiftID, EmployeeID, OvertimeHours, Status, Notes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, weekScheduleID, dayID, shiftID, employeeID, overtimeHours, status, notes);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
