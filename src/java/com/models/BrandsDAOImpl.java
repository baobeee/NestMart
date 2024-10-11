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
    String query = "SELECT b.BrandID, b.BrandName, b.Description, " +
                   "CASE WHEN EXISTS (SELECT 1 FROM Products p WHERE p.BrandID = b.BrandID) " +
                   "THEN 1 ELSE 0 END as HasProducts " +
                   "FROM Brands b";
    List<Brands> brandList = new ArrayList<>();
    List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

    for (Map row : rows) {
        Brands b = new Brands(
            (int) row.get("BrandID"),
            (String) row.get("BrandName"),
            (String) row.get("Description")
        );
        b.setHasProducts(((Number) row.get("HasProducts")).intValue() == 1);
        brandList.add(b);
    }
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
    // Kiểm tra xem brand có sản phẩm liên kết không
    String checkProductsQuery = "SELECT COUNT(*) FROM Products WHERE BrandID = ?";
    int productCount = jdbcTemplate.queryForObject(checkProductsQuery, Integer.class, id);
    
    if (productCount > 0) {
        throw new IllegalStateException("Cannot delete brand with ID " + id + " because it has associated products");
    }
    
    // Nếu không có sản phẩm liên kết, tiến hành xóa brand
    String deleteBrandQuery = "DELETE FROM Brands WHERE BrandID = ?";
    int brandDeleted = jdbcTemplate.update(deleteBrandQuery, id);
    
    if (brandDeleted == 0) {
        throw new IllegalStateException("Brand with ID " + id + " not found");
    }
    
    System.out.println("Brand deleted successfully: " + brandDeleted);
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
    @Override
    public int getTotalBrands() {
        String query = "SELECT COUNT(*) FROM Brands";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
@Override
public List<Brands> findPaginated(int page, int pageSize) {
    String query = "SELECT b.BrandID, b.BrandName, b.Description, " +
                   "CASE WHEN EXISTS (SELECT 1 FROM Products p WHERE p.BrandID = b.BrandID) " +
                   "THEN 1 ELSE 0 END as HasProducts " +
                   "FROM Brands b " +
                   "ORDER BY b.BrandID " +
                   "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    
    int offset = (page - 1) * pageSize;
    
    return jdbcTemplate.query(query, new Object[]{offset, pageSize}, (rs, rowNum) -> {
        Brands brand = new Brands(
            rs.getInt("BrandID"),
            rs.getString("BrandName"),
            rs.getString("Description")
        );
        brand.setHasProducts(rs.getInt("HasProducts") == 1);
        return brand;
    });
}
 @Override
    public List<Brands> searchByKeyword(String keyword) {
        String query = "SELECT BrandID, BrandName, Description FROM Brands WHERE BrandName LIKE ?";
        return jdbcTemplate.query(query, new Object[]{"%" + keyword + "%"}, new RowMapper<Brands>() {
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
    public String findClosestMatch(String keyword) {
        String query = "SELECT BrandName FROM Brands";
        List<String> brandNames = jdbcTemplate.queryForList(query, String.class);

        String closestMatch = null;
        int minDistance = Integer.MAX_VALUE;

        for (String brandName : brandNames) {
            int distance = levenshteinDistance(keyword, brandName);
            if (distance < minDistance) {
                minDistance = distance;
                closestMatch = brandName;
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

}
