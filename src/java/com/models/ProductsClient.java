package com.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class ProductsClient {

    @NotBlank(message = "Product ID is required.")
    private String productID;

    @NotNull(message = "Category ID is required.")
    private int categoryID;

    @NotNull(message = "Brand ID is required.")
    private int brandID;

    @NotBlank(message = "Product Name is required.")
    private String productName;

    private String productDescription;

    @NotNull(message = "Unit Price cannot be null.")
    @DecimalMin(value = "0.01", message = "Unit Price must be greater than 0.")
    @Digits(integer = 10, fraction = 2, message = "Unit Price is invalid.")
    private BigDecimal unitPrice;

    private List<ProductImage> images; // Danh sách hình ảnh

    @NotNull(message = "Date Added is required.")
    private Date dateAdded;

    @NotNull(message = "Discount is required.")
    @DecimalMin(value = "0.00", message = "Discount must be greater than or equal to 0.")
    @Digits(integer = 10, fraction = 2, message = "Discount is invalid.")
    private BigDecimal discount;

    @Digits(integer = 1, fraction = 1, message = "Average Rating must be between 0.0 and 5.0.")
    private BigDecimal averageRating;
    private int totalQuantitySold;

    // Default Constructor
    public ProductsClient() {
        this.productID = "";
        this.categoryID = 0;
        this.brandID = 0;
        this.productName = "";
        this.productDescription = "";
        this.unitPrice = BigDecimal.ZERO;
        this.images = new ArrayList<>();
        this.dateAdded = new Date();
        this.discount = BigDecimal.ZERO;
        this.averageRating = null; 
        
    }

    // Parameterized Constructor
    public ProductsClient(String productID, int categoryID, int brandID, String productName, String productDescription,
            BigDecimal unitPrice, List<ProductImage> images, Date dateAdded, BigDecimal discount, BigDecimal averageRating, int totalQuantitySold) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.brandID = brandID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
        this.images = images; // Danh sách hình ảnh
        this.dateAdded = dateAdded;
        this.discount = discount;
        this.averageRating = averageRating;
        this.totalQuantitySold = totalQuantitySold;
    }

    // Getters and Setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalQuantitySold() {
        return totalQuantitySold;
    }

    public void setTotalQuantitySold(int TotalQuantitySold) {
        this.totalQuantitySold = TotalQuantitySold;
    }
}
