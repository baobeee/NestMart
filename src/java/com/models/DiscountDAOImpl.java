package com.models;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
@MultipartConfig
public class DiscountDAOImpl implements DiscountDAO {

    private JdbcTemplate jdbcTemplate;
    public DiscountDAOImpl() {
    }
    @Autowired
    public DiscountDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Discount> findAll() {
        String query = "SELECT DiscountID, DiscountName, Description, StartDate, EndDate, Image FROM Discounts";
        List<Discount> discountList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        for (Map<String, Object> row : rows) {
            Discount discount = new Discount(
                    (int) row.get("DiscountID"),
                    (String) row.get("DiscountName"),
                    (String) row.get("Description"),
                    (Date) row.get("StartDate"),
                    (Date) row.get("EndDate"),
                    (String) row.get("Image")
            );
            discountList.add(discount);
        }
        return discountList;
    }
@Override
public void save(Discount discount, MultipartFile imageFile, ServletContext servletContext) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String formattedStartDate = sdf.format(discount.getStartDate());
    String formattedEndDate = sdf.format(discount.getEndDate());

    String fileName = null;
    if (imageFile != null && !imageFile.isEmpty()) {
        try {
            fileName = Paths.get(imageFile.getOriginalFilename()).getFileName().toString();
            if (fileName == null || fileName.isEmpty()) {
                throw new RuntimeException("File name is not valid.");
            }
 
            String relativePath = "/web/assets/admin/images/uploads/discount/";
            String uploadPath = new File(servletContext.getRealPath("")).getParentFile().getParentFile().getAbsolutePath() + relativePath;

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                boolean dirsCreated = uploadDir.mkdirs();
                if (!dirsCreated) {
                    throw new RuntimeException("Failed to create directories: " + uploadPath);
                }
            }

            String filePath = Paths.get(uploadPath, fileName).toString();
            imageFile.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error when saving file: " + e.getMessage());
        }
    }

    // Fixed query with correct number of parameters
    String query = "INSERT INTO Discounts (DiscountName, Description, StartDate, EndDate, Image) VALUES (?, ?, ?, ?, ?)";

    jdbcTemplate.update(query,
            discount.getDiscountName(),
            discount.getDescription(),
            formattedStartDate,
            formattedEndDate,
            fileName
    );
}
    @Override
    public void update(Discount discount, MultipartFile imageFile, ServletContext servletContext) {
        if (discount.getDiscountName() == null || discount.getDiscountName().isEmpty()) {
            throw new RuntimeException("DiscountName cannot be null");
        }

        // Lấy tên hình ảnh cũ từ cơ sở dữ liệu
        String currentImageName = getCurrentImageName(discount.getDiscountID());
        String fileName = currentImageName;

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                fileName = Paths.get(imageFile.getOriginalFilename()).getFileName().toString();
                if (fileName == null || fileName.isEmpty()) {
                    throw new RuntimeException("File name is not valid.");
                }

                String relativePath = "/web/assets/admin/images/uploads/discount/";
    String uploadPath = new File(servletContext.getRealPath("")).getParentFile().getParentFile().getAbsolutePath() + relativePath;

                // Đảm bảo thư mục tồn tại
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    if (!uploadDir.mkdirs()) {
                        throw new RuntimeException("Cannot create folder: " + uploadPath);
                    }
                }

                // Đường dẫn đầy đủ của file
                String filePath = Paths.get(uploadPath, fileName).toString();

                // Xóa tệp cũ nếu tồn tại và tên file đã thay đổi
                File oldFile = new File(Paths.get(uploadPath, currentImageName).toString());
                if (oldFile.exists() && !oldFile.getName().equals(fileName)) {
                    if (!oldFile.delete()) {
                        System.out.println("Cannot delete old file: " + oldFile.getAbsolutePath());
                    }
                }

                // Lưu file ảnh vào thư mục
                imageFile.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error when saving file: " + e.getMessage());
            }
        }

        // Cập nhật discount trong cơ sở dữ liệu với tên file ảnh (cũ hoặc mới)
        String query = "UPDATE Discounts SET DiscountName = ?, Description = ?, StartDate = ?, EndDate = ?, Image = ? WHERE DiscountID = ?";
        jdbcTemplate.update(query,
                discount.getDiscountName(),
                discount.getDescription(),
                discount.getStartDate(),
                discount.getEndDate(),
                fileName, // sử dụng fileName đã được cập nhật hoặc giữ nguyên
                discount.getDiscountID()
        );

        // Cập nhật giá sản phẩm với discountValue mới
       
    }

    // Phương thức để lấy tên hình ảnh hiện tại từ cơ sở dữ liệu
    private String getCurrentImageName(int discountID) {
        String query = "SELECT Image FROM Discounts WHERE DiscountID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{discountID}, String.class);
    }

    private void updateProductPrices(int discountID, int discountValue) {
        String productQuery = "SELECT p.ProductID, p.UnitPrice FROM Products p JOIN Offers o ON p.ProductID = o.ProductID WHERE o.DiscountID = ?";
        List<Map<String, Object>> products = jdbcTemplate.queryForList(productQuery, discountID);

        BigDecimal discountPercentage = new BigDecimal(discountValue).divide(new BigDecimal(100));

        for (Map<String, Object> product : products) {
            String productId = (String) product.get("ProductID");
            BigDecimal unitPrice = (BigDecimal) product.get("UnitPrice");

            BigDecimal discountedPrice = unitPrice.multiply(discountPercentage);

            String updatePriceQuery = "UPDATE Products SET Discount = ? WHERE ProductID = ?";
            jdbcTemplate.update(updatePriceQuery, discountedPrice, productId);
        }
    }

    @Override
    public void deleteById(int discountId) {
        String updateProductsQuery = "UPDATE Products SET Discount = 0 WHERE ProductID IN (SELECT ProductID FROM Offers WHERE DiscountID = ?)";
        jdbcTemplate.update(updateProductsQuery, discountId);

        String deleteOffersQuery = "DELETE FROM Offers WHERE DiscountID = ?";
        jdbcTemplate.update(deleteOffersQuery, discountId);

        String deleteDiscountQuery = "DELETE FROM Discounts WHERE DiscountID = ?";
        jdbcTemplate.update(deleteDiscountQuery, discountId);
    }

    @Override
    public Discount findById(int id) {
        String query = "SELECT * FROM Discounts WHERE DiscountID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Discount>() {
            @Override
            public Discount mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Discount(
                        rs.getInt("DiscountID"),
                        rs.getString("DiscountName"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("Image")
                );
            }
        });
    }


    @Override
    public List<Discount> findPaginated(int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page number or page size");
        }

        String query = "SELECT DiscountID, DiscountName, Description, StartDate, EndDate, Image "
                + "FROM Discounts "
                + "ORDER BY DiscountID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{offset, pageSize}, new RowMapper<Discount>() {
            @Override
            public Discount mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Discount(
                        rs.getInt("DiscountID"),
                        rs.getString("DiscountName"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("Image")
                );
            }
        });
    }

    @Override
    public List<Discount> searchByKeyword(String keyword, int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page number or page size");
        }

        String query = "SELECT DiscountID, DiscountName, Description, StartDate, EndDate, Image "
                + "FROM Discounts "
                + "WHERE DiscountName LIKE ? "
                + "ORDER BY DiscountID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String searchKeyword = "%" + keyword + "%";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{searchKeyword, offset, pageSize}, new RowMapper<Discount>() {
            @Override
            public Discount mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Discount(
                        rs.getInt("DiscountID"),
                        rs.getString("DiscountName"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("Image")
                );
            }
        });
    }

    @Override
    public int countByKeyword(String keyword) {
        String query = "SELECT COUNT(*) FROM Discounts WHERE DiscountName LIKE ?";
        return jdbcTemplate.queryForObject(query, new Object[]{"%" + keyword + "%"}, Integer.class);
    }

    @Override
    public int getTotalDiscounts() {
        String query = "SELECT COUNT(*) FROM Discounts";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

 @Override
public String findClosestMatch(String keyword) {
    if (keyword == null || keyword.trim().isEmpty()) {
        throw new IllegalArgumentException("Keyword must not be null or empty");
    }
    
    keyword = keyword.toLowerCase().trim();
    
    String query = "SELECT DiscountName FROM Discounts";
    List<String> discountNames = jdbcTemplate.queryForList(query, String.class);
    
    String closestMatch = null;
    double bestScore = Double.MAX_VALUE;
    
    for (String name : discountNames) {
        if (name != null) {
            String lowerName = name.toLowerCase();
            double score;
            
            // Ưu tiên cao cho các kết quả bắt đầu bằng từ khóa
            if (lowerName.startsWith(keyword)) {
                score = 0.1 * (lowerName.length() - keyword.length());
            } else {
                // Sử dụng khoảng cách Levenshtein có trọng số
                score = weightedLevenshteinDistance(keyword, lowerName);
                
                // Chuẩn hóa điểm số dựa trên độ dài của chuỗi
                score = score / Math.max(keyword.length(), lowerName.length());
            }
            
            if (score < bestScore) {
                bestScore = score;
                closestMatch = name;
            }
        }
    }
    
    return closestMatch;
}

private double weightedLevenshteinDistance(String s1, String s2) {
    int[][] dp = new int[s1.length() + 1][s2.length() + 1];
    
    for (int i = 0; i <= s1.length(); i++) {
        for (int j = 0; j <= s2.length(); j++) {
            if (i == 0) {
                dp[i][j] = j;
            } else if (j == 0) {
                dp[i][j] = i;
            } else {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 2;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, 
                                             dp[i][j - 1] + 1), 
                                    dp[i - 1][j - 1] + cost);
            }
        }
    }
    
    return dp[s1.length()][s2.length()];
}

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
