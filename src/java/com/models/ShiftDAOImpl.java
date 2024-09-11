/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Win10
 */
@Repository
public class ShiftDAOImpl implements ShiftDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ShiftDAOImpl() {
    }

    public ShiftDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public void save(Shift shift) {
        String sql = "INSERT INTO Shift (ShiftName, StartTime, EndTime, Description) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, shift.getShiftName(), shift.getStartTime(), shift.getEndTime(), shift.getDescription());
    }

    @Override
    public Shift findById(int shiftID) {
        String sql = "SELECT * FROM Shift WHERE ShiftID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{shiftID}, new BeanPropertyRowMapper<>(Shift.class));
    }

    @Override
    public List<Shift> findAll() {
        String sql = "SELECT * FROM Shift";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Shift.class));
    }

    @Override
    public void update(Shift shift) {
        String sql = "UPDATE Shift SET ShiftName = ?, StartTime = ?, EndTime = ?, Description = ? WHERE ShiftID = ?";
        jdbcTemplate.update(sql, shift.getShiftName(), shift.getStartTime(), shift.getEndTime(), shift.getDescription(), shift.getShiftID());
    }

    @Override
    public void delete(int shiftID) {
        String sql = "DELETE FROM Shift WHERE ShiftID = ?";
        jdbcTemplate.update(sql, shiftID);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
