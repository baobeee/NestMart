/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.models;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Win10
 */
public interface WeekDetailsDAO {

    List<WeekDetails> findByWeekScheduleID(int weekScheduleID);

    public List<WeekDetails> getWeekDetailsByScheduleID(int weekScheduleID);

    public void save(WeekDetails weekDetails);

    public WeekDetails findByScheduleAndDayAndShift(int weekScheduleID, int dayID, int shiftID, int employeeID);

    WeekDetails findByID(int weekDetailID);

    void update(WeekDetails weekDetails);

    public List<WeekDetails> findByEmployeeID(int employeeID);

    List<WeekDetails> findByScheduleAndEmployee(int weekScheduleID, int employeeID);

    public boolean isShiftDuplicated(int employeeID, int dayID, int shiftID);

    public List<WeekDetails> findByShiftAndDay(int shiftID, int dayID);

    public void delete(int weekDetailID);

    public int getOvertimeHoursByWeekScheduleID(int weekScheduleID);
}
