/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
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

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WeekSchedule> findAll() {
        String sql = "SELECT * FROM WeekSchedule";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            WeekSchedule weekSchedule = new WeekSchedule();
            weekSchedule.setWeekScheduleID(rs.getInt("weekScheduleID"));
            weekSchedule.setWeekStartDate(rs.getDate("weekStartDate"));
            weekSchedule.setWeekEndDate(rs.getDate("weekEndDate"));
            return weekSchedule;
        });
    }

    @Override
    public WeekSchedule findByID(int weekScheduleID) {
        String sql = "SELECT * FROM WeekSchedule WHERE weekScheduleID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{weekScheduleID}, (rs, rowNum) -> {
            WeekSchedule weekSchedule = new WeekSchedule();
            weekSchedule.setWeekScheduleID(rs.getInt("weekScheduleID"));
            weekSchedule.setWeekStartDate(rs.getDate("weekStartDate"));
            weekSchedule.setWeekEndDate(rs.getDate("weekEndDate"));
            return weekSchedule;
        });
    }

    @Override
    public void save(WeekSchedule weekSchedule) {
        String sql = "INSERT INTO WeekSchedule (weekStartDate, weekEndDate) VALUES (?,?)";
        jdbcTemplate.update(sql, weekSchedule.getWeekStartDate(), weekSchedule.getWeekEndDate());
    }

    @Override
    public boolean isWeekScheduleExists(Date weekStartDate, Date weekEndDate) {
        String sql = "SELECT COUNT(*) FROM WeekSchedule WHERE (weekStartDate <= ? AND weekEndDate >=?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{weekEndDate, weekStartDate}, Integer.class) > 0;
    }

    @Override
    public boolean hasAssignedWeekDetails(int weekScheduleID) {
        String sql = "SELECT COUNT(*) FROM WeekDetails WHERE weekScheduleID = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{weekScheduleID}, Integer.class);
        return count > 0;
    }

    @Override
    public List<WeekSchedule> findWeeksWithSalaries() {
        String sql = "SELECT ws.* FROM WeekSchedule ws "
                + "JOIN Salary s ON ws.weekScheduleID = s.weekScheduleID "
                + "GROUP BY ws.weekScheduleID, ws.weekStartDate, wsa.weekEndDate";

        return jdbcTemplate.query(sql, new Object[]{}, new RowMapper<WeekSchedule>() {
            @Override
            public WeekSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                WeekSchedule weekSchedule = new WeekSchedule();
                weekSchedule.setWeekScheduleID(rs.getInt("weekScheduleID"));
                weekSchedule.setWeekStartDate(rs.getDate("weekStartDate"));
                weekSchedule.setWeekEndDate(rs.getDate("weekEndDate"));
                return weekSchedule;
            }
        });
    }

    @Override
    public void delete(int weekScheduleID) {
        // Kiểm tra xem tuần làm việc có tồn tại không
        if (!hasAssignedWeekDetails(weekScheduleID)) { // Kiểm tra nếu không có phân công
            String sql = "DELETE FROM WeekSchedule WHERE weekScheduleID = ?";
            jdbcTemplate.update(sql, weekScheduleID);
        } else {
            throw new IllegalStateException("Cannot delete week schedule with assigned week details.");
        }
    }

    @Override
    public List<WeekSchedule> getPagedWeekSchedules(int page, int pageSize) {
        String query = "SELECT * FROM WeekSchedule ORDER BY WeekScheduleID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{offset, pageSize}, new RowMapper<WeekSchedule>() {
            @Override
            public WeekSchedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new WeekSchedule(
                        rs.getInt("WeekScheduleID"),
                        rs.getDate("WeekStartDate"),
                        rs.getDate("WeekEndDate")
                );
            }
        });
    }

    // Phương thức để đếm tổng số lịch tuần
    @Override
    public int getTotalWeekSchedules() {
        String query = "SELECT COUNT(*) FROM WeekSchedule";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

}
