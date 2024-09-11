package com.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class InventoryDAOImpl implements InventoryDAO{
    private JdbcTemplate jdbcTemplate;

    public InventoryDAOImpl() {
    }
    
    public InventoryDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Inventory> findAllInventory() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        String query = "SELECT i.ProductID, i.Quantity, i.LastUpdated, p.ProductName FROM Inventory i INNER JOIN Products p ON i.productID = p.productID";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Inventory inventory = new Inventory();
            inventory.setProductID(rs.getString("ProductID"));
            inventory.setQuantity(rs.getInt("Quantity"));
            inventory.setProductName(rs.getString("ProductName"));
            Timestamp timestamp = rs.getTimestamp("LastUpdated");
            LocalDateTime dateTime = timestamp.toLocalDateTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = dateTime.format(formatter);
            inventory.setFormattedDateTime(formattedDateTime);
            inventory.setLastUpdated(dateTime);
            return inventory;
        });
    }
    
    @Override
    public List<Products> findAllProducts() {
        String query = "SELECT ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, Image, DateAdded, Discount, AverageRating FROM Products";
        List<Products> productList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); 

        for (Map<String, Object> row : rows) {
            Products p = new Products(
                    (String) row.get("ProductID"), 
                    (Integer) row.get("CategoryID"),
                    (Integer) row.get("BrandID"),
                    (String) row.get("ProductName"),
                    (String) row.get("ProductDescription"),
                    (BigDecimal) row.get("UnitPrice"),
                    (String) row.get("Image"),
                    (java.util.Date) row.get("DateAdded"),
               (BigDecimal) row.get("Discount"), 
                    ((Number) row.get("AverageRating")).byteValue()  
            );
            productList.add(p);
        }
        System.out.println(productList); 
        return productList;
    }
    
    @Override
    public void add(Inventory inventory) {
        String query = "INSERT INTO Inventory VALUES (?, ?, ?)";
        jdbcTemplate.update(query, inventory.getProductID(), inventory.getQuantity(), Timestamp.valueOf(inventory.getLastUpdated()));
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM Inventory WHERE ProductID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Inventory get(String id) {
        String sql = "SELECT i.ProductID, i.Quantity, i.LastUpdated, p.ProductName FROM Inventory i INNER JOIN Products p ON i.productID = p.productID WHERE i.productID = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Inventory inventory = new Inventory();
                inventory.setProductID(rs.getString("ProductID"));
                inventory.setQuantity(rs.getInt("Quantity"));
                Timestamp timestamp = rs.getTimestamp("LastUpdated");
                inventory.setLastUpdated(timestamp.toLocalDateTime());
                inventory.setProductName(rs.getString("ProductName"));
                return inventory;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(Inventory inventory) {
        String query = "UPDATE Inventory SET Quantity = ?, LastUpdated = ? WHERE ProductID = ?";
        jdbcTemplate.update(query, inventory.getQuantity(), Timestamp.valueOf(inventory.getLastUpdated()), inventory.getProductID());
    }

    @Override
    public List<Inventory> findInventory(String keyword) {
        String query = "SELECT i.ProductID, i.Quantity, i.LastUpdated, p.ProductName FROM Inventory i INNER JOIN Products p ON i.productID = p.productID WHERE p.ProductName LIKE ?";
        keyword = "%" + keyword + "%";
        return jdbcTemplate.query(query, new Object[]{keyword}, BeanPropertyRowMapper.newInstance(Inventory.class));
    }
    
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
