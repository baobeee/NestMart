/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Acer
 */
public class OrderDetailsDTODAOImpl implements OrderDetailsDTODAO {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<OrderDetailsDTO> findByOrderId(int orderID) {
        String sql = "SELECT od.ProductID, od.Quantity, od.UnitPrice, od.TotalPrice, p.ProductName, pi.Image\n"
                + "FROM OrderDetails od\n"
                + "JOIN Products p ON od.ProductID = p.ProductID\n"
                + "JOIN ProductImage pi ON p.ProductID = pi.ProductID\n"
                + "WHERE pi.ProductImageID = (\n"
                + "    SELECT MIN(pi2.ProductImageID)\n"
                + "    FROM ProductImage pi2\n"
                + "    WHERE pi2.ProductID = p.ProductID\n"
                + ")\n"
                + "AND od.OrderID = ?;";

        return jdbcTemplate.query(sql, new Object[]{orderID}, (rs, rowNum) -> {
            OrderDetailsDTO dto = new OrderDetailsDTO();
            dto.setProductID(rs.getString("ProductID"));
            dto.setQuantity(rs.getInt("Quantity"));
            dto.setUnitPrice(rs.getDouble("UnitPrice"));
            dto.setTotalPrice(rs.getDouble("TotalPrice"));
            dto.setProductName(rs.getString("ProductName"));
            dto.setImage(rs.getString("Image"));
            return dto;
        });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
