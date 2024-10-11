package com.models;

import java.util.Date;

public class Support {

    private int supportID;
    private Integer customerID;  // Changed from int to Integer
    private Integer employeeID;  // Changed from int to Integer
    private String message;
    private String status;
    private Date sendDate;
    private String fullName;
    private String phoneNumber;

    // Default constructor
    public Support() {
        this.supportID = 0;
        this.customerID = null;   // Changed from 0 to null
        this.employeeID = null;   // Changed from 0 to null
        this.message = "";
        this.status = "Pending";
        this.sendDate = new Date();
    }

    // Parameterized constructor
    public Support(int supportID, Integer customerID, Integer employeeID, String message, String status, Date sendDate) {
        this.supportID = supportID;
        this.customerID = customerID;
        this.employeeID = employeeID;
        this.message = message;
        this.status = status;
        this.sendDate = sendDate;
    }

    // Getters and Setters
    public int getSupportID() {
        return supportID;
    }

    public void setSupportID(int supportID) {
        this.supportID = supportID;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
