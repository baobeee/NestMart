/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class Discount {

    private int discountID;
    private String discountName;
    private String description;
    private Date startDate;
    private Date endDate;
    private String image;

    public Discount() {
        this.discountID = 0;
        this.discountName = "";
        this.description = "";
        this.startDate = null;
        this.endDate = null;
        this.image = "";

    }

    public Discount(int discountID, String discountName, String description, Date startDate, Date endDate, String image) {
        this.discountID = discountID;
        this.discountName = discountName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDiscountID() {
        return discountID;
    }

    public String getDiscountName() {
        return discountName;
    }


    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getImage() {
        return image;
    }

}
