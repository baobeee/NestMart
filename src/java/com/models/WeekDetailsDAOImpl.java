package com.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

public class WeekDetailsDAOImpl implements WeekDetailsDAO {

    private JdbcTemplate jdbcTemplate;

    public WeekDetailsDAOImpl() {
    }

    public WeekDetailsDAOImpl(DataSource dataSource) {
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
    public void save(WeekDetails weekDetails) {
        String sql;
        if (weekDetails.getWeekDetailID() == 0) {
            // Nếu weekDetailID là 0, nghĩa là đây là một bản ghi mới, thực hiện INSERT
            sql = "INSERT INTO WeekDetails (weekScheduleID, employeeID, dayID, shiftID) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, weekDetails.getWeekScheduleID(), weekDetails.getEmployeeID(), weekDetails.getDayID(), weekDetails.getShiftID());
        } else {
            // Nếu weekDetailID khác 0, thực hiện UPDATE
            sql = "UPDATE WeekDetails SET weekScheduleID = ?, employeeID = ?, dayID = ?, shiftID = ? WHERE weekDetailID = ?";
            jdbcTemplate.update(sql, weekDetails.getWeekScheduleID(), weekDetails.getEmployeeID(), weekDetails.getDayID(), weekDetails.getShiftID(), weekDetails.getWeekDetailID());
        }
    }

    @Override
    public List<WeekDetails> findByWeekScheduleID(int weekScheduleID) {
        String sql = "SELECT * FROM WeekDetails WHERE WeekScheduleID =?";
        return jdbcTemplate.query(sql, new Object[]{weekScheduleID}, (rs, rowNum) -> {
            WeekDetails weekDetails = new WeekDetails();
            weekDetails.setWeekDetailID(rs.getInt("weekDetailID"));
            weekDetails.setWeekScheduleID(rs.getInt("weekScheduleID"));
            weekDetails.setEmployeeID(rs.getInt("employeeID"));
            weekDetails.setDayID(rs.getInt("dayID"));
            weekDetails.setShiftID(rs.getInt("shiftID"));
            weekDetails.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
            weekDetails.setStatus(rs.getString("status"));
            weekDetails.setIsUpdated(rs.getBoolean("isUpdated"));
            weekDetails.setUpdateDate(rs.getDate("updateDate"));
            weekDetails.setNotes(rs.getString("notes"));

            return weekDetails;
        });
    }

    @Override
    public List<WeekDetails> getWeekDetailsByScheduleID(int weekScheduleID) {
        String sql = "SELECT a.fullName AS employeeName, d.dayName AS dayName, s.shiftName, s.startTime, "
                + "s.endTime, wd.overtimeHours, wd.status, wd.notes "
                + "FROM WeekDetails wd "
                + "JOIN Accounts a ON wd.employeeID = a.accountID "
                + "JOIN DayOfWeek d ON wd.dayID = d.dayID "
                + "JOIN Shift s ON wd.shiftID = s.shiftID "
                + "WHERE wd.weekScheduleID = ?";

        return jdbcTemplate.query(sql, new Object[]{weekScheduleID}, new RowMapper<WeekDetails>() {
            @Override
            public WeekDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                WeekDetails weekDetails = new WeekDetails();

                // Tạo đối tượng Accounts và set giá trị fullName
                Accounts account = new Accounts();
                account.setFullName(rs.getString("employeeName"));
                weekDetails.setEmployeeID(account.getAccountID());  // Giả định setAccountID ở đây

                // Tạo đối tượng DayOfWeek và set giá trị dayName
                DayOfWeek dayOfWeek = new DayOfWeek();
                dayOfWeek.setDayName(rs.getString("dayName"));
                weekDetails.setDayID(dayOfWeek.getDayID());

                // Tạo đối tượng Shift và set giá trị shiftName, startTime, endTime
                Shift shift = new Shift();
                shift.setShiftName(rs.getString("shiftName"));
                shift.setStartTime(rs.getTime("startTime"));
                shift.setEndTime(rs.getTime("endTime"));
                weekDetails.setShiftID(shift.getShiftID());

                // Set các giá trị còn lại
                weekDetails.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
                weekDetails.setStatus(rs.getString("status"));
                weekDetails.setNotes(rs.getString("notes"));

                return weekDetails;
            }
        });
    }

    @Override
    public WeekDetails findByScheduleAndDayAndShift(int weekScheduleID, int dayID, int shiftID, int employeeID) {
        String sql = "SELECT * FROM WeekDetails WHERE weekScheduleID = ? AND dayID = ? AND shiftID = ? AND employeeID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{weekScheduleID, dayID, shiftID, employeeID}, (rs, rowNum) -> {
                WeekDetails weekDetails = new WeekDetails();
                weekDetails.setWeekDetailID(rs.getInt("weekDetailID"));
                weekDetails.setWeekScheduleID(rs.getInt("weekScheduleID"));
                weekDetails.setEmployeeID(rs.getInt("employeeID"));
                weekDetails.setDayID(rs.getInt("dayID"));
                weekDetails.setShiftID(rs.getInt("shiftID"));
                return weekDetails;
            });
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no result is found
        } catch (Exception e) {
            throw new RuntimeException("Error fetching WeekDetails for ScheduleID: " + weekScheduleID, e);
        }
    }

    @Override
    public WeekDetails findByID(int weekDetailID) {
        String sql = "SELECT * FROM WeekDetails WHERE weekDetailID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{weekDetailID}, (rs, rowNum) -> {
                WeekDetails weekDetails = new WeekDetails();
                weekDetails.setWeekDetailID(rs.getInt("weekDetailID"));
                weekDetails.setWeekScheduleID(rs.getInt("weekScheduleID"));
                weekDetails.setEmployeeID(rs.getInt("employeeID"));
                weekDetails.setDayID(rs.getInt("dayID"));
                weekDetails.setShiftID(rs.getInt("shiftID"));
                weekDetails.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
                weekDetails.setStatus(rs.getString("status"));
                weekDetails.setIsUpdated(rs.getBoolean("isUpdated"));
                weekDetails.setUpdateDate(rs.getDate("updateDate"));
                weekDetails.setNotes(rs.getString("notes"));
                return weekDetails;
            });
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no result is found
        } catch (Exception e) {
            throw new RuntimeException("Error fetching WeekDetails with ID: " + weekDetailID, e);
        }
    }

    @Override
    public void update(WeekDetails weekDetails) {
        String sql = "UPDATE WeekDetails SET weekScheduleID = ?, employeeID = ?, dayID = ?, shiftID = ?, "
                + "overtimeHours = ?, status = ?, isUpdated = ?, updateDate = ?, notes = ? WHERE weekDetailID = ?";

        try {
            jdbcTemplate.update(sql, weekDetails.getWeekScheduleID(), weekDetails.getEmployeeID(), weekDetails.getDayID(),
                    weekDetails.getShiftID(), weekDetails.getOvertimeHours(), weekDetails.getStatus(), weekDetails.isIsUpdated(),
                    weekDetails.getUpdateDate(), weekDetails.getNotes(), weekDetails.getWeekDetailID());
        } catch (Exception e) {
            throw new RuntimeException("Error updating WeekDetails for ID: " + weekDetails.getWeekDetailID(), e);
        }
    }

    @Override
    public List<WeekDetails> findByEmployeeID(int employeeID) {
        String sql = "SELECT * FROM WeekDetails WHERE employeeID = ?";
        return jdbcTemplate.query(sql, new Object[]{employeeID}, (rs, rowNum) -> {
            WeekDetails weekDetails = new WeekDetails();
            weekDetails.setWeekDetailID(rs.getInt("weekDetailID"));
            weekDetails.setWeekScheduleID(rs.getInt("weekScheduleID"));
            weekDetails.setEmployeeID(rs.getInt("employeeID"));
            weekDetails.setDayID(rs.getInt("dayID"));
            weekDetails.setShiftID(rs.getInt("shiftID"));
            weekDetails.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
            weekDetails.setStatus(rs.getString("status"));
            weekDetails.setIsUpdated(rs.getBoolean("isUpdated"));
            weekDetails.setUpdateDate(rs.getDate("updateDate"));
            weekDetails.setNotes(rs.getString("notes"));
            return weekDetails;
        });
    }

    @Override
    public List<WeekDetails> findByScheduleAndEmployee(int weekScheduleID, int employeeID) {
        String sql = "SELECT * FROM WeekDetails WHERE weekScheduleID = ? AND employeeID = ?";
        return jdbcTemplate.query(sql, new Object[]{weekScheduleID, employeeID}, (rs, rowNum) -> {
            WeekDetails weekDetails = new WeekDetails();
            weekDetails.setWeekDetailID(rs.getInt("weekDetailID"));
            weekDetails.setWeekScheduleID(rs.getInt("weekScheduleID"));
            weekDetails.setEmployeeID(rs.getInt("employeeID"));
            weekDetails.setDayID(rs.getInt("dayID"));
            weekDetails.setShiftID(rs.getInt("shiftID"));
            weekDetails.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
            weekDetails.setStatus(rs.getString("status"));
            weekDetails.setIsUpdated(rs.getBoolean("isUpdated"));
            weekDetails.setUpdateDate(rs.getDate("updateDate"));
            weekDetails.setNotes(rs.getString("notes"));
            return weekDetails;
        });
    }

    @Override
    public boolean isShiftDuplicated(int employeeID, int dayID, int shiftID) {
        String sql = "SELECT COUNT(*) FROM WeekDetails WHERE employeeID = ? AND dayID = ? AND shiftID = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{employeeID, dayID, shiftID}, Integer.class);
        return count > 0;
    }

    @Override
    public List<WeekDetails> findByShiftAndDay(int shiftID, int dayID) {
        String sql = "SELECT * FROM WeekDetails WHERE shiftID = ? AND dayID = ?";

        // Tạo RowMapper bên trong phương thức
        RowMapper<WeekDetails> rowMapper = new RowMapper<WeekDetails>() {
            @Override
            public WeekDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                WeekDetails weekDetail = new WeekDetails();
                weekDetail.setWeekDetailID(rs.getInt("weekDetailID"));
                weekDetail.setWeekScheduleID(rs.getInt("weekScheduleID"));
                weekDetail.setEmployeeID(rs.getInt("employeeID"));
                weekDetail.setDayID(rs.getInt("dayID"));
                weekDetail.setShiftID(rs.getInt("shiftID"));
                weekDetail.setOvertimeHours(rs.getBigDecimal("overtimeHours"));
                weekDetail.setStatus(rs.getString("status"));
                weekDetail.setIsUpdated(rs.getBoolean("isUpdated"));
                weekDetail.setUpdateDate(rs.getDate("updateDate"));
                weekDetail.setNotes(rs.getString("notes"));
                return weekDetail;
            }
        };

        return jdbcTemplate.query(sql, new Object[]{shiftID, dayID}, rowMapper);
    }

    @Override
    public void delete(int weekDetailID) {
        String sql = "DELETE FROM WeekDetails WHERE weekDetailID = ?";
        jdbcTemplate.update(sql, weekDetailID);
    }

    @Override
    public int getOvertimeHoursByWeekScheduleID(int weekScheduleID) {
        String sql = "SELECT SUM(overtimeHours) FROM WeekDetails WHERE weekScheduleID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{weekScheduleID}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        } catch (DataAccessException e) {
            System.err.println("Error retrieving overtime hours for weekScheduleID " + weekScheduleID + ": " + e.getMessage());
            return 0;
        }
    }

}
