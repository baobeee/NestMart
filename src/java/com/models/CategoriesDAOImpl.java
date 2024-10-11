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
    String query = "SELECT c.CategoryID, c.CategoryName, c.Description, " +
                   "CASE WHEN EXISTS (SELECT 1 FROM Products p WHERE p.CategoryID = c.CategoryID) " +
                   "THEN 1 ELSE 0 END as HasProducts " +
                   "FROM Categories c";
    return jdbcTemplate.query(query, new RowMapper<Categories>() {
        @Override
        public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categories category = new Categories(
                rs.getInt("CategoryID"),
                rs.getString("CategoryName"),
                rs.getString("Description")
            );
            category.setHasProducts(rs.getInt("HasProducts") == 1);
            return category;
        }
    });
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
    // Kiểm tra xem category có sản phẩm liên kết không
    String checkProductsQuery = "SELECT COUNT(*) FROM Products WHERE CategoryID = ?";
    int productCount = jdbcTemplate.queryForObject(checkProductsQuery, Integer.class, id);
    
    if (productCount > 0) {
        throw new IllegalStateException("Cannot delete category with ID " + id + " because it has associated products");
    }
    
    // Nếu không có sản phẩm liên kết, tiến hành xóa category
    String deleteCategoryQuery = "DELETE FROM Categories WHERE CategoryID = ?";
    int categoryDeleted = jdbcTemplate.update(deleteCategoryQuery, id);
    
    if (categoryDeleted == 0) {
        throw new IllegalStateException("Category with ID " + id + " not found");
    }
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

  @Override
public List<Categories> findPaginated(int page, int pageSize) {
    String query = "SELECT c.CategoryID, c.CategoryName, c.Description, " +
                   "CASE WHEN EXISTS (SELECT 1 FROM Products p WHERE p.CategoryID = c.CategoryID) " +
                   "THEN 1 ELSE 0 END as HasProducts " +
                   "FROM Categories c " +
                   "ORDER BY c.CategoryID " +
                   "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    
    int offset = (page - 1) * pageSize;
    
    return jdbcTemplate.query(query, new Object[]{offset, pageSize}, new RowMapper<Categories>() {
        @Override
        public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categories category = new Categories(
                rs.getInt("CategoryID"),
                rs.getString("CategoryName"),
                rs.getString("Description")
            );
            category.setHasProducts(rs.getInt("HasProducts") == 1);
            return category;
        }
    });
}
 @Override
   
public List<Categories> searchByKeyword(String keyword) {
    System.out.println("Searching for keyword: " + keyword);
    String query = "SELECT CategoryID, CategoryName, Description FROM Categories WHERE CategoryName LIKE ?";
    List<Categories> results = jdbcTemplate.query(query, new Object[]{"%" + keyword + "%"}, new RowMapper<Categories>() {
        @Override
        public Categories mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Categories(
                rs.getInt("CategoryID"),
                rs.getString("CategoryName"),
                rs.getString("Description")
            );
        }
    });
    System.out.println("Found " + results.size() + " results.");
    return results;
}


   @Override
public String findClosestMatch(String keyword) {
    System.out.println("Finding closest match for keyword: " + keyword);
    String query = "SELECT CategoryName FROM Categories";
    List<String> categoryNames = jdbcTemplate.queryForList(query, String.class);

    String closestMatch = null;
    int minDistance = Integer.MAX_VALUE;

    for (String categoryName : categoryNames) {
        int distance = levenshteinDistance(keyword, categoryName);
        System.out.println("Comparing with: " + categoryName + " - Distance: " + distance);
        if (distance < minDistance) {
            minDistance = distance;
            closestMatch = categoryName;
        }
    }

    System.out.println("Closest match found: " + closestMatch);
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
                dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
                                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
            }
        }
    }
    System.out.println("Levenshtein distance between '" + a + "' and '" + b + "' is: " + dp[a.length()][b.length()]);
    return dp[a.length()][b.length()];
}


    @Override
    public int getTotalCategories() {
        String query = "SELECT COUNT(*) FROM Categories";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
