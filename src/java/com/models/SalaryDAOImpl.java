package com.models;

import java.math.BigDecimal;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class SalaryDAOImpl implements SalaryDAO {

    private JdbcTemplate jdbcTemplate;

    public SalaryDAOImpl() {
    }

    public SalaryDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public void save(Salary salary) {
        String sql = "INSERT INTO Salary (AccountID, SalaryPaymentDate, TotalHours, HourlyRate, TotalSalary) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                salary.getAccountID(),
                salary.getSalaryPaymentDate(),
                salary.getTotalHours(),
                salary.getHourlyRate(),
                salary.getTotalSalary()
        );
    }

    @Override
    public Salary findById(int id) {
        String sql = "SELECT "
                + "s.SalaryID, "
                + "a.FullName AS EmployeeName, "
                + "s.SalaryPaymentDate, "
                + "s.TotalHours, "
                + "s.HourlyRate, "
                + "s.TotalSalary, "
                + "ws.WeekStartDate, "
                + "ws.WeekEndDate "
                + "FROM Salary s "
                + "JOIN Accounts a ON s.AccountID = a.AccountID "
                + "JOIN WeekSchedule ws ON s.WeekScheduleID = ws.WeekScheduleID "
                + "WHERE s.SalaryID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Salary salary = new Salary();
            salary.setSalaryID(rs.getInt("SalaryID"));
            salary.setAccountID(rs.getInt("AccountID"));
            salary.setEmployeeName(rs.getString("EmployeeName"));
            salary.setSalaryPaymentDate(rs.getDate("SalaryPaymentDate"));
            salary.setTotalHours(rs.getBigDecimal("TotalHours"));
            salary.setHourlyRate(rs.getBigDecimal("HourlyRate"));
            salary.setTotalSalary(rs.getBigDecimal("TotalSalary"));
            salary.setWeekStartDate(rs.getDate("WeekStartDate"));
            salary.setWeekEndDate(rs.getDate("WeekEndDate"));
            return salary;
        });
    }

    @Override
    public void update(Salary salary) {
        String sql = "UPDATE Salary SET AccountID = ?, SalaryPaymentDate = ?, TotalHours = ?, HourlyRate = ?, TotalSalary = ? WHERE SalaryID = ?";
        jdbcTemplate.update(sql,
                salary.getAccountID(),
                salary.getSalaryPaymentDate(),
                salary.getTotalHours(),
                salary.getHourlyRate(),
                salary.getTotalSalary(),
                salary.getSalaryID()
        );
    }

    @Override
    public void delete(int salaryID) {
        String sql = "DELETE FROM Salary WHERE SalaryID = ?";
        jdbcTemplate.update(sql, salaryID);
    }

    @Override
    public List<Salary> getAllSalaries() {
        String sql = "SELECT s.SalaryID, a.FullName AS EmployeeName, s.SalaryPaymentDate, s.TotalHours, "
                + "s.HourlyRate, s.TotalSalary, ws.WeekStartDate, ws.WeekEndDate "
                + "FROM Salary s "
                + "JOIN Accounts a ON s.AccountID = a.AccountID "
                + "LEFT JOIN WeekSchedule ws ON s.WeekScheduleID = ws.WeekScheduleID";

        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Salary salary = new Salary();
            salary.setSalaryID(rs.getInt("SalaryID"));
            // Có thể không cần thiết, tùy thuộc vào cấu trúc bảng
            // salary.setAccountID(rs.getInt("AccountID")); 
            salary.setEmployeeName(rs.getString("EmployeeName"));
            salary.setSalaryPaymentDate(rs.getDate("SalaryPaymentDate"));
            salary.setTotalHours(rs.getBigDecimal("TotalHours"));
            salary.setHourlyRate(rs.getBigDecimal("HourlyRate"));
            salary.setTotalSalary(rs.getBigDecimal("TotalSalary"));
            salary.setWeekStartDate(rs.getDate("WeekStartDate"));
            salary.setWeekEndDate(rs.getDate("WeekEndDate"));
            return salary;
        });
    }

    @Override
    //details salary
    public List<Salary> getSalariesByAccountId(int accountID) {
        String sql = "SELECT a.FullName AS EmployeeName, s.SalaryPaymentDate, s.HourlyRate, wd.OvertimeHours, wd.Status, "
                + "d.DayName AS DayOfWeek, sh.ShiftName, sh.StartTime AS ShiftStartTime, sh.EndTime AS ShiftEndTime, wd.Notes "
                + "FROM Salary s "
                + "JOIN WeekDetails wd ON s.AccountID = wd.EmployeeID "
                + "JOIN Accounts a ON s.AccountID = a.AccountID "
                + "JOIN DayOfWeek d ON wd.DayID = d.DayID "
                + "JOIN Shift sh ON wd.ShiftID = sh.ShiftID "
                + "WHERE s.AccountID = ?";

        // Tính tổng lương tuần cho AccountID
        BigDecimal totalWeeklySalary = jdbcTemplate.queryForObject(
                "SELECT SUM(6 * s.HourlyRate + wd.OvertimeHours * s.HourlyRate) "
                + "FROM Salary s "
                + "JOIN WeekDetails wd ON s.AccountID = wd.EmployeeID "
                + "WHERE s.AccountID = ?",
                new Object[]{accountID},
                BigDecimal.class
        );

        // Truy vấn chi tiết lương
        List<Salary> salaries = jdbcTemplate.query(sql, new Object[]{accountID}, (ResultSet rs, int rowNum) -> {
            Salary salary = new Salary();
            salary.setEmployeeName(rs.getString("EmployeeName"));
            salary.setSalaryPaymentDate(rs.getDate("SalaryPaymentDate"));
            salary.setHourlyRate(rs.getBigDecimal("HourlyRate"));
            salary.setOvertimeHours(rs.getBigDecimal("OvertimeHours"));
            salary.setStatus(rs.getString("Status"));
            salary.setDayOfWeek(rs.getString("DayOfWeek"));
            salary.setShiftName(rs.getString("ShiftName"));
            salary.setShiftStartTime(rs.getTime("ShiftStartTime"));
            salary.setShiftEndTime(rs.getTime("ShiftEndTime"));
            salary.setNotes(rs.getString("Notes"));

            // Tính lương ngày
            BigDecimal hourlyRate = rs.getBigDecimal("HourlyRate");
            BigDecimal overtimeHours = rs.getBigDecimal("OvertimeHours");
            BigDecimal dailySalary = BigDecimal.valueOf(6).multiply(hourlyRate).add(overtimeHours.multiply(hourlyRate));

            salary.setDailySalary(dailySalary);
            salary.setTotalSalary(totalWeeklySalary);

            return salary;
        });

        return salaries;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
