package com.models;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OffersDAOImpl implements OffersDAO {

    private JdbcTemplate jdbcTemplate;

    public OffersDAOImpl() {
    }

    public OffersDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public List<Offers> findAll() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT o.offerID, o.productID, d.discountID, o.offerName, o.description, "
                + " p.productName, d.discountName, o.discountType, o.discountValue "
                + "FROM Offers o "
                + "JOIN Products p ON o.productID = p.productID "
                + "JOIN Discounts d ON o.discountID = d.discountID";
        List<Offers> offerList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); // Kiểm tra số lượng bản ghi

        for (Map<String, Object> row : rows) {
            Offers c = new Offers(
                    (int) row.get("OfferID"),
                    (String) row.get("ProductID"),
                    (int) row.get("DiscountID"),
                    (String) row.get("OfferName"),
                    (String) row.get("Description"),
                    (String) row.get("ProductName"),
                    (String) row.get("DiscountName"),
                    (String) row.get("DiscountType"),
                    ((Number) row.get("DiscountValue")).intValue()
            );
            offerList.add(c);
        }
        System.out.println(offerList); // In danh sách danh mục
        return offerList;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Offers offers) {
        // Kiểm tra nếu đã có mẫu tin
        String checkQuery = "SELECT COUNT(*) FROM Offers WHERE ProductID = ? AND DiscountID = ?";
        Integer count = jdbcTemplate.queryForObject(checkQuery, Integer.class, offers.getProductID(), offers.getDiscountID());

        if (count == null || count == 0) {
            // Nếu không tồn tại, thực hiện thêm
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String query = "INSERT INTO Offers (ProductID, DiscountID, OfferName, Description, DiscountType, DiscountValue) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            jdbcTemplate.update(query,
                    offers.getProductID(),
                    offers.getDiscountID(),
                    offers.getOfferName(),
                    offers.getDescription(),
                    offers.getDiscountType(),
                    offers.getDiscountValue()
            );

            // Gọi phương thức đã chỉnh sửa
            updateProductDiscount(offers.getProductID(), offers.getDiscountType(), offers.getDiscountValue());
        } else {
            throw new RuntimeException("Mẫu tin đã tồn tại.");
        }
    }

    @Override
    public boolean checkIfExists(Offers offers) {
        String query = "SELECT COUNT(*) FROM Offers WHERE ProductID = ? AND DiscountID = ?";
        int count = jdbcTemplate.queryForObject(query, new Object[]{offers.getProductID(), offers.getDiscountID()}, Integer.class);
        return count > 0;
    }

    private void updateProductDiscount(String productId, String discountType, int discountValue) {
    String priceQuery = "SELECT UnitPrice FROM Products WHERE ProductID = ?";
    BigDecimal unitPrice = jdbcTemplate.queryForObject(priceQuery, BigDecimal.class, productId);

    BigDecimal discountAmount; // Giá trị giảm giá sẽ được lưu vào cột Discount

    if ("Discount of money".equalsIgnoreCase(discountType)) {
        // Lưu trực tiếp số tiền giảm vào cột Discount
        discountAmount = new BigDecimal(discountValue);

        if (discountAmount.compareTo(unitPrice) > 0) {
            throw new RuntimeException("Discount value cannot be greater than the product price.");
        }
    } else if ("% Discount".equalsIgnoreCase(discountType)) {
        // Tính toán số tiền giảm theo phần trăm và lưu vào cột Discount
        BigDecimal discountPercentage = new BigDecimal(discountValue).divide(new BigDecimal(100));
        discountAmount = unitPrice.multiply(discountPercentage); // Số tiền giảm từ % discount

        if (discountAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("The discounted price cannot be negative.");
        }
    } else {
        throw new RuntimeException("Invalid discount type.");
    }

    // Cập nhật giá trị giảm vào cột Discount (không phải giá tiền sau giảm)
    String updateQuery = "UPDATE Products SET Discount = ? WHERE ProductID = ?";
    jdbcTemplate.update(updateQuery, discountAmount, productId);
}

  @Override
public void update(Offers offers) {
    String priceQuery = "SELECT UnitPrice FROM Products WHERE ProductID = ?";
    BigDecimal unitPrice = jdbcTemplate.queryForObject(priceQuery, BigDecimal.class, offers.getProductID());

    BigDecimal discountedPrice;

    if ("Discount of money".equalsIgnoreCase(offers.getDiscountType())) {
        discountedPrice = unitPrice.subtract(new BigDecimal(offers.getDiscountValue()));
        if (discountedPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("The discounted price cannot be negative."); // Thông báo lỗi
        }
    } else if ("% Discount".equalsIgnoreCase(offers.getDiscountType())) {
        BigDecimal discountPercentage = new BigDecimal(offers.getDiscountValue()).divide(new BigDecimal(100));
        discountedPrice = unitPrice.subtract(unitPrice.multiply(discountPercentage));
        if (discountedPrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("The discounted price cannot be negative."); // Thông báo lỗi
        }
    } else {
        throw new RuntimeException("Invalid discount type."); // Thông báo lỗi nếu loại giảm giá không hợp lệ
    }

    String query = "UPDATE Offers SET ProductID = ?, DiscountID = ?, OfferName = ?, Description = ?, "
            + " DiscountType = ?, DiscountValue = ? WHERE OfferID = ?";
    jdbcTemplate.update(query,
            offers.getProductID(),
            offers.getDiscountID(),
            offers.getOfferName(),
            offers.getDescription(),
            offers.getDiscountType(),
            offers.getDiscountValue(),
            offers.getOfferID()
    );

    updateProductPrices(offers.getOfferID(), offers.getDiscountType(), offers.getDiscountValue());
}


    private void updateProductPrices(int offerID, String discountType, int discountValue) {
        String productQuery = "SELECT p.ProductID, p.UnitPrice FROM Products p JOIN Offers o ON p.ProductID = o.ProductID WHERE o.OfferID = ?";
        List<Map<String, Object>> products = jdbcTemplate.queryForList(productQuery, offerID);

        for (Map<String, Object> product : products) {
            String productId = (String) product.get("ProductID");
            BigDecimal unitPrice = (BigDecimal) product.get("UnitPrice");
            BigDecimal discountedPrice;

            if ("Discount of money".equalsIgnoreCase(discountType)) {
                discountedPrice = unitPrice.subtract(new BigDecimal(discountValue));
            } else if ("% Discount".equalsIgnoreCase(discountType)) {
                BigDecimal discountPercentage = new BigDecimal(discountValue).divide(new BigDecimal(100));
                // Tính giá trị giảm
                BigDecimal discountAmount = unitPrice.multiply(discountPercentage);
                discountedPrice = discountAmount;
            } else {
                continue;
            }

            String updatePriceQuery = "UPDATE Products SET Discount = ? WHERE ProductID = ?";
            jdbcTemplate.update(updatePriceQuery, discountedPrice, productId);
        }
    }

    @Override
    public void deleteById(int id) {
        String updateProductsQuery = "UPDATE Products SET Discount = 0 WHERE ProductID IN (SELECT ProductID FROM Offers WHERE OfferID = ?)";
        jdbcTemplate.update(updateProductsQuery, id);
        String query = "DELETE FROM Offers WHERE OfferID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Offers findById(int id) {
        String query = "SELECT o.offerID, o.productID, d.discountID, o.offerName, o.description, "
                + " p.productName, d.discountName, o.discountType, o.discountValue "
                + "FROM Offers o "
                + "JOIN Products p ON o.productID = p.productID "
                + "JOIN Discounts d ON o.discountID = d.discountID "
                + "WHERE o.OfferID=?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Offers>() {
            @Override
            public Offers mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Offers(
                        rs.getInt("OfferID"),
                        rs.getString("ProductID"),
                        rs.getInt("DiscountID"),
                        rs.getString("OfferName"),
                        rs.getString("Description"),
                        rs.getString("ProductName"),
                        rs.getString("DiscountName"),
                        rs.getString("DiscountType"),
                        rs.getInt("DiscountValue")
                );
            }
        });
    }

    @Override
    public List<Offers> findPaginated(int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Số trang hoặc kích thước trang không hợp lệ");
        }

        String query = "SELECT o.offerID, o.productID, d.discountID, o.offerName, o.description, "
                + " p.productName, d.discountName, o.discountType, o.discountValue "
                + "FROM Offers o "
                + "JOIN Products p ON o.productID = p.productID "
                + "JOIN Discounts d ON o.discountID = d.discountID "
                + "ORDER BY o.offerID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{offset, pageSize}, new RowMapper<Offers>() {
            @Override
            public Offers mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Offers(
                        rs.getInt("offerID"),
                        rs.getString("productID"),
                        rs.getInt("discountID"),
                        rs.getString("offerName"),
                        rs.getString("description"),
                        rs.getString("productName"),
                        rs.getString("discountName"),
                        rs.getString("DiscountType"),
                        rs.getInt("DiscountValue")
                );
            }
        });
    }

    @Override
    public List<Offers> searchByKeyword(String keyword, int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Số trang hoặc kích thước trang không hợp lệ");
        }

        String query = "SELECT o.offerID, o.productID, d.discountID, o.offerName, o.description, "
                + " p.productName, d.discountName, o.discountType, o.discountValue "
                + "FROM Offers o "
                + "JOIN Products p ON o.productID = p.productID "
                + "JOIN Discounts d ON o.discountID = d.discountID "
                + "WHERE p.productName LIKE ? OR d.discountName LIKE ? "
                + "ORDER BY o.offerID "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        String searchKeyword = "%" + keyword + "%";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{searchKeyword, searchKeyword, offset, pageSize}, new RowMapper<Offers>() {
            @Override
            public Offers mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Offers(
                        rs.getInt("offerID"),
                        rs.getString("productID"),
                        rs.getInt("discountID"),
                        rs.getString("offerName"),
                        rs.getString("description"),
                        rs.getString("productName"),
                        rs.getString("discountName"),
                        rs.getString("DiscountType"),
                        rs.getInt("DiscountValue")
                );
            }
        });
    }

    @Override
    public int countByKeyword(String keyword) {
        String query = "SELECT COUNT(*) FROM Offers o "
                + "JOIN Products p ON o.productID = p.productID "
                + "JOIN Discounts d ON o.discountID = d.discountID "
                + "WHERE p.productName LIKE ? OR d.discountName LIKE ?";

        String searchKeyword = "%" + keyword + "%";
        return jdbcTemplate.queryForObject(query, new Object[]{searchKeyword, searchKeyword}, Integer.class);
    }

    @Override
    public int getTotalOffers() {
        String query = "SELECT COUNT(*) FROM Offers";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    @Override
    public String findClosestMatch(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Từ khóa không được để trống");
        }

        keyword = keyword.toLowerCase().trim();

        // Truy vấn để lấy OfferName, ProductName và DiscountName từ các bảng Offers, Products và Discounts
        String query = "SELECT o.OfferName, p.ProductName, d.DiscountName "
                + "FROM Offers o "
                + "JOIN Products p ON o.ProductID = p.ProductID "
                + "JOIN Discounts d ON o.discountID = d.discountID";

        List<Map<String, Object>> results = jdbcTemplate.queryForList(query);

        String closestMatch = null;
        double bestScore = Double.MAX_VALUE;

        for (Map<String, Object> row : results) {
            String offerName = (String) row.get("OfferName");
            String productName = (String) row.get("ProductName");
            String discountName = (String) row.get("DiscountName");

            // So sánh từ khóa với từng tên
            for (String name : new String[]{offerName, productName, discountName}) {
                if (name != null) {
                    String lowerName = name.toLowerCase();
                    double score;

                    if (lowerName.startsWith(keyword)) {
                        score = 0.1 * (lowerName.length() - keyword.length());
                    } else {
                        score = weightedLevenshteinDistance(keyword, lowerName);
                        score = score / Math.max(keyword.length(), lowerName.length());
                    }

                    if (score < bestScore) {
                        bestScore = score;
                        closestMatch = name;
                    }
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
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
