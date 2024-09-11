package com.models;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
public interface ProductsDAO {

    List<Products> findAll();

    void save(Products product, MultipartFile imageFile, ServletContext servletContext);

    Products findById(String id);  // Thay đổi từ int thành String

    void update(Products product, MultipartFile imageFile, ServletContext servletContext);

    void deleteById(String id);  // Thay đổi từ int thành String
        List<Products> findByCategoryId(int categoryId);
    Map<String, String> getProductNames(List<String> productIds);

}
