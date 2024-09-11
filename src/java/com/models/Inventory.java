package com.models;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;

public class Inventory {
        private String productID;
        @Min(1)
        private int quantity;
        private LocalDateTime lastUpdated;
        private String formattedDateTime;
        private String productName;

    public Inventory() {
        this.productID = "";
        this.quantity = 0;
        this.lastUpdated = null;
    }

    public Inventory(String productID, int quantity, LocalDateTime lastUpdated) {
        this.productID = productID;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFormattedDateTime() {
        return formattedDateTime;
    }

    public void setFormattedDateTime(String formattedDateTime) {
        this.formattedDateTime = formattedDateTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
}
