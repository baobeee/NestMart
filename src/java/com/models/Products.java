package com.models;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

public class Products {

      @NotBlank(message = "Product ID is required.")
    private String productID;

    @NotNull(message = "Category ID is required.")
    private int categoryID;

    @NotNull(message = "Brand ID is required.")
    private int brandID;

    @NotBlank(message = "Product Name is required.")
    private String productName;

    private String productDescription;

    @NotNull(message = "Unit Price is required.")
    @Min(value = 1, message = "Unit Price must be positive.")
    private BigDecimal unitPrice;

    private String image;

    @NotNull(message = "Date Added is required.")
    private Date dateAdded;

    @NotNull(message = "Discount is required.")
    private BigDecimal discount;

    private Byte averageRating;


    public Products() {
        this.productID = "";
        this.categoryID = 0;
        this.brandID = 0;
        this.productName = "";
        this.productDescription = "";
        this.unitPrice = BigDecimal.ZERO;
        this.image = "";
        this.dateAdded = new Date();
        this.discount = BigDecimal.ZERO;
        this.averageRating = null; // Khởi tạo với null nếu không có giá trị
    }

    public Products(String productID, int categoryID, int brandID, String productName, String productDescription,
            BigDecimal unitPrice, String image, Date dateAdded, BigDecimal discount, Byte averageRating) {
        this.productID = productID;
        this.categoryID = categoryID;
        this.brandID = brandID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
        this.image = image;
        this.dateAdded = dateAdded;
        this.discount = discount;
        this.averageRating = averageRating;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Byte getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Byte averageRating) {
        this.averageRating = averageRating;
    }
}
