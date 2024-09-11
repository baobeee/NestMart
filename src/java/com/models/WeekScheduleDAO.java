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

    public boolean isWeekScheduleExists(Date startDate, Date endDate);

    public void generateSalariesForWeek(int weekScheduleID);

    public List<WeekSchedule> getAllWeekSchedules();

    public LinkedHashMap<String, Map<String, List<WeekDetails>>> getWeekDetailsGroupedByDay(int weekScheduleID);

    public WeekSchedule getWeekScheduleById(int weekScheduleID);

    public void createWeekSchedule(WeekSchedule weekSchedule);

    public boolean hasDetailsForWeek(int weekScheduleID);

    public void deleteWeekSchedule(int WeekScheduleID);

    public List<DayOfWeek> getDaysOfWeek();

    public List<Shift> getAllShifts();

    public List<Accounts> getAllEmployees();

    public boolean isWeekScheduleExists(int weekScheduleID);
    
    public void insertWeekDetails(int weekScheduleID, int dayID, int shiftID, int employeeID,
            BigDecimal overtimeHours, String status, String notes);
}
