package com.models;

import java.math.BigDecimal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;

public class SalaryDAOImpl implements SalaryDAO {

    private JdbcTemplate jdbcTemplate;

    public SalaryDAOImpl() {
    }

    public SalaryDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean exists(Salary salary) {
        String sql = "SELECT COUNT(*) FROM Salary WHERE accountID = ? AND weekScheduleID = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{salary.getAccountID(), salary.getWeekScheduleID()}, Integer.class);
        return count > 0;
    }

    @Override
    public void updateSalaryAfterShiftChange(int accountID, int weekScheduleID, BigDecimal newTotalSalary) {
        String sql = "UPDATE Salary SET totalSalary = ? WHERE accountID = ? AND weekScheduleID = ?";
        jdbcTemplate.update(sql, newTotalSalary, accountID, weekScheduleID);
    }

    @Override
    public BigDecimal calculateNewSalary(int accountID, int shiftID) {
        // Lấy mức lương theo giờ của nhân viên từ bảng Accounts
        String sql = "SELECT hourlyRate FROM Accounts WHERE accountID = ?";
        BigDecimal hourlyRate = jdbcTemplate.queryForObject(sql, BigDecimal.class, accountID);

        // Kiểm tra nếu mức lương theo giờ không tồn tại
        if (hourlyRate == null) {
            System.err.println("Hourly rate not found for accountID: " + accountID);
            return BigDecimal.ZERO;
        }

        int hoursWorked = 0;
        switch (shiftID) {
            case 1:
                hoursWorked = 5;
                break;
            case 2:
                hoursWorked = 4;
                break;
            case 3: // Ca tối
                hoursWorked = 5;
                break;
            default:
                System.err.println("Invalid shiftID: " + shiftID);
                return BigDecimal.ZERO;
        }

        // Tính toán lương cho ca làm việc
        BigDecimal salaryForShift = hourlyRate.multiply(new BigDecimal(hoursWorked));
        System.out.println("Calculated salary for accountID " + accountID + " and shiftID " + shiftID + " is: " + salaryForShift);

        return salaryForShift;
    }

    @Override
    public BigDecimal calculateTotalSalaryByWeek(int weekScheduleID) {
        String sql = "SELECT SUM(totalSalary) AS totalSalary FROM Salary WHERE weekScheduleID = ?";
        BigDecimal totalSalary = jdbcTemplate.queryForObject(sql, new Object[]{weekScheduleID}, BigDecimal.class);

        // Return value check
        return totalSalary != null ? totalSalary : BigDecimal.ZERO; // Return 0 if no data
    }

    @Override
    public List<EmployeeSalaryDTO> calculateSalariesByWeek(int weekScheduleID) {
        String sql = "SELECT "
                + "a.accountID AS accountID, "
                + "a.fullName AS employeeName, "
                + "COALESCE(SUM(s.totalHours), 0) AS totalHours, "
                + "COALESCE(a.hourlyRate, 0) AS hourlyRate, "
                + "COALESCE(SUM(s.totalSalary), 0) AS totalSalary "
                + "FROM Accounts a "
                + "LEFT JOIN Salary s ON a.accountID = s.accountID AND s.weekScheduleID = ? "
                + "WHERE a.role IN (2, 3) "
                + "GROUP BY a.accountID, a.fullName, a.hourlyRate "
                + "ORDER BY a.fullName";

        return jdbcTemplate.query(sql, new Object[]{weekScheduleID}, (rs, rowNum) -> {
            return new EmployeeSalaryDTO(
                    rs.getInt("accountID"),
                    rs.getString("employeeName"),
                    rs.getBigDecimal("hourlyRate"),
                    rs.getInt("totalHours"),
                    rs.getBigDecimal("totalSalary"),
                    rs.getInt("overtimeHours"),
                    rs.getBigDecimal("overtimeSalary")
            );
        });
    }

    @Override
    public List<EmployeeSalaryDTO> findSalariesByWeek(int weekScheduleID) {
        String sql = "SELECT a.accountID, a.fullName, a.hourlyRate, "
                + "COALESCE(SUM(s.totalHours), 0) AS totalHours, "
                + "COALESCE(SUM(s.totalSalary), 0) AS totalSalary "
                + "FROM Accounts a "
                + "LEFT JOIN Salary s ON a.accountID = s.accountID AND s.weekScheduleID = ? "
                + "WHERE a.role IN (2, 3) "
                + "GROUP BY a.accountID, a.fullName, a.hourlyRate";

        return jdbcTemplate.query(sql, new Object[]{weekScheduleID}, (rs, rowNum) -> {
            return new EmployeeSalaryDTO(
                    rs.getInt("accountID"),
                    rs.getString("fullName"),
                    rs.getBigDecimal("hourlyRate"),
                    rs.getInt("totalHours"),
                    rs.getBigDecimal("totalSalary"),
                    rs.getInt("overtimeHours"),
                    rs.getBigDecimal("overtimeSalary")
            );
        });
    }

    public List<WeekSalaryDTO> calculateWeeklySalaries() {
        // Lấy thông tin tuần lương từ cơ sở dữ liệu
        String sql = "SELECT  "
                + "    ws.weekScheduleID,  "
                + "    ws.weekStartDate,  "
                + "    ws.weekEndDate,  "
                + "    COALESCE(SUM(CASE  "
                + "        WHEN wd.shiftID = 1 THEN 5 "
                + "        WHEN wd.shiftID = 2 THEN 4 "
                + "        WHEN wd.shiftID = 3 THEN 5 "
                + "        ELSE 0 END), 0) AS totalHoursWorked,  "
                + "    COALESCE(SUM(CASE  "
                + "        WHEN wd.shiftID = 1 THEN 5 * a.HourlyRate " // Lương ca sáng
                + "        WHEN wd.shiftID = 2 THEN 4 * a.HourlyRate " // Lương ca trưa
                + "        WHEN wd.shiftID = 3 THEN 5 * a.HourlyRate " // Lương ca chiều
                + "        ELSE 0 END), 0) AS regularSalary,  "
                + "    COALESCE(SUM(wd.overtimeHours), 0) AS totalOvertimeHours,  "
                + "    COALESCE(SUM(wd.overtimeHours * a.HourlyRate * 1), 0) AS overtimeSalary,  "
                + "    COALESCE(SUM(CASE  "
                + "        WHEN wd.shiftID = 1 THEN 5 * a.HourlyRate " // Tính lương cho ca sáng
                + "        WHEN wd.shiftID = 2 THEN 4 * a.HourlyRate " // Tính lương cho ca trưa
                + "        WHEN wd.shiftID = 3 THEN 5 * a.HourlyRate " // Tính lương cho ca chiều
                + "        ELSE 0 END), 0) + "
                + "    COALESCE(SUM(wd.overtimeHours * a.HourlyRate * 1), 0) AS totalSalary, "
                + "    COALESCE(MAX(s.SalaryPaymentDate), NULL) AS salaryPaymentDate   "
                + "FROM WeekSchedule ws  "
                + "LEFT JOIN WeekDetails wd ON ws.weekScheduleID = wd.weekScheduleID  "
                + "LEFT JOIN Accounts a ON wd.EmployeeID = a.AccountID "
                + "LEFT JOIN Salary s ON ws.weekScheduleID = s.WeekScheduleID AND wd.shiftID = s.shiftID "
                + "GROUP BY ws.weekScheduleID, ws.weekStartDate, ws.weekEndDate, s.SalaryPaymentDate "
                + "ORDER BY ws.weekStartDate;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new WeekSalaryDTO(
                    rs.getInt("weekScheduleID"),
                    rs.getDate("weekStartDate"),
                    rs.getDate("weekEndDate"),
                    rs.getBigDecimal("totalSalary"),
                    rs.getInt("totalHoursWorked") + rs.getInt("totalOvertimeHours"), // Cộng tổng giờ làm việc và giờ làm thêm ở đây
                    rs.getBigDecimal("totalOvertimeHours"),
                    rs.getBigDecimal("overtimeSalary"),
                    rs.getDate("salaryPaymentDate")
            );
        });
    }

    @Override
    public List<EmployeeSalaryDTO> findSalariesWithHoursByWeek(int weekScheduleID) {
        String sql = "SELECT " +
                "    a.AccountID AS employeeID, " +  // ID của nhân viên
                "    a.FullName, " +                  // Tên đầy đủ của nhân viên
                "    a.HourlyRate, " +                // Lương theo giờ
                "    COALESCE(SUM(CASE " +  
                "        WHEN wd.shiftID = 1 THEN 5  " +  // Ca sáng: 5 giờ
                "        WHEN wd.shiftID = 2 THEN 4  " +  // Ca trưa: 4 giờ
                "        WHEN wd.shiftID = 3 THEN 5  " +  // Ca chiều: 5 giờ
                "        ELSE 0 " + 
                "    END), 0) + COALESCE(SUM(wd.overtimeHours), 0) AS totalHours, " +  // Tính tổng giờ làm (cả giờ làm thêm)
                "    COALESCE(SUM(CASE " +  
                "        WHEN wd.shiftID = 1 THEN 5 * a.HourlyRate " +
                "        WHEN wd.shiftID = 2 THEN 4 * a.HourlyRate " +
                "        WHEN wd.shiftID = 3 THEN 5 * a.HourlyRate " +
                "        ELSE 0 " + 
                "    END), 0) + COALESCE(SUM(wd.overtimeHours * a.HourlyRate), 0) AS totalSalary " +  // Tính tổng lương (cả lương giờ làm thêm)
                "FROM WeekDetails wd " +
                "LEFT JOIN Accounts a ON wd.EmployeeID = a.AccountID " +
                "WHERE wd.weekScheduleID = ? " +  // ID của tuần
                "GROUP BY a.AccountID, a.FullName, a.HourlyRate " +
                "ORDER BY a.FullName;";

        return jdbcTemplate.query(sql, new Object[]{weekScheduleID}, new EmployeeSalaryRowMapper());
    }

    private static class EmployeeSalaryRowMapper implements RowMapper<EmployeeSalaryDTO> {
        @Override
        public EmployeeSalaryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeSalaryDTO employeeSalary = new EmployeeSalaryDTO();
            employeeSalary.setAccountID(rs.getInt("employeeID"));
            employeeSalary.setFullName(rs.getString("FullName"));
            employeeSalary.setHourlyRate(rs.getBigDecimal("HourlyRate"));
            employeeSalary.setTotalHours(rs.getInt("totalHours")); // Tổng giờ làm
            employeeSalary.setTotalSalary(rs.getBigDecimal("totalSalary")); // Tổng lương
            return employeeSalary;
        }
    }

    @Override
    public List<EmployeeSalaryDTO> findTotalSalary(int weekScheduleID) {
        String sql = "SELECT "
                + "wd.EmployeeID AS accountID, "
                + "CONCAT(a.FirstName, ' ', a.LastName) AS fullName, "
                + "a.HourlyRate, "
                + "SUM(CASE WHEN wd.shiftID = 1 THEN 5 "
                + "WHEN wd.shiftID = 2 THEN 4 "
                + "WHEN wd.shiftID = 3 THEN 5 "
                + "ELSE 0 END) AS totalHours, "
                + "SUM((CASE WHEN wd.shiftID = 1 THEN 5 "
                + "WHEN wd.shiftID = 2 THEN 4 "
                + "WHEN wd.shiftID = 3 THEN 5 ELSE 0 END) * a.HourlyRate) AS totalSalary, "
                // Tính tổng giờ làm thêm
                + "GREATEST(0, SUM(CASE WHEN wd.shiftID = 1 THEN 5 "
                + "WHEN wd.shiftID = 2 THEN 4 "
                + "WHEN wd.shiftID = 3 THEN 5 ELSE 0 END) - 40) AS overtimeHours, "
                // Tính tổng lương cho giờ làm thêm
                + "GREATEST(0, SUM(CASE WHEN wd.shiftID = 1 THEN 5 "
                + "WHEN wd.shiftID = 2 THEN 4 "
                + "WHEN wd.shiftID = 3 THEN 5 ELSE 0 END) - 40) * a.HourlyRate * 1 AS overtimeSalary "
                + "FROM Accounts a "
                + "JOIN WeekDetails wd ON a.AccountID = wd.EmployeeID "
                + "WHERE wd.weekScheduleID = ? "
                + "GROUP BY wd.EmployeeID, a.FirstName, a.LastName, a.HourlyRate";

        return jdbcTemplate.query(sql, new Object[]{weekScheduleID}, (rs, rowNum) -> {
            int regularHours = rs.getInt("totalHours");
            int overtimeHours = rs.getInt("overtimeHours");
            int totalHours = regularHours + overtimeHours;

            return new EmployeeSalaryDTO(
                    rs.getInt("accountID"),
                    rs.getString("fullName"),
                    rs.getBigDecimal("hourlyRate"),
                    totalHours, // Sử dụng tổng giờ làm việc
                    rs.getBigDecimal("totalSalary").add(rs.getBigDecimal("overtimeSalary")), // Tổng lương bao gồm lương làm thêm
                    overtimeHours,
                    rs.getBigDecimal("overtimeSalary")
            );
        });
    }

}
