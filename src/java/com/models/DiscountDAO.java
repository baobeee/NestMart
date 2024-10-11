/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.models;

import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Acer
 */
public interface DiscountDAO {
    List<Discount> findAll();
    void save(Discount discount, MultipartFile imageFile, ServletContext servletContext);
    void update(Discount discount, MultipartFile imageFile, ServletContext servletContext);
    void deleteById(int discountId);
    Discount findById(int id);
    
    List<Discount> findPaginated(int page, int pageSize);
    List<Discount> searchByKeyword(String keyword, int page, int pageSize);
    int countByKeyword(String keyword);
    int getTotalDiscounts();
    String findClosestMatch(String keyword); 
}
