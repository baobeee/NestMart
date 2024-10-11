package com.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class DiscountClientDAOImpl implements DiscountClientDAO {

    private JdbcTemplate jdbcTemplate;

    public DiscountClientDAOImpl() {
    }

    public DiscountClientDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

   @Override
public List<DiscountClient> getAllDiscountsWithProducts() {
    List<DiscountClient> discountClients = new ArrayList<>();
    String sql = "WITH RankedProducts AS (\n"
            + "    SELECT \n"
            + "        d.DiscountID, \n"
            + "        d.DiscountName, \n"
            + "        d.Image as DiscountImage, \n"
            + "        p.ProductID, \n"
            + "        p.ProductName, \n"
            + "        p.UnitPrice, \n"
            + "        pi.Image as ProductImage, \n"
            + "        p.Discount as ProductDiscount, \n"
            + "        d.StartDate, \n"
            + "        d.EndDate,\n"
            + "        d.Description,\n"
            + "        COALESCE(avgFeedback.AverageRating, 0) AS AverageRating,  -- Đánh giá trung bình\n"
            + "        COALESCE(SUM(od.Quantity), 0) AS TotalQuantitySold,        -- Tổng số lượt bán\n"
            + "        ROW_NUMBER() OVER (PARTITION BY d.DiscountID ORDER BY p.ProductID) as RowNum\n"
            + "    FROM Discounts d\n"
            + "    LEFT JOIN Offers o ON d.DiscountID = o.DiscountID\n"
            + "    LEFT JOIN Products p ON o.ProductID = p.ProductID\n"
            + "    LEFT JOIN (\n"
            + "        SELECT ProductID, MIN(Image) as Image \n"
            + "        FROM ProductImage \n"
            + "        GROUP BY ProductID\n"
            + "    ) pi ON p.ProductID = pi.ProductID\n"
            + "    LEFT JOIN (\n"
            + "        SELECT \n"
            + "            f.ProductID, \n"
            + "            AVG(CAST(f.Rating AS FLOAT)) AS AverageRating \n"
            + "        FROM Feedback f \n"
            + "        GROUP BY f.ProductID\n"
            + "    ) AS avgFeedback ON p.ProductID = avgFeedback.ProductID \n"
            + "    LEFT JOIN OrderDetails od ON p.ProductID = od.ProductID \n"
            + "    LEFT JOIN Orders o2 ON od.OrderID = o2.OrderID \n"
            + "    WHERE \n"
            + "        (o2.OrderStatus = 'Completed' OR o2.OrderStatus IS NULL) \n"
            + "    GROUP BY \n"
            + "        d.DiscountID, d.DiscountName, d.Image, p.ProductID, p.ProductName, \n"
            + "        p.UnitPrice, pi.Image, p.Discount, d.StartDate, d.EndDate, \n"
            + "        d.Description, avgFeedback.AverageRating \n"
            + ")\n"
            + "SELECT *\n"
            + "FROM RankedProducts\n"
            + "WHERE RowNum <= 8\n"
            + "ORDER BY DiscountID, ProductID;";

    return jdbcTemplate.query(sql, (ResultSet rs) -> {
        List<DiscountClient> results = new ArrayList<>();
        DiscountClient currentDiscountClient = null;
        List<ProductsClient> productList = null;

        while (rs.next()) {
            int discountID = rs.getInt("DiscountID");

            if (currentDiscountClient == null || currentDiscountClient.getDiscountID() != discountID) {
                if (currentDiscountClient != null) {
                    currentDiscountClient.setProducts(productList);
                    results.add(currentDiscountClient);
                }
                currentDiscountClient = new DiscountClient();
                currentDiscountClient.setDiscountID(discountID);
                currentDiscountClient.setDiscountName(rs.getString("DiscountName"));
                currentDiscountClient.setImage(rs.getString("DiscountImage"));
                currentDiscountClient.setStartDate(rs.getDate("StartDate"));
                currentDiscountClient.setEndDate(rs.getDate("EndDate"));
                currentDiscountClient.setDescription(rs.getString("Description"));
                productList = new ArrayList<>();
            }

            String productID = rs.getString("ProductID");
            if (productID != null) {
                ProductsClient product = new ProductsClient();
                product.setProductID(productID);
                product.setProductName(rs.getString("ProductName"));
                product.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                product.setDiscount(rs.getBigDecimal("ProductDiscount"));

                // Lấy thông tin hình ảnh sản phẩm
                ProductImage productImage = new ProductImage();
                productImage.setImages(rs.getString("ProductImage"));
                List<ProductImage> images = new ArrayList<>();
                images.add(productImage);
                product.setImages(images);

                // Thêm thông tin đánh giá và tổng số lượt bán
                product.setAverageRating(rs.getBigDecimal("AverageRating")); 
                product.setTotalQuantitySold(rs.getInt("TotalQuantitySold"));

                productList.add(product);
            }
        }

        if (currentDiscountClient != null) {
            currentDiscountClient.setProducts(productList);
            results.add(currentDiscountClient);
        }

        return results;
    });
}

    @Override
public List<ProductsClient> findProductsByDiscount(int discountID, int page, int pageSize) {
    String sql = "WITH RankedProducts AS (\n" +
                 "    SELECT \n" +
                 "        p.ProductID, \n" +
                 "        p.ProductName, \n" +
                 "        p.UnitPrice, \n" +
                 "        pi.Image as ProductImage, \n" +
                 "        p.Discount as ProductDiscount\n" +
                 "    FROM Products p\n" +
                 "    LEFT JOIN Offers o ON p.ProductID = o.ProductID\n" +
                 "    LEFT JOIN (\n" +
                 "        SELECT ProductID, MIN(Image) as Image \n" +
                 "        FROM ProductImage \n" +
                 "        GROUP BY ProductID\n" +
                 "    ) pi ON p.ProductID = pi.ProductID\n" +
                 "    WHERE o.DiscountID = ?\n" +
                 ")\n" +
                 "SELECT *\n" +
                 "FROM RankedProducts\n" +
                 "ORDER BY ProductID\n" +
                 "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";  // sử dụng OFFSET và LIMIT

    int offset = (page - 1) * pageSize; // Tính số hàng cần bỏ qua

    return jdbcTemplate.query(sql, new Object[]{discountID, offset, pageSize}, (ResultSet rs) -> {
        List<ProductsClient> productList = new ArrayList<>();

        while (rs.next()) {
            String productID = rs.getString("ProductID");

            if (productID != null) {
                ProductsClient product = new ProductsClient();
                product.setProductID(productID);
                product.setProductName(rs.getString("ProductName"));
                product.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                product.setDiscount(rs.getBigDecimal("ProductDiscount"));

                ProductImage productImage = new ProductImage();
                productImage.setImages(rs.getString("ProductImage"));
                List<ProductImage> images = new ArrayList<>();
                images.add(productImage);
                product.setImages(images);

                productList.add(product);
            }
        }

        return productList;
    });
}

@Override
public int countProductsByDiscount(int discountID) {
    String sql = "SELECT COUNT(*) FROM Products p " +
                 "LEFT JOIN Offers o ON p.ProductID = o.ProductID " +
                 "WHERE o.DiscountID = ?";

    return jdbcTemplate.queryForObject(sql, new Object[]{discountID}, Integer.class);
}



    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
