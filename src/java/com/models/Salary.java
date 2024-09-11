package com.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Salary {

    private int salaryID;
    private int accountID;
    private String employeeName;
    private Date salaryPaymentDate;
    private BigDecimal totalHours;
    private BigDecimal hourlyRate;
    private BigDecimal totalSalary;
    private Date weekStartDate;
    private Date weekEndDate;

    // Thông tin chi tiết từ WeekDetails
    private BigDecimal overtimeHours;
    private String status;
    private String notes;
    private String dayOfWeek;
    private String shiftName;
    private Time shiftStartTime;
    private Time shiftEndTime;
    private BigDecimal dailySalary;

    // Constructors
    public Salary() {
    }

    public Salary(int salaryID, int accountID, String employeeName, Date salaryPaymentDate,
            BigDecimal totalHours, BigDecimal hourlyRate, BigDecimal totalSalary,
            Date weekStartDate, Date weekEndDate, BigDecimal overtimeHours, String status,
            String notes, String dayOfWeek, String shiftName, Time shiftStartTime,
            Time shiftEndTime, BigDecimal dailySalary) {
        this.salaryID = salaryID;
        this.accountID = accountID;
        this.employeeName = employeeName;
        this.salaryPaymentDate = salaryPaymentDate;
        this.totalHours = totalHours;
        this.hourlyRate = hourlyRate;
        this.totalSalary = totalSalary;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.overtimeHours = overtimeHours;
        this.status = status;
        this.notes = notes;
        this.dayOfWeek = dayOfWeek;
        this.shiftName = shiftName;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.dailySalary = dailySalary;
    }

    // Getters and setters
    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getSalaryPaymentDate() {
        return salaryPaymentDate;
    }

    public void setSalaryPaymentDate(Date salaryPaymentDate) {
        this.salaryPaymentDate = salaryPaymentDate;
    }

    public BigDecimal getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(BigDecimal totalHours) {
        this.totalHours = totalHours;
    }

    public BigDecimal getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
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

    // New getters and setters
    public BigDecimal getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(BigDecimal overtimeHours) {
        this.overtimeHours = overtimeHours;
    }


    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
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

    public BigDecimal getDailySalary() {
        return dailySalary;
    }

    public void setDailySalary(BigDecimal dailySalary) {
        this.dailySalary = dailySalary;
    }

}
