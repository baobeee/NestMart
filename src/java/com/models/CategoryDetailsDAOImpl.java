package com.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CategoryDetailsDAOImpl implements CategoryDetailsDAO {

    private JdbcTemplate jdbcTemplate;

    public CategoryDetailsDAOImpl() {
    }

    public CategoryDetailsDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

      
    @Override
    public List<CategoryDetails> findAll() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT c.CategoryDetailID, c.CategoryID, c.ProductID, c.AttributeName, c.AttributeValue, p.ProductName, ca.CategoryName FROM CategoryDetails c join Products p on c.productID = p.productID join Categories ca on c.categoryID = ca.categoryID ";
 List<CategoryDetails> categorydetailList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); 

        for (Map<String, Object> row : rows) {
            CategoryDetails p = new CategoryDetails(
                    (Integer) row.get("CategoryDetailID"),
                    (Integer) row.get("CategoryID"),
                    (String) row.get("ProductID"),
                    (String) row.get("AttributeName"),
                    (String) row.get("AttributeValue"),
                    (String) row.get("ProductName"), 
                    (String) row.get("CategoryName")
            );
            categorydetailList.add(p);
        }
        System.out.println(categorydetailList); // In danh sách sản phẩm
        return categorydetailList;
    }

    @Override
    public CategoryDetails findById(int categoryDetailID) {
        String query = "SELECT * FROM CategoryDetails WHERE CategoryDetailID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{categoryDetailID}, new RowMapper<CategoryDetails>() {
            @Override
            public CategoryDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CategoryDetails(
                        rs.getInt("CategoryDetailID"),
                        rs.getInt("CategoryID"),
                        rs.getString("ProductID"),
                        rs.getString("AttributeName"),
                        rs.getString("AttributeValue"),
                        rs.getString("AttributeValue"),
                        rs.getString("AttributeValue")
                );
            }
        });
    }

    @Override
    public void deleteById(int categoryDetailID) {
        String query = "DELETE FROM CategoryDetails WHERE CategoryDetailID = ?";
        jdbcTemplate.update(query, categoryDetailID);
    }

    @Override
    public List<CategoryDetails> findByCategoryId(int categoryId) {
        String query = "SELECT CD.CategoryDetailID, CD.CategoryID, CD.ProductID, CD.AttributeName, CD.AttributeValue "
                + "FROM CategoryDetails CD "
                + "WHERE CD.CategoryID = ?";
        return jdbcTemplate.query(query, new Object[]{categoryId}, new BeanPropertyRowMapper<>(CategoryDetails.class));
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
