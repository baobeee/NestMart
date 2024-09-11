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
public class DayOfWeekDAOImpl implements DayOfWeekDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DayOfWeekDAOImpl() {
    }

    public DayOfWeekDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public void save(DayOfWeek dayOfWeek) {
        String sql = "INSERT INTO DayOfWeek (DayName) VALUES (?)";
        jdbcTemplate.update(sql, dayOfWeek.getDayName());
    }

    @Override
    public DayOfWeek findById(int dayID) {
        String sql = "SELECT * FROM DayOfWeek WHERE DayID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{dayID}, new BeanPropertyRowMapper<>(DayOfWeek.class));
    }

    @Override
    public List<DayOfWeek> findAll() {
        String sql = "SELECT * FROM DayOfWeek";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DayOfWeek.class));
    }

    @Override
    public void update(DayOfWeek dayOfWeek) {
        String sql = "UPDATE DayOfWeek SET DayName = ? WHERE DayID = ?";
        jdbcTemplate.update(sql, dayOfWeek.getDayName(), dayOfWeek.getDayID());
    }

    @Override
    public void delete(int dayID) {
        String sql = "DELETE FROM DayOfWeek WHERE DayID = ?";
        jdbcTemplate.update(sql, dayID);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
