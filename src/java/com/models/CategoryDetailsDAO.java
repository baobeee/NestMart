/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public interface CategoryDetailsDAO {
      List<CategoryDetails> findAll();
    CategoryDetails findById(int categoryDetailID);
 
    void deleteById(int categoryDetailID);
        List<CategoryDetails> findByCategoryId(int categoryId);


}
