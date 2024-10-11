package com.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public class Orders {
    private int orderID;
    private int customerID;
    private int shipperID;
    private int feedbackID;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime orderDate;
    private String formattedOrderDate;
    private String orderStatus;
    private BigDecimal TotalAmount;
    private String paymentMethod;
    private String shippingAddress;
    private LocalDateTime transactionDate;
    private String formattedTransactionDate;
    private String transactionStatus;
    private String transactionDescription;
    private LocalDateTime returnRequestDate;
    private String formattedReturnRequestDate;
    private String returnRequestStatus;
    private String returnRequestReason;
    private Date deliveryDate;
    private String notes;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    private String shipperName;
    private String shipperEmail;
    private String shipperPhone;
    private String shipperAddress;
    private List<OrderDetails> orderDetails;
    private Integer returnRequestID;
    private List<ProductImage> productImage;
    private String name;
    private String phone;
    
    public Orders() {
        this.orderID = 0;
        this.customerID = 0;
        this.shipperID = 0;
        this.orderDate = null;
        this.orderStatus = "";
        this.TotalAmount = BigDecimal.ZERO;
        this.paymentMethod = "";
        this.shippingAddress = "";
        this.transactionDate = null;
        this.transactionStatus = "";
        this.transactionDescription = "";
        this.returnRequestDate = null;
        this.returnRequestStatus = "";
        this.returnRequestReason = "";
        this.deliveryDate = null;
        this.notes = "";
        this.customerName = "";
        this.customerEmail = "";
        this.customerPhone = "";
        this.customerAddress = "";
        this.shipperName = "";
        this.shipperEmail = "";
        this.shipperPhone = "";
        this.shipperAddress = "";
        this.orderDetails = new ArrayList<>();
    }
    
    public Orders(int orderID, int customerID, int shipperID, LocalDateTime orderDate, String formattedOrderDate, String orderStatus, 
            BigDecimal TotalAmount, String paymentMethod, String shippingAddress, LocalDateTime transactionDate, String formattedTransactionDate, 
            String transactionStatus, String transactionDescription, LocalDateTime returnRequestDate, String formattedReturnRequestDate, String returnRequestStatus, 
            String returnRequestReason, Date deliveryDate, String notes, String customerName, String customerEmail, String customerPhone, String customerAddress, 
            String shipperName, String shipperEmail, String shipperPhone, String shipperAddress, List<OrderDetails> orderDetails) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.shipperID = shipperID;
        this.orderDate = orderDate;
        this.formattedOrderDate = formattedOrderDate;
        this.orderStatus = orderStatus;
        this.TotalAmount = TotalAmount;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
        this.transactionDate = transactionDate;
        this.formattedTransactionDate = formattedTransactionDate;
        this.transactionStatus = transactionStatus;
        this.transactionDescription = transactionDescription;
        this.returnRequestDate = returnRequestDate;
        this.formattedReturnRequestDate = formattedReturnRequestDate;
        this.returnRequestStatus = returnRequestStatus;
        this.returnRequestReason = returnRequestReason;
        this.deliveryDate = deliveryDate;
        this.notes = notes;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.shipperName = shipperName;
        this.shipperEmail = shipperEmail;
        this.shipperPhone = shipperPhone;
        this.shipperAddress = shipperAddress;
        this.orderDetails = orderDetails;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getShipperID() {
        return shipperID;
    }

    public void setShipperID(int shipperID) {
        this.shipperID = shipperID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(BigDecimal TotalAmount) {
        this.TotalAmount = TotalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public LocalDateTime getReturnRequestDate() {
        return returnRequestDate;
    }

    public void setReturnRequestDate(LocalDateTime returnRequestDate) {
        this.returnRequestDate = returnRequestDate;
    }

    public String getReturnRequestStatus() {
        return returnRequestStatus;
    }

    public void setReturnRequestStatus(String returnRequestStatus) {
        this.returnRequestStatus = returnRequestStatus;
    }

    public String getReturnRequestReason() {
        return returnRequestReason;
    }

    public void setReturnRequestReason(String returnRequestReason) {
        this.returnRequestReason = returnRequestReason;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getFormattedOrderDate() {
        return formattedOrderDate;
    }

    public void setFormattedOrderDate(String formattedOrderDate) {
        this.formattedOrderDate = formattedOrderDate;
    }

    public String getFormattedTransactionDate() {
        return formattedTransactionDate;
    }

    public void setFormattedTransactionDate(String formattedTransactionDate) {
        this.formattedTransactionDate = formattedTransactionDate;
    }

    public String getFormattedReturnRequestDate() {
        return formattedReturnRequestDate;
    }

    public void setFormattedReturnRequestDate(String formattedReturnRequestDate) {
        this.formattedReturnRequestDate = formattedReturnRequestDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public String getShipperEmail() {
        return shipperEmail;
    }

    public void setShipperEmail(String shipperEmail) {
        this.shipperEmail = shipperEmail;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }

    public String getShipperAddress() {
        return shipperAddress;
    }

    public void setShipperAddress(String shipperAddress) {
        this.shipperAddress = shipperAddress;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Integer getReturnRequestID() {
        return returnRequestID;
    }

    public void setReturnRequestID(Integer returnRequestID) {
        this.returnRequestID = returnRequestID;
    }

    public List<ProductImage> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<ProductImage> productImage) {
        this.productImage = productImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
