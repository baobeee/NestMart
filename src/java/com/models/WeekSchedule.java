package com.models;

import java.util.Date;

public class WeekSchedule {

    private int weekScheduleID;
    private Date weekStartDate;
    private Date weekEndDate;

    public WeekSchedule() {
    }

    public WeekSchedule(int weekScheduleID, Date weekStartDate, Date weekEndDate) {
        this.weekScheduleID = weekScheduleID;
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
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

    
}
