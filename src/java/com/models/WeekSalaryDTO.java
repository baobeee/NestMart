/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Win10
 */
public class WeekSalaryDTO {

    private int weekScheduleID;
    private Date weekStartDate;
    private Date weekEndDate;
    private BigDecimal totalSalary;
    private int totalHoursWorked;
    private BigDecimal totalOvertimeHours;
    private BigDecimal totalOvertimeSalary;
    private Date salaryPaymentDate;

    public WeekSalaryDTO(int weekScheduleID, Date weekStartDate, Date weekEndDate, BigDecimal totalSalary, int totalHoursWorked, BigDecimal totalOvertimeHours, BigDecimal totalOvertimeSalary, Date salaryPaymentDate) {
        this.weekScheduleID = weekScheduleID;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.totalSalary = totalSalary != null ? totalSalary : BigDecimal.ZERO;
        this.totalHoursWorked = totalHoursWorked;
        this.totalOvertimeHours = totalOvertimeHours != null ? totalOvertimeHours : BigDecimal.ZERO;
        this.totalOvertimeSalary = totalOvertimeSalary != null ? totalOvertimeSalary : BigDecimal.ZERO;
        this.salaryPaymentDate = salaryPaymentDate;
    }

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

    public BigDecimal getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public int getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }

    public BigDecimal getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(BigDecimal totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }

    public BigDecimal getTotalOvertimeSalary() {
        return totalOvertimeSalary;
    }

    public void setTotalOvertimeSalary(BigDecimal totalOvertimeSalary) {
        this.totalOvertimeSalary = totalOvertimeSalary;
    }

    public Date getSalaryPaymentDate() {
        return salaryPaymentDate;
    }

    public void setSalaryPaymentDate(Date salaryPaymentDate) {
        this.salaryPaymentDate = salaryPaymentDate;
    }

}
