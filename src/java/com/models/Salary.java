package com.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Salary {
    private int salaryID;
    private int accountID; 
    private Date salaryPaymentDate;
    private BigDecimal totalHours;
    private BigDecimal hourlyRate;
    private BigDecimal totalSalary;
    private int weekScheduleID;
    private int shiftID;

    // Constructors
    public Salary() {
    }

    public Salary(int salaryID, int accountID, Date salaryPaymentDate, BigDecimal totalHours, BigDecimal hourlyRate, BigDecimal totalSalary, int weekScheduleID, int shiftID) {
        this.salaryID = salaryID;
        this.accountID = accountID; 
        this.salaryPaymentDate = salaryPaymentDate;
        this.totalHours = totalHours;
        this.hourlyRate = hourlyRate;
        this.totalSalary = totalSalary;
        this.weekScheduleID = weekScheduleID;
        this.shiftID = shiftID;
    }

    // Getters and Setters
    public int getSalaryID() {
        return salaryID;
    }

    public void setSalaryID(int salaryID) {
        this.salaryID = salaryID;
    }

    public int getAccountID() {
        return accountID; 
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID; 
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
        calculateTotalSalary(); // Tính lại tổng lương khi thay đổi hourlyRate
    }

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public int getWeekScheduleID() {
        return weekScheduleID;
    }

    public void setWeekScheduleID(int weekScheduleID) {
        this.weekScheduleID = weekScheduleID;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    // Phương thức tính toán tổng lương dựa trên giờ làm và mức lương theo giờ
    private void calculateTotalSalary() {
        if (totalHours != null && hourlyRate != null) {
            this.totalSalary = totalHours.multiply(hourlyRate);
        }
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryID=" + salaryID +
                ", accountID=" + accountID + 
                ", salaryPaymentDate=" + salaryPaymentDate +
                ", totalHours=" + totalHours +
                ", hourlyRate=" + hourlyRate +
                ", totalSalary=" + totalSalary +
                ", weekScheduleID=" + weekScheduleID +
                ", shiftID=" + shiftID +
                '}';
    }
}
