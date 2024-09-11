/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class Offers {

    private int offerID;
    private String productID;
    private int discountID;
    private String offerName;
    private String description;
    private Date startDate;
    private Date endDate;
    private String productName;
    private String discountName;

    public Offers() {
        this.offerID = 0;
        this.productID = "";
        this.discountID = 0;
        this.offerName = "";
        this.description = "";
        this.startDate = null;
        this.endDate = null;
        this.productName = "";
        this.discountName = "";
    }

    public Offers(int offerID, String productID, int discountID, String offerName, String description, Date startDate, Date endDate, String productName, String discountName) {
        this.offerID = offerID;
        this.productID = productID;
        this.discountID = discountID;
        this.offerName = offerName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.productName = productName;
        this.discountName = discountName;
    }

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
