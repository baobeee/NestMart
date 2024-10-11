package com.models;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private JdbcTemplate jdbcTemplate;

    
    @Override
    public void saveOrderDetail(int orderId, String productId, int quantity, BigDecimal unitPrice, BigDecimal totalPrice) {
        String sql = "INSERT INTO OrderDetails (OrderID, ProductID, Quantity, UnitPrice, TotalPrice) " +
                     "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, orderId, productId, quantity, unitPrice, totalPrice);
    }
      @Override
    public List<OrderDetails> findByOrderId(int orderID) {
        String sql = "SELECT * FROM OrderDetails WHERE OrderID = ?";
        return jdbcTemplate.query(sql, new Object[]{orderID}, new BeanPropertyRowMapper<>(OrderDetails.class));
    }
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}