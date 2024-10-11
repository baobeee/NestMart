/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.models;

import java.util.List;

/**
 *
 * @author Acer
 */
public interface DiscountClientDAO {

    public List<DiscountClient> getAllDiscountsWithProducts();

public List<ProductsClient> findProductsByDiscount(int discountID, int page, int pageSize);
 public int countProductsByDiscount(int discountID) ;
}
