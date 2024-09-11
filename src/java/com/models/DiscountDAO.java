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
    public List<Discount> findAll();

    void save(Discount discount, MultipartFile imageFile, ServletContext servletContext); 

    Discount findById(int id);

    void update(Discount discount, MultipartFile imageFile, ServletContext servletContext);


    void deleteById(int id);
}
