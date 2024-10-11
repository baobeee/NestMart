package com.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmployeeResponse {
    private int responseID;
    private int employeeID;
    private int feedbackID;
    private String responseContent;
    private LocalDateTime responseDate;
    private String formattedResponseDate;

    public EmployeeResponse() {
        this.responseID = 0;
        this.employeeID = 0;
        this.feedbackID = 0;
        this.responseContent = "";
        this.responseDate = null;
    }

    public EmployeeResponse(int responseID, int employeeID, int feedbackID, String responseContent, LocalDateTime responseDate) {
        this.responseID = responseID;
        this.employeeID = employeeID;
        this.feedbackID = feedbackID;
        this.responseContent = responseContent;
        this.responseDate = responseDate;
    }

    public int getResponseID() {
        return responseID;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public LocalDateTime getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }

    public String getFormattedResponseDate() {
        if (responseDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return responseDate.format(formatter);
        }
        return "";
    }

    public void setFormattedResponseDate(String formattedResponseDate) {
        this.formattedResponseDate = formattedResponseDate;
    }
}
