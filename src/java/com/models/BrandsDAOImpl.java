package com.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BrandsDAOImpl implements BrandsDAO {

    private JdbcTemplate jdbcTemplate;

    public BrandsDAOImpl() {
    }

    public BrandsDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public List<Brands> findAll() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT BrandID, BrandName, Description FROM Brands";
        List<Brands> brandList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); // Kiểm tra số lượng bản ghi

        for (Map row : rows) {
            Brands b = new Brands((int) row.get("BrandID"),
                    (String) row.get("BrandName"),
                    (String) row.get("Description"));
            brandList.add(b);
        }
        System.out.println(brandList); // In danh sách thương hiệu
        return brandList;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Brands brand) {
        String query = "INSERT INTO Brands (BrandName, Description) VALUES (?, ?)";
        jdbcTemplate.update(query, brand.getBrandName(), brand.getDescription());
    }

    @Override
    public void update(Brands brand) {
        System.out.println("Executing update for brand: " + brand.getBrandID());
        String query = "UPDATE Brands SET BrandName = ?, Description = ? WHERE BrandID = ?";
        int rowsAffected = jdbcTemplate.update(query, brand.getBrandName(), brand.getDescription(), brand.getBrandID());
        System.out.println("Rows affected: " + rowsAffected);
    }

   @Override
public void deleteById(int id) {
    // First, delete all products associated with the Brand
    String deleteProductsQuery = "DELETE FROM Products WHERE BrandID = ?";
    int productsDeleted = jdbcTemplate.update(deleteProductsQuery, id);
    System.out.println("Products deleted: " + productsDeleted);

    // Then, delete the brand itself
    String deleteBrandQuery = "DELETE FROM Brands WHERE BrandID = ?";
    int brandDeleted = jdbcTemplate.update(deleteBrandQuery, id);
    System.out.println("Brand deleted: " + brandDeleted);
}

    @Override
    public Brands findById(int id) {
        String query = "SELECT BrandID, BrandName, Description FROM Brands WHERE BrandID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Brands>() {
            @Override
            public Brands mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Brands(
                        rs.getInt("BrandID"),
                        rs.getString("BrandName"),
                        rs.getString("Description")
                );
            }
        });
    }
     @Override
    public String getBrandNameById(int id) {
        String query = "SELECT BrandName FROM Brands WHERE BrandID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, String.class);
    }
}
