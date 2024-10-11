package com.models;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.web.multipart.MultipartFile;

public interface ProductsDAO {

   
    List<Products> findAll();

    
   void save(Products product, List<MultipartFile> imageFiles, ServletContext servletContext);

    
    Products findById(String id); 

   
   void update(Products product, List<MultipartFile> imageFiles, List<String> imagesToDelete, ServletContext servletContext);


    void deleteById(String id); 

   
    int getTotalProducts();

  
    List<Products> findPaginated(int page, int pageSize);

   
    List<Products> findByCategoryId(int categoryId);

   
    Map<String, String> getProductNames(List<String> productIds);
   
    boolean isProductSold(String productId);

    
    List<Products> searchByKeyword(String keyword);

    String findClosestMatch(String keyword);
           int levenshteinDistance(String a, String b);

}
