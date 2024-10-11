// ReturnRequestResponse.java
package com.models;

import java.time.LocalDateTime;
import java.util.Date;

public class ReturnRequestResponse {
    private int returnRequestResponseID; // ID của phản hồi yêu cầu trả hàng
    private int orderID; // Mã đơn hàng liên quan đến yêu cầu trả hàng
    private int employeeID; // Mã nhân viên xử lý yêu cầu trả hàng
    private LocalDateTime returnRequestResponseDate; // Changed from Date to LocalDateTime
    private String message; // Thông điệp hoặc lý do trả hàng
    
    // Constructors
    public ReturnRequestResponse() {}

    public ReturnRequestResponse(int returnRequestResponseID, int orderID, int employeeID, LocalDateTime  returnRequestResponseDate, String message) {
        this.returnRequestResponseID = returnRequestResponseID;
        this.orderID = orderID;
        this.employeeID = employeeID;
        this.returnRequestResponseDate = returnRequestResponseDate;
        this.message = message;
    }

    // Getters and Setters
    public int getReturnRequestResponseID() {
        return returnRequestResponseID;
    }

    public void setReturnRequestResponseID(int returnRequestResponseID) {
        this.returnRequestResponseID = returnRequestResponseID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

  
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getReturnRequestResponseDate() {
        return returnRequestResponseDate;
    }

    public void setReturnRequestResponseDate(LocalDateTime returnRequestResponseDate) {
        this.returnRequestResponseDate = returnRequestResponseDate;
    }
}
