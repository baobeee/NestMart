package com.models;

import java.util.Date;

public class Offers {

    private int offerID;
    private String productID;
    private int discountID;
    private String offerName;
    private String description;
    private String productName;
    private String discountName;
    private String discountType; 
    private int discountValue;    

    public Offers() {
        this.offerID = 0;
        this.productID = "";
        this.discountID = 0;
        this.offerName = "";
        this.description = "";
        this.productName = "";
        this.discountName = "";
        this.discountType = ""; 
        this.discountValue = 0;  
    }

    public Offers(int offerID, String productID, int discountID, String offerName, String description, String productName, String discountName, String discountType, int discountValue) {
        this.offerID = offerID;
        this.productID = productID;
        this.discountID = discountID;
        this.offerName = offerName;
        this.description = description;
        this.productName = productName;
        this.discountName = discountName;
        this.discountType = discountType;   
        this.discountValue = discountValue; 
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    // Các getter và setter khác
    public int getOfferID() {
        return offerID;
    }

    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }
}
