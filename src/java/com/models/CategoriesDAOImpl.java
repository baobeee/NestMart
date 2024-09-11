package com.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CategoriesDAOImpl implements CategoriesDAO {

    private JdbcTemplate jdbcTemplate;

    public CategoriesDAOImpl() {
    }

    public CategoriesDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public List<Categories> findAll() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT CategoryID, CategoryName, Description FROM Categories";
        List<Categories> categoryList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); // Kiểm tra số lượng bản ghi

        for (Map row : rows) {
            Categories c = new Categories((int) row.get("CategoryID"),
                    (String) row.get("CategoryName"),
                    (String) row.get("Description"));
            categoryList.add(c);
        }
        System.out.println(categoryList); // In danh sách danh mục
        return categoryList;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Categories category) {
        String query = "INSERT INTO Categories (CategoryName, Description) VALUES (?, ?)";
        jdbcTemplate.update(query, category.getCategoryName(), category.getDescription());
    }

    @Override
    public void update(Categories category) {
        System.out.println("Executing update for category: " + category.getCategoryID());
        String query = "UPDATE Categories SET CategoryName = ?, Description = ? WHERE CategoryID = ?";
        int rowsAffected = jdbcTemplate.update(query, category.getCategoryName(), category.getDescription(), category.getCategoryID());
        System.out.println("Rows affected: " + rowsAffected);
    }

    @Override
    public void deleteById(int id) {
        // First, delete all products associated with the Category
        String deleteProductsQuery = "DELETE FROM Products WHERE CategoryID = ?";
        int productsDeleted = jdbcTemplate.update(deleteProductsQuery, id);
        System.out.println("Products deleted: " + productsDeleted);

        // Then, delete the category itself
        String deleteCategoryQuery = "DELETE FROM Categories WHERE CategoryID = ?";
        int categoryDeleted = jdbcTemplate.update(deleteCategoryQuery, id);
        System.out.println("Category deleted: " + categoryDeleted);
    }

    @Override
    public Categories findById(int id) {
        String query = "SELECT CategoryID, CategoryName, Description FROM Categories WHERE CategoryID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Categories>() {
            @Override
            public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Categories(
                        rs.getInt("CategoryID"),
                        rs.getString("CategoryName"),
                        rs.getString("Description")
                );
            }
        });
    }

    @Override
    public String getCategoryNameById(int id) {
        String query = "SELECT CategoryName FROM Categories WHERE CategoryID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, String.class);
    }

    @Override
    public Map<Integer, String> getCategoryNames(List<Integer> categoryIds) {
        if (categoryIds.isEmpty()) {
            return Collections.emptyMap();
        }

        // Tạo chuỗi placeholders cho câu truy vấn SQL
        String placeholders = String.join(",", Collections.nCopies(categoryIds.size(), "?"));
        String query = "SELECT CategoryID, CategoryName FROM Categories WHERE CategoryID IN (" + placeholders + ")";

        return jdbcTemplate.query(query, categoryIds.toArray(), rs -> {
            Map<Integer, String> categoryNames = new HashMap<>();
            while (rs.next()) {
                categoryNames.put(rs.getInt("CategoryID"), rs.getString("CategoryName"));
            }
            return categoryNames;
        });
    }
}
