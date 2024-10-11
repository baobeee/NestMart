package com.models;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetails {
    private int categoryDetailID;
    private int categoryID;
    private String productID;
    private String attributeName;
    private String attributeValue;
    private String productName;
    private String categoryName;
     private int numAttributes;
    private List<CategoryDetails> attributes;

    public CategoryDetails() {
        this.categoryDetailID = 0;
        this.categoryID = 0;
        this.productID = "";
        this.attributeName = "";
        this.attributeValue = "";
        this.categoryName = "";
        this.productName = "";
           this.numAttributes = 1;
        this.attributes = new ArrayList<>();
        
    }

    public CategoryDetails(int categoryDetailID, int categoryID, String productID, String attributeName, String attributeValue, String productName, String categoryName, int numAttributes, List<CategoryDetails> attributes) {
        this.categoryDetailID = categoryDetailID;
        this.categoryID = categoryID;
        this.productID = productID;
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.categoryName = categoryName;
        this.productName = productName;
           this.numAttributes = 0; 
        this.attributes = attributes != null ? attributes : new ArrayList<>();

                
    }

    public int getCategoryDetailID() {
        return categoryDetailID;
    }

    public void setCategoryDetailID(int categoryDetailID) {
        this.categoryDetailID = categoryDetailID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getNumAttributes() {
        return numAttributes;
    }

    public void setNumAttributes(int numAttributes) {
        this.numAttributes = numAttributes;
    }

    public List<CategoryDetails> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CategoryDetails> attributes) {
        this.attributes = attributes;
    }
}
