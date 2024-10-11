package com.models;

import java.util.Date;
import java.util.List;

public class DiscountClient {
    private int discountID;
    private String discountName;
    private String image;
     private Date startDate;
    private Date endDate;
    private String description;
    private List<ProductsClient> products; 

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ProductsClient> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsClient> products) {
        this.products = products;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
