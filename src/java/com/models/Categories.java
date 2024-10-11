package com.models;

public class Categories {
    private int categoryID;
    private String categoryName;
    private String description;
    private boolean hasProducts;

    public Categories() {
        this.categoryID = 0;
        this.categoryName = "";
        this.description = "";
    }

    public Categories(int categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.description = description;
    }
    
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasProducts() {
        return hasProducts;
    }

    public void setHasProducts(boolean hasProducts) {
        this.hasProducts = hasProducts;
    }
    
}
