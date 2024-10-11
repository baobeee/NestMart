/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Acer
 */
public class OrdersDTODAOImpl implements OrdersDTODAO {

    private JdbcTemplate jdbcTemplate;

    @Override
    public OrdersDTO findByOrderId(int orderID) {
        String query = "SELECT OrderID, CustomerID, Name, Phone, ShippingAddress, PaymentMethod, Notes, "
                + "TotalAmount, OrderDate, OrderStatus, ShipperID "
                + "FROM Orders WHERE OrderID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{orderID}, (rs, rowNum)
                -> new OrdersDTO(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("ShippingAddress"),
                        rs.getString("PaymentMethod"),
                        rs.getString("Notes"),
                        rs.getBigDecimal("TotalAmount"),
                        rs.getDate("OrderDate"),
                        rs.getString("OrderStatus"),
                        rs.getInt("ShipperID")
                )
        );
    }

    @Override
    public List<OrdersDTO> findPaginated(int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page number or page size");
        }

        String query = "SELECT OrderID, CustomerID, Name, Phone, ShippingAddress, PaymentMethod, Notes, TotalAmount, OrderDate, OrderStatus, ShipperID "
                + "FROM Orders "
                + "ORDER BY OrderID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{offset, pageSize}, new RowMapper<OrdersDTO>() {
            @Override
            public OrdersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new OrdersDTO(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("ShippingAddress"),
                        rs.getString("PaymentMethod"),
                        rs.getString("Notes"),
                        rs.getBigDecimal("TotalAmount"),
                        rs.getDate("OrderDate"),
                        rs.getString("OrderStatus"),
                        rs.getInt("ShipperID")
                );
            }
        });
    }

    @Override
    public List<OrdersDTO> searchByKeyword(String keyword, int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page number or page size");
        }

        String query = "SELECT OrderID, CustomerID, Name, Phone, ShippingAddress, PaymentMethod, Notes, TotalAmount, OrderDate, OrderStatus, ShipperID "
                + "FROM Orders "
                + "WHERE Name LIKE ? OR CAST(OrderID AS VARCHAR) LIKE ? OR Phone LIKE ? "
                + "ORDER BY OrderID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String searchKeyword = "%" + keyword + "%";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{searchKeyword, searchKeyword, searchKeyword, offset, pageSize}, new RowMapper<OrdersDTO>() {
            @Override
            public OrdersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new OrdersDTO(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Phone"),
                        rs.getString("ShippingAddress"),
                        rs.getString("PaymentMethod"),
                        rs.getString("Notes"),
                        rs.getBigDecimal("TotalAmount"),
                        rs.getDate("OrderDate"),
                        rs.getString("OrderStatus"),
                        rs.getInt("ShipperID")
                );
            }
        });
    }
@Override
public List<OrdersDTO> findOrdersByCustomerId(int customerId) {
    String query = "SELECT OrderID, CustomerID, Name, Phone, ShippingAddress, PaymentMethod, Notes, TotalAmount, OrderDate, OrderStatus, ShipperID " +
                   "FROM Orders " +
                   "WHERE CustomerID = ? " +
                   "ORDER BY OrderID";

    return jdbcTemplate.query(query, new Object[]{customerId}, new RowMapper<OrdersDTO>() {
        @Override
        public OrdersDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new OrdersDTO(
                rs.getInt("OrderID"),
                rs.getInt("CustomerID"),
                rs.getString("Name"),
                rs.getString("Phone"),
                rs.getString("ShippingAddress"),
                rs.getString("PaymentMethod"),
                rs.getString("Notes"),
                rs.getBigDecimal("TotalAmount"),
                rs.getDate("OrderDate"),
                rs.getString("OrderStatus"),
                rs.getInt("ShipperID")
            );
        }
    });
}

    @Override
    public String findClosestMatch(String keyword) {
        if (keyword == null) {
            throw new IllegalArgumentException("Keyword must not be null");
        }

        String query = "SELECT Name FROM Orders";
        List<String> brandNames = jdbcTemplate.queryForList(query, String.class);

        String closestMatch = null;
        int minDistance = Integer.MAX_VALUE;

        for (String brandName : brandNames) {
            if (brandName != null) {
                int distance = levenshteinDistance(keyword, brandName);
                if (distance < minDistance) {
                    minDistance = distance;
                    closestMatch = brandName;
                }
            }
        }

        return closestMatch;
    }

    @Override
    public int levenshteinDistance(String a, String b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
                }
            }
        }

        return dp[a.length()][b.length()];
    }

    @Override
    public int getTotalOrders() {
        String query = "SELECT COUNT(*) FROM Orders";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    @Override
    public int countByKeyword(String keyword) {
        String query = "SELECT COUNT(*) FROM Orders WHERE Name LIKE ?";
        return jdbcTemplate.queryForObject(query, new Object[]{"%" + keyword + "%"}, Integer.class);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
