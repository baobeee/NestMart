package com.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class WeekDetails {
    private int weekDetailID;
    private int weekScheduleID;
    private int employeeID;
    private String employeeName;  
    private int dayID;
    private String dayName;       
    private int shiftID;
    private String shiftName; 
    private Time shiftStartTime; 
    private Time shiftEndTime;   
    private BigDecimal overtimeHours;
    private String status;
    private boolean isUpdated;
    private Date updateDate;
    private String notes;
    private String role; 
    private DayOfWeek dayOfWeek;
    private Shift shift;

    // Constructors
    public WeekDetails() {}

    public WeekDetails(int weekDetailID, int weekScheduleID, int employeeID, String employeeName, int dayID, String dayName, int shiftID, String shiftName, Time shiftStartTime, Time shiftEndTime, BigDecimal overtimeHours, String status, boolean isUpdated, Date updateDate, String notes, String role, DayOfWeek dayOfWeek, Shift shift) {
        this.weekDetailID = weekDetailID;
        this.weekScheduleID = weekScheduleID;
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
        this.isUpdated = isUpdated;
        this.updateDate = updateDate;
        this.notes = notes;
        this.role = role; // Initialize role
        this.dayOfWeek = dayOfWeek;
        this.shift = shift;
    }

    // Getters and Setters
    public int getWeekDetailID() {
        return weekDetailID;
    }

    public void setWeekDetailID(int weekDetailID) {
        this.weekDetailID = weekDetailID;
    }

    public int getWeekScheduleID() {
        return weekScheduleID;
    }

    public void setWeekScheduleID(int weekScheduleID) {
        this.weekScheduleID = weekScheduleID;
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

    public Time getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(Time shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public Time getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Time shiftEndTime) {
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

    public boolean getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getRole() {
        return role; 
    }

    public void setRole(String role) {
        this.role = role; 
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
