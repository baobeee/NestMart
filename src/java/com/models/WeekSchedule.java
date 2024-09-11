package com.models;

import java.math.BigDecimal;
import java.util.Date;

public class WeekSchedule {

    private int weekScheduleID;
    private Date weekStartDate;
    private Date weekEndDate;
    private int employeeID;
    private String employeeName;
    private int dayID;
    private String dayName;
    private int shiftID;
    private String shiftName;
    private Date shiftStartTime;
    private Date shiftEndTime;
    private BigDecimal overtimeHours;
    private String status;
    private String notes;

    public WeekSchedule() {
    }

    public WeekSchedule(int weekScheduleID, Date weekStartDate, Date weekEndDate, int employeeID, String employeeName, int dayID, String dayName, int shiftID, String shiftName, Date shiftStartTime, Date shiftEndTime, BigDecimal overtimeHours, String status, String notes) {
        this.weekScheduleID = weekScheduleID;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.dayID = dayID;
        this.dayName = dayName;
        this.shiftID = shiftID;
        this.shiftName = shiftName;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.overtimeHours = overtimeHours;
        this.status = status;
        this.notes = notes;
    }

    // Getters and Setters
    public int getWeekScheduleID() {
        return weekScheduleID;
    }

    public void setWeekScheduleID(int weekScheduleID) {
        this.weekScheduleID = weekScheduleID;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public String getShiftName() {
        return shiftName;
    }

    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
    }

    public Date getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(Date shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public Date getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Date shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public BigDecimal getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(BigDecimal overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
