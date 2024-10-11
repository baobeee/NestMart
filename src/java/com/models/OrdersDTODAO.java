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
public interface OrdersDTODAO {
    public OrdersDTO findByOrderId(int orderID);
     int getTotalOrders();
     int countByKeyword(String keyword);
     List<OrdersDTO> searchByKeyword(String keyword, int page, int pageSize);
     List<OrdersDTO> findPaginated(int page, int pageSize);
      public String findClosestMatch(String keyword);
       public int levenshteinDistance(String a, String b);
       public List<OrdersDTO> findOrdersByCustomerId(int customerId);
}
