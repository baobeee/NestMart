/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Win10
 */
public interface WeekScheduleDAO {

    List<WeekSchedule> findAll();

    WeekSchedule findByID(int weekScheduleID);

    public void save(WeekSchedule weekSchedule);

    public boolean isWeekScheduleExists(Date valueOf, Date valueOf0);

    public boolean hasAssignedWeekDetails(int weekScheduleID);

    public List<WeekSchedule> findWeeksWithSalaries();

    public void delete(int weekScheduleID);

    public List<WeekSchedule> getPagedWeekSchedules(int page, int pageSize);

    public int getTotalWeekSchedules();

}
