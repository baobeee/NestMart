package com.models;

import java.sql.Time;

public class Shift {
    private int shiftID;
    private String shiftName;
    private Time startTime;
    private Time endTime;
    private String description;

    // Constructors
    public Shift() {}

    public Shift(int shiftID, String shiftName, Time startTime, Time endTime, String description) {
        this.shiftID = shiftID;
        this.shiftName = shiftName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    // Getters and setters
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isValidShift() {
        return startTime.before(endTime);
    }

    // Phương thức để tính độ dài ca
    public long getShiftDuration() {
        return (endTime.getTime() - startTime.getTime()) / (60 * 1000);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "shiftID=" + shiftID +
                ", shiftName='" + shiftName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                '}';
    }
}
