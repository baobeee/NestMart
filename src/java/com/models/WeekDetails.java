package com.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class WeekDetails {

    private int weekDetailID;
    private int weekScheduleID;
    private int employeeID;
    private int dayID;
    private int shiftID;
    private BigDecimal overtimeHours;
    private String status;
    private boolean isUpdated;
    private Date updateDate;
    private String notes;

    public WeekDetails() {
    }

    public WeekDetails(int weekDetailID, int weekScheduleID, int employeeID, int dayID, int shiftID, BigDecimal overtimeHours, String status, boolean isUpdated, Date updateDate, String notes) {
        this.weekDetailID = weekDetailID;
        this.weekScheduleID = weekScheduleID;
        this.employeeID = employeeID;
        this.dayID = dayID;
        this.shiftID = shiftID;
        this.overtimeHours = overtimeHours;
        this.status = status;
        this.isUpdated = isUpdated;
        this.updateDate = updateDate;
        this.notes = notes;
    }

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

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
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

    public boolean isIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "WeekDetails{dayID=" + dayID + ", shiftID=" + shiftID + ", employeeID=" + employeeID + "}";
    }

}
