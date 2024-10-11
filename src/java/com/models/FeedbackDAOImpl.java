package com.models;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class FeedbackDAOImpl implements FeedbackDAO {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    public FeedbackDAOImpl() {
    }

    public FeedbackDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Map<String, Object> getAverageRatingAndCount(String productId) {
        String sql = "SELECT AVG(CAST(Rating AS FLOAT)) AS averageRating, "
                + "COUNT(*) AS feedbackCount "
                + "FROM Feedback "
                + "WHERE ProductID = ?;";

        return jdbcTemplate.queryForObject(sql, new Object[]{productId}, new RowMapper<Map<String, Object>>() {
            @Override
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                Map<String, Object> result = new HashMap<>();
                double averageRating = rs.getDouble("averageRating");

                DecimalFormat df = new DecimalFormat("#.#");
                String formattedAverageRating = df.format(averageRating);

                result.put("averageRating", Double.parseDouble(formattedAverageRating));
                result.put("feedbackCount", rs.getInt("feedbackCount"));
                return result;
            }
        });
    }

    @Override
    public List<Feedback> findAllFeedback() {
        String query = "SELECT f.FeedbackID, a.FullName AS CustomerName, p.ProductID, p.ProductName, "
                + "f.Rating, f.FeedbackContent, f.FeedbackDate, f.FeedbackImage, MIN(pi.Image) AS ProductImage FROM Feedback f "
                + "JOIN Products p ON f.ProductID = p.ProductID LEFT JOIN ProductImage pi ON f.ProductID = pi.ProductID "
                + "INNER JOIN Accounts a ON a.AccountID = f.CustomerID "
                + "GROUP BY f.FeedbackID, p.ProductID, p.ProductName, f.Rating, f.FeedbackContent, f.FeedbackDate, f.FeedbackImage, a.FullName;";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size());

        Map<Integer, Feedback> fbMap = new HashMap<>();

        for (Map<String, Object> row : rows) {
            int feedbackID = (int) row.get("FeedbackID");
            Feedback fb = fbMap.get(feedbackID);
            if (fb == null) {
                fb = new Feedback();
                fb.setFeedbackID(feedbackID);
                fb.setRating((short) row.get("Rating"));
                fb.setFeedbackContent((String) row.get("FeedbackContent"));

                Timestamp timestampFeedbackDate = (Timestamp) row.get("FeedbackDate");
                LocalDateTime dateTimeFeedbackDate = timestampFeedbackDate != null ? timestampFeedbackDate.toLocalDateTime() : null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTimeFeedbackDate = (dateTimeFeedbackDate != null) ? dateTimeFeedbackDate.format(formatter) : null;
                fb.setFormattedFeedbackDate(formattedDateTimeFeedbackDate);
                fb.setFeedbackDate(dateTimeFeedbackDate);
                System.out.println("FeedbackID: " + fb.getFeedbackID() + ", FeedbackDate: " + fb.getFeedbackDate());
                System.out.println("FormattedFeedbackDate: " + fb.getFormattedFeedbackDate());
                fb.setCustomerName((String) row.get("CustomerName"));

                fb.setFeedbackImage((String) row.get("FeedbackImage"));

                Products product = new Products();
                product.setProductID((String) row.get("ProductID"));
                product.setProductName((String) row.get("ProductName"));

                List<ProductImage> images = new ArrayList<>();
                String imagePath = (String) row.get("ProductImage");
                if (imagePath != null) {
                    ProductImage image = new ProductImage();
                    image.setImages(imagePath);
                    images.add(image);
                }
                product.setImages(images);
                fb.setProduct(product);

                fbMap.put(feedbackID, fb);
            }
        }
        return new ArrayList<>(fbMap.values());
    }

    @Override
    public Products findProductByID(String productID) {
        String query = "SELECT p.*, pi.ProductImageID, pi.ProductID , pi.Image "
                + "FROM Products p LEFT JOIN ProductImage pi ON p.ProductID = pi.ProductID "
                + "WHERE p.ProductID = ?";

        return jdbcTemplate.query(query, new Object[]{productID}, rs -> {
            Products product = null;
            List<ProductImage> images = new ArrayList<>();
            while (rs.next()) {
                if (product == null) {
                    product = new Products();
                    product.setProductID(rs.getString("ProductID"));
                    product.setCategoryID(rs.getInt("CategoryID"));
                    product.setBrandID(rs.getInt("BrandID"));
                    product.setProductName(rs.getString("ProductName"));
                    product.setProductDescription(rs.getString("ProductDescription"));
                    product.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                    product.setDateAdded(rs.getDate("DateAdded"));
                    product.setDiscount(rs.getBigDecimal("Discount"));
                }

                String imageId = rs.getString("ProductImageID");
                if (imageId != null) {
                    ProductImage image = new ProductImage();
                    image.setProductImageID(imageId);
                    image.setProductID(rs.getString("ProductID"));
                    image.setImages(rs.getString("Image"));
                    images.add(image);
                }
            }
            if (product != null) {
                product.setImages(images);
            }
            return product;
        });
    }

    @Override
    public List<Feedback> getFeedbackByProductId(int fbID) {
        String query = "SELECT f.FeedbackID, f.FeedbackContent, f.Rating, f.FeedbackDate, f.FeedbackImage, f.ProductID, "
                + "p.ProductName, pi.Image AS ProductImage, a.FullName AS CustomerName "
                + "FROM Feedback f "
                + "INNER JOIN Products p ON f.ProductID = p.ProductID "
                + "LEFT JOIN ProductImage pi ON p.ProductID = pi.ProductID "
                + "INNER JOIN Accounts a ON f.CustomerID = a.AccountID "
                + "WHERE f.FeedbackID = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, fbID);
        Map<Integer, Feedback> fbMap = new HashMap<>();

        for (Map<String, Object> row : rows) {
            int feedbackID = (Integer) row.get("FeedbackID");
            Feedback feedback = fbMap.get(feedbackID);
            if (feedback == null) {
                feedback = new Feedback();
                feedback.setFeedbackID(feedbackID);
                feedback.setFeedbackContent((String) row.get("FeedbackContent"));

                feedback.setRating(((Number) row.get("Rating")).shortValue());

                Timestamp timestamp = (Timestamp) row.get("FeedbackDate");
                LocalDateTime feedbackDate = timestamp != null ? timestamp.toLocalDateTime() : null;
                feedback.setFeedbackDate(feedbackDate);
                if (feedbackDate != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    feedback.setFormattedFeedbackDate(feedbackDate.format(formatter));
                }

                feedback.setFeedbackImage((String) row.get("FeedbackImage"));

                Products product = new Products();
                product.setProductID("ProductID");
                product.setProductName((String) row.get("ProductName"));

                String productImage = (String) row.get("ProductImage");
                if (productImage != null) {
                    ProductImage img = new ProductImage();
                    img.setImages(productImage);
                    List<ProductImage> images = new ArrayList<>();
                    images.add(img);
                    product.setImages(images);
                }

                feedback.setProduct(product);

                feedback.setCustomerName((String) row.get("CustomerName"));

                fbMap.put(feedbackID, feedback);
            }
        }

        return new ArrayList<>(fbMap.values());
    }

    @Override
    public EmployeeResponse getResponseByFeedbackId(int feedbackID) {
        String query = "SELECT ResponseID, EmployeeID, FeedbackID, ResponseContent, ResponseDate FROM EmployeeResponse WHERE FeedbackID = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{feedbackID}, (rs, rowNum) -> {
                EmployeeResponse er = new EmployeeResponse();
                er.setResponseID(rs.getInt("ResponseID"));
                er.setEmployeeID(rs.getInt("EmployeeID"));
                er.setFeedbackID(rs.getInt("FeedbackID"));
                er.setResponseContent(rs.getString("ResponseContent"));
                Timestamp timestamp = rs.getTimestamp("ResponseDate");
                if (timestamp != null) {
                    LocalDateTime responseDate = timestamp.toLocalDateTime();
                    er.setResponseDate(responseDate);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedResponseDate = responseDate.format(formatter);
                    er.setFormattedResponseDate(formattedResponseDate);
                }
                return er;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void add(Feedback feedback) {
        feedback.setFeedbackDate(LocalDateTime.now());
        String query = "INSERT INTO Feedback (ProductID, CustomerID, Rating, FeedbackContent, FeedbackImage, FeedbackDate) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, feedback.getProductID(), feedback.getCustomerID(), feedback.getRating(), feedback.getFeedbackContent(), feedback.getFeedbackImage(), Timestamp.valueOf(feedback.getFeedbackDate()));
    }

    @Override
    public Feedback get(int feedbackID) {
        String query = "SELECT f.FeedbackID, a.FullName AS CustomerName, p.ProductID, p.ProductName, CAST(ROUND(AVG(CAST(f.Rating AS FLOAT)), 1) AS DECIMAL(10, 1)) AS AverageRating, "
                + "f.Rating, f.FeedbackContent, f.FeedbackDate, f.FeedbackImage, MIN(pi.Image) AS ProductImage FROM Feedback f "
                + "JOIN Products p ON f.ProductID = p.ProductID LEFT JOIN ProductImage pi ON f.ProductID = pi.ProductID "
                + "INNER JOIN Accounts a ON a.AccountID = f.CustomerID "
                + "WHERE f.FeedbackID = ? "
                + "GROUP BY f.FeedbackID, p.ProductID, p.ProductName, f.Rating, f.FeedbackContent, f.FeedbackDate, f.FeedbackImage, a.FullName;";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{feedbackID}, (rs, rowNum) -> {
                Feedback fb = new Feedback();
                fb.setFeedbackID(rs.getInt("FeedbackID"));
                fb.setFeedbackContent(rs.getString("FeedbackContent"));
                fb.setRating(rs.getShort("Rating"));
                fb.setFeedbackImage(rs.getString("FeedbackImage"));
                Timestamp timestamp = rs.getTimestamp("FeedbackDate");
                fb.setFeedbackDate(timestamp.toLocalDateTime());
                fb.setCustomerName(rs.getString("CustomerName"));
                fb.setProductImage(rs.getString("ProductImage"));
                fb.setProductName(rs.getString("ProductName"));
                return fb;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Feedback> getFeedbacksForProduct(String productId) {
        String sql = "SELECT f.FeedbackID, f.ProductID, f.CustomerID, f.Rating, f.FeedbackContent, f.FeedbackDate, c.FullName FROM Feedback f JOIN Accounts c ON f.CustomerID = c.AccountID WHERE f.ProductID = ?";

        return jdbcTemplate.query(sql, new Object[]{productId}, new RowMapper<Feedback>() {
            @Override
            public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setProductID(rs.getString("ProductID"));
                feedback.setCustomerID(rs.getInt("CustomerID"));
                feedback.setRating(rs.getShort("Rating"));
                feedback.setFeedbackContent(rs.getString("FeedbackContent"));
                Timestamp timestampFeedbackDate = (rs.getTimestamp("FeedbackDate"));
                LocalDateTime dateTimeFeedbackDate = timestampFeedbackDate != null ? timestampFeedbackDate.toLocalDateTime() : null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTimeFeedbackDate = (dateTimeFeedbackDate != null) ? dateTimeFeedbackDate.format(formatter) : null;
                feedback.setFormattedFeedbackDate(formattedDateTimeFeedbackDate);
                feedback.setFeedbackDate(dateTimeFeedbackDate);
                feedback.setCustomerName(rs.getString("FullName"));
                return feedback;
            }
        });
    }

    @Override
    public List<Feedback> getFeedbacksByRating(String productId, int rating) {
        String sql = "SELECT f.FeedbackID, f.ProductID, f.CustomerID, f.Rating, f.FeedbackContent, f.FeedbackDate, c.FullName FROM Feedback f JOIN Accounts c ON f.CustomerID = c.AccountID WHERE f.ProductID = ? AND f.Rating = ?";
        return jdbcTemplate.query(sql, new Object[]{productId, rating}, new RowMapper<Feedback>() {
            @Override
            public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt("FeedbackID"));
                feedback.setProductID(rs.getString("ProductID"));
                feedback.setCustomerID(rs.getInt("CustomerID"));
                feedback.setRating(rs.getShort("Rating"));
                feedback.setFeedbackContent(rs.getString("FeedbackContent"));
                Timestamp timestampFeedbackDate = (rs.getTimestamp("FeedbackDate"));
                LocalDateTime dateTimeFeedbackDate = timestampFeedbackDate != null ? timestampFeedbackDate.toLocalDateTime() : null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTimeFeedbackDate = (dateTimeFeedbackDate != null) ? dateTimeFeedbackDate.format(formatter) : null;
                feedback.setFormattedFeedbackDate(formattedDateTimeFeedbackDate);
                feedback.setFeedbackDate(dateTimeFeedbackDate);
                feedback.setCustomerName(rs.getString("FullName"));
                return feedback;
            }
        });
    }

    @Override
    public List<Feedback> getFeedbackWithoutResponse() {
        String query = "SELECT f.FeedbackID, f.ProductID, f.FeedbackContent, f.Rating, f.FeedbackDate, f.FeedbackImage, "
                + "p.ProductName, MIN(pi.Image) AS ProductImage, a.FullName AS CustomerName, er.ResponseContent, er.ResponseDate FROM Feedback f "
                + "INNER JOIN Products p ON f.ProductID = p.ProductID "
                + "LEFT JOIN ProductImage pi ON p.ProductID = pi.ProductID "
                + "INNER JOIN Accounts a ON f.CustomerID = a.AccountID "
                + "LEFT JOIN EmployeeResponse er ON f.FeedbackID = er.FeedbackID "
                + "WHERE er.FeedbackID IS NULL "
                + "GROUP BY f.FeedbackID, f.ProductID, p.ProductName, f.Rating, f.FeedbackContent, f.FeedbackDate, f.FeedbackImage, a.FullName, er.ResponseContent, er.ResponseDate";

        return mapFeedbackList(query);
    }

    @Override
    public List<Feedback> getFeedbackWithResponse() {
        String query = "SELECT f.FeedbackID, f.ProductID, f.FeedbackContent, f.Rating, f.FeedbackDate, f.FeedbackImage, "
                + "p.ProductName, MIN(pi.Image) AS ProductImage, a.FullName AS CustomerName, er.ResponseContent, er.ResponseDate FROM Feedback f "
                + "INNER JOIN Products p ON f.ProductID = p.ProductID "
                + "LEFT JOIN ProductImage pi ON p.ProductID = pi.ProductID "
                + "INNER JOIN Accounts a ON f.CustomerID = a.AccountID "
                + "INNER JOIN EmployeeResponse er ON f.FeedbackID = er.FeedbackID WHERE er.EmployeeID = 2"
                + "GROUP BY f.FeedbackID, f.ProductID, p.ProductName, f.Rating, f.FeedbackContent, f.FeedbackDate, f.FeedbackImage, a.FullName, er.ResponseContent, er.ResponseDate";

        return mapFeedbackList(query);
    }

    private List<Feedback> mapFeedbackList(String query) {
        List<Feedback> feedbackList = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        for (Map<String, Object> row : rows) {
            Feedback feedback = new Feedback();
            feedback.setFeedbackID((Integer) row.get("FeedbackID"));
            feedback.setFeedbackContent((String) row.get("FeedbackContent"));
            feedback.setRating((Short) row.get("Rating"));

            Timestamp timestampFeedbackDate = (Timestamp) row.get("FeedbackDate");
            LocalDateTime dateTimeFeedbackDate = timestampFeedbackDate.toLocalDateTime();
            feedback.setFeedbackDate(dateTimeFeedbackDate);

            feedback.setProductID((String) row.get("ProductID"));
            feedback.setFeedbackImage((String) row.get("FeedbackImage"));

            Products product = new Products();
            product.setProductName((String) row.get("ProductName"));

            List<ProductImage> images = new ArrayList<>();
            String productImagePath = (String) row.get("ProductImage");
            if (productImagePath != null) {
                ProductImage image = new ProductImage();
                image.setImages(productImagePath);
                images.add(image);
            }

            product.setImages(images);
            feedback.setProduct(product);
            feedback.setCustomerName((String) row.get("CustomerName"));

            String responseContent = (String) row.get("ResponseContent");
            if (responseContent != null) {
                feedback.setResponseContent(responseContent);
            }

            Timestamp responseDate = (Timestamp) row.get("ResponseDate");
            if (responseDate != null) {
                LocalDateTime responseDateTime = responseDate.toLocalDateTime();
                feedback.setResponseDate(responseDateTime);
            }

            feedbackList.add(feedback);
        }

        return feedbackList;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
