package com.models;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailsDAO {
    void saveOrderDetail(int orderId, String productId, int quantity, BigDecimal unitPrice, BigDecimal totalPrice);
     public List<OrderDetails> findByOrderId(int orderID);
}
