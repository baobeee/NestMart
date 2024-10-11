package com.models;

import java.math.BigDecimal;
import java.util.List;

public interface OrdersDAO {

    public List<Orders> findAllOrders(int customerID);

    public Integer getFeedback(String productID, int customerID);

    public List<Orders> getOrdersByStatus(String status, int customerID);

    public Orders get(String id);

    public void update(Orders order);

    List<Orders> getOrdersByShipper(int shipperID);

    void updateOrder(int orderID, String newStatus, String paymentMethod);

    List<Orders> searchAndFilterOrders(int shipperID, String orderIdQuery, String searchQuery, String status);
    
    public int saveOrder(int customerId, String shippingAddress, String notes, String paymentMethod,
            String name, String phone, BigDecimal totalAmount);

    public void updateShipperAndStatus(int orderID, int shipperID);

    public List<Orders> findAll();
    
    public void updateCancalledStatus(int orderID);
}
