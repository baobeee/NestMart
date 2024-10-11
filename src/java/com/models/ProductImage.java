package com.models;

public class ProductImage {
    private String productImageID;
    private String productID;
    private String images;
    
    public ProductImage() {}

    public ProductImage(String productImageID, String productID, String images) {
        this.productImageID = productImageID;
        this.productID = productID;
        this.images = images;
    }

    public String getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(String productImageID) {
        this.productImageID = productImageID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

  public String getImages() {
    return images;
}

   public void setImages(String images) { 
        this.images = images;
    }
}
