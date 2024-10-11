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
                    (String) row.get("CategoryName"),
                      0, 
                    new ArrayList<>() 
            );
            categorydetailList.add(p);
        }
        System.out.println(categorydetailList);
        return categorydetailList;
    }

 @Override
public CategoryDetails findById(int categoryDetailID) {
    String query = "SELECT c.CategoryDetailID, c.CategoryID, c.ProductID, c.AttributeName, c.AttributeValue, " +
                   "p.ProductName, ca.CategoryName " +
                   "FROM CategoryDetails c " +
                   "JOIN Products p ON c.ProductID = p.ProductID " +
                   "JOIN Categories ca ON c.CategoryID = ca.CategoryID " +
                   "WHERE c.CategoryDetailID = ?";

    return jdbcTemplate.queryForObject(query, new Object[]{categoryDetailID}, new RowMapper<CategoryDetails>() {
        @Override
        public CategoryDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CategoryDetails(
                    rs.getInt("CategoryDetailID"),
                    rs.getInt("CategoryID"),
                    rs.getString("ProductID"),
                    rs.getString("AttributeName"),
                    rs.getString("AttributeValue"),
                    rs.getString("ProductName"),
                    rs.getString("CategoryName"),
                    0, 
                    new ArrayList<>() 
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
@Override
public void save(CategoryDetails categoryDetails) {
    String query = "INSERT INTO CategoryDetails (CategoryID, ProductID, AttributeName, AttributeValue) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(query, categoryDetails.getCategoryID(), categoryDetails.getProductID(),
            categoryDetails.getAttributeName(), categoryDetails.getAttributeValue());
}
@Override
public void update(CategoryDetails categoryDetails) {
    String query = "UPDATE CategoryDetails SET CategoryID = ?, ProductID = ?, AttributeName = ?, AttributeValue = ? WHERE CategoryDetailID = ?";
    int rowsAffected = jdbcTemplate.update(query, 
        categoryDetails.getCategoryID(), 
        categoryDetails.getProductID(), 
        categoryDetails.getAttributeName(), 
        categoryDetails.getAttributeValue(), 
        categoryDetails.getCategoryDetailID());
    System.out.println("Rows affected: " + rowsAffected);
}


      @Override
    public List<Products> findProductsByCategoryId(int categoryId) {
        String query = "SELECT ProductID, ProductName FROM Products WHERE CategoryID = ?";
        return jdbcTemplate.query(query, new Object[]{categoryId}, new RowMapper<Products>() {
            @Override
            public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
                Products product = new Products();
                product.setProductID(rs.getString("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                return product;
            }
        });
    }
@Override
public int getTotalCategoryDetails() {
    String query = "SELECT COUNT(*) FROM CategoryDetails";
    return jdbcTemplate.queryForObject(query, Integer.class);
}

@Override
public List<CategoryDetails> findPaginated(int page, int pageSize) {
    int offset = (page - 1) * pageSize;
    String query = "SELECT c.CategoryDetailID, c.CategoryID, c.ProductID, c.AttributeName, c.AttributeValue, " +
                   "p.ProductName, ca.CategoryName " +
                   "FROM CategoryDetails c " +
                   "JOIN Products p ON c.ProductID = p.ProductID " +
                   "JOIN Categories ca ON c.CategoryID = ca.CategoryID " +
                   "ORDER BY c.CategoryDetailID " +
                   "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

    return jdbcTemplate.query(query, new Object[]{offset, pageSize}, new RowMapper<CategoryDetails>() {
        @Override
        public CategoryDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CategoryDetails(
                rs.getInt("CategoryDetailID"),
                rs.getInt("CategoryID"),
                rs.getString("ProductID"),
                rs.getString("AttributeName"),
                rs.getString("AttributeValue"),
                rs.getString("ProductName"),
                rs.getString("CategoryName"),
                0, 
                new ArrayList<>() 
            );
        }
    });
}
@Override
public List<CategoryDetails> searchByKeyword(String keyword) {
    // Câu lệnh SQL với collation Latin1_General_CI_AI để tìm kiếm không phân biệt dấu
    String query = "SELECT CD.CategoryDetailID, CD.CategoryID, CD.ProductID, CD.AttributeName, CD.AttributeValue, " +
                   "P.ProductName, CA.CategoryName " +
                   "FROM CategoryDetails CD " +
                   "JOIN Products P ON CD.ProductID = P.ProductID " +
                   "JOIN Categories CA ON CD.CategoryID = CA.CategoryID " +
                   "WHERE P.ProductName COLLATE Latin1_General_CI_AI LIKE ? " +
                   "OR SOUNDEX(P.ProductName COLLATE Latin1_General_CI_AI) = SOUNDEX(?)";

    System.out.println("Searching for keyword: " + keyword);
    return jdbcTemplate.query(query, new Object[]{"%" + keyword + "%", keyword}, new RowMapper<CategoryDetails>() {
        @Override
        public CategoryDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CategoryDetails(
                rs.getInt("CategoryDetailID"),
                rs.getInt("CategoryID"),
                rs.getString("ProductID"),
                rs.getString("AttributeName"),
                rs.getString("AttributeValue"),
                rs.getString("ProductName"),
                rs.getString("CategoryName"),
                0, 
                new ArrayList<>()
            );
        }
    });
}




@Override
public String findClosestMatch(String keyword) {
    String query = "SELECT ProductName FROM Products";
    List<String> allProductNames = jdbcTemplate.queryForList(query, String.class);

    String closestMatch = null;
    int minDistance = Integer.MAX_VALUE;

    for (String productName : allProductNames) {
        // Compare the keyword and product names after removing accents
        String normalizedKeyword = removeAccents(keyword.toLowerCase());
        String normalizedProductName = removeAccents(productName.toLowerCase());
        
        int distance = levenshteinDistance(normalizedKeyword, normalizedProductName);
        
        if (distance < minDistance) {
            minDistance = distance;
            closestMatch = productName;
        }
    }

    // If no valid closest match found, return null
    if (minDistance == Integer.MAX_VALUE || minDistance > 3) {
        return null;
    }

    return closestMatch;
}

// Utility method to remove accents
public String removeAccents(String input) {
    return java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
}



@Override

public int levenshteinDistance(String a, String b) {
    int[][] dp = new int[a.length() + 1][b.length() + 1];

    for (int i = 0; i <= a.length(); i++) {
        for (int j = 0; j <= b.length(); j++) {
            if (i == 0) {
                dp[i][j] = j;
            } else if (j == 0) {
                dp[i][j] = i;
            } else {
                dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
                                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
    }
    return dp[a.length()][b.length()];
}

@Override
public List<AttributeDetail> findByProductId(String productId) {
    String query = "SELECT c.AttributeName, c.AttributeValue FROM CategoryDetails c JOIN Products p ON c.ProductID = p.ProductID WHERE p.ProductID = ?";

    return jdbcTemplate.query(query, new Object[]{productId}, new RowMapper<AttributeDetail>() {
        @Override
        public AttributeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new AttributeDetail(
                rs.getString("AttributeName"),
                rs.getString("AttributeValue")
            );
        }
    });
}
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
