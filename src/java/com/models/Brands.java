package com.models;

public class Brands {
    private int brandID;
    private String brandName;
    private String description;

    public Brands() {
        this.brandID = 0;
        this.brandName = "";
        this.description = "";
    }

    public Brands(int brandID, String brandName, String description) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.description = description;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
