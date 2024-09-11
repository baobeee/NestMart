package com.models;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

public class ProductsDAOImpl implements ProductsDAO {

    private JdbcTemplate jdbcTemplate;

    public ProductsDAOImpl() {
    }

    public ProductsDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public List<Products> findAll() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, Image, DateAdded, Discount, AverageRating FROM Products";
        List<Products> productList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); // Kiểm tra số lượng bản ghi

        for (Map<String, Object> row : rows) {
            Products p = new Products(
                    (String) row.get("ProductID"),
                    (Integer) row.get("CategoryID"),
                    (Integer) row.get("BrandID"),
                    (String) row.get("ProductName"),
                    (String) row.get("ProductDescription"),
                    (BigDecimal) row.get("UnitPrice"), // Giữ kiểu BigDecimal
                    (String) row.get("Image"),
                    (java.util.Date) row.get("DateAdded"),
                    (BigDecimal) row.get("Discount"), // Giữ kiểu BigDecimal
                    ((Number) row.get("AverageRating")).byteValue() // Convert from Byte
            );
            productList.add(p);
        }
        System.out.println(productList); // In danh sách sản phẩm
        return productList;
    }
  @Override
public void save(Products product, MultipartFile imageFile, ServletContext servletContext) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDateAdded = sdf.format(product.getDateAdded());

    // Check for required fields
    if (product.getProductID() == null || product.getProductID().isEmpty()) {
        throw new RuntimeException("ProductID không được để trống.");
    }
    if (product.getUnitPrice() == null || product.getUnitPrice().compareTo(BigDecimal.ZERO) <= 0) {
        throw new RuntimeException("UnitPrice phải lớn hơn 0.");
    }
    if (imageFile == null || imageFile.isEmpty()) {
        throw new RuntimeException("Ảnh sản phẩm không được để trống.");
    }
    if (product.getDiscount() == null || product.getDiscount().compareTo(BigDecimal.ZERO) < 0) {
        throw new RuntimeException("Discount không thể âm.");
    }
    if (product.getAverageRating() == null || product.getAverageRating().intValue() < 0 || product.getAverageRating().intValue() > 5) {
        throw new RuntimeException("AverageRating phải từ 0 đến 5.");
    }

    // Check if ProductID already exists
    String query = "SELECT ProductID FROM Products WHERE ProductID = ?";
    List<String> existingIds = jdbcTemplate.query(query, new Object[]{product.getProductID()},
            (rs, rowNum) -> rs.getString("ProductID"));

    if (!existingIds.isEmpty()) {
        throw new RuntimeException("Sản phẩm với ID này đã tồn tại.");
    }

    String fileName = null;

    // Handle file upload
    try {
        // Get file name
        fileName = Paths.get(imageFile.getOriginalFilename()).getFileName().toString();
        if (fileName == null || fileName.isEmpty()) {
            throw new RuntimeException("Tên file không hợp lệ.");
        }

        // Compute upload path
            String relativePath = "/web/assets/admin/images/uploads/products/";
        String uploadPath = new File(servletContext.getRealPath("")).getParentFile().getParentFile().getAbsolutePath() + relativePath;

        // Ensure the directory exists
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            if (uploadDir.mkdirs()) {
                System.out.println("Directories created: " + uploadDir.getAbsolutePath());
            } else {
                throw new RuntimeException("Không thể tạo thư mục: " + uploadDir.getAbsolutePath());
            }
        }

        // Full file path
        String filePath = uploadPath + File.separator + fileName;
        System.out.println("File Path: " + filePath);

        // Save the file
        imageFile.transferTo(new File(filePath));
        System.out.println("File saved successfully: " + filePath);

    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
    }

    // Insert product into database
    String insertQuery = "INSERT INTO Products (ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, Image, DateAdded, Discount, AverageRating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(insertQuery,
            product.getProductID(),
            product.getCategoryID(),
            product.getBrandID(),
            product.getProductName(),
            product.getProductDescription(),
            product.getUnitPrice(),
            fileName,
            formattedDateAdded,
            product.getDiscount(),
            product.getAverageRating()
    );
}

@Override
public void update(Products product, MultipartFile imageFile, ServletContext servletContext) {
    // Get current file name
    String fileName = product.getImage();

    if (imageFile != null && !imageFile.isEmpty()) {
        try {
            // Get new file name
            fileName = Paths.get(imageFile.getOriginalFilename()).getFileName().toString();
            if (fileName == null || fileName.isEmpty()) {
                throw new RuntimeException("Tên file không hợp lệ.");
            }

            // Compute upload path
            String relativePath = "/web/assets/admin/images/uploads/products/";
            String uploadPath = new File(servletContext.getRealPath("")).getParentFile().getParentFile().getAbsolutePath() + relativePath;

            // Ensure the directory exists
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                if (uploadDir.mkdirs()) {
                    System.out.println("Directories created: " + uploadDir.getAbsolutePath());
                } else {
                    throw new RuntimeException("Không thể tạo thư mục: " + uploadDir.getAbsolutePath());
                }
            }

            // Full file path
            String filePath = uploadPath + File.separator + fileName;

            // Delete old file if exists
            File oldFile = new File(uploadPath + File.separator + product.getImage());
            if (oldFile.exists() && !oldFile.delete()) {
                System.out.println("Không thể xóa tệp cũ: " + oldFile.getAbsolutePath());
            }

            // Save the new file
            imageFile.transferTo(new File(filePath));
            System.out.println("File saved successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
        }
    } else {
        // Keep existing file if no new file is provided
        if (fileName == null || fileName.isEmpty()) {
            fileName = jdbcTemplate.queryForObject(
                    "SELECT Image FROM Products WHERE ProductID = ?",
                    new Object[]{product.getProductID()},
                    String.class
            );
        }
    }

    // Update product in database
    String query = "UPDATE Products SET CategoryID = ?, BrandID = ?, ProductName = ?, ProductDescription = ?, UnitPrice = ?, Image = ?, DateAdded = ?, Discount = ?, AverageRating = ? WHERE ProductID = ?";
    jdbcTemplate.update(query,
            product.getCategoryID(),
            product.getBrandID(),
            product.getProductName(),
            product.getProductDescription(),
            product.getUnitPrice(),
            fileName,
            product.getDateAdded(),
            product.getDiscount(),
            product.getAverageRating(),
            product.getProductID()
    );
}


    @Override
    public void deleteById(String id) {
        String query = "DELETE FROM Products WHERE ProductID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Products findById(String id) {
        String query = "SELECT ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, Image, DateAdded, Discount, AverageRating FROM Products WHERE ProductID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Products>() {
            @Override
            public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Products(
                        rs.getString("ProductID"),
                        rs.getInt("CategoryID"),
                        rs.getInt("BrandID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getBigDecimal("UnitPrice"), // Thay đổi từ double thành BigDecimal
                        rs.getString("Image"),
                        rs.getDate("DateAdded"),
                        rs.getBigDecimal("Discount"), // Thay đổi từ double thành BigDecimal
                        rs.getByte("AverageRating") // Directly read Byte
                );
            }
        });
    }

    @Override
    public List<Products> findByCategoryId(int categoryId) {
        String query = "SELECT * FROM Products WHERE CategoryID = ?";
        return jdbcTemplate.query(query, new Object[]{categoryId}, new BeanPropertyRowMapper<>(Products.class));
    }

    @Override
    public Map<String, String> getProductNames(List<String> productIds) {
        if (productIds.isEmpty()) {
            return Collections.emptyMap();
        }

        String placeholders = String.join(",", Collections.nCopies(productIds.size(), "?"));
        String query = "SELECT ProductID, ProductName FROM Products WHERE ProductID IN (" + placeholders + ")";

        return jdbcTemplate.query(query, productIds.toArray(), rs -> {
            Map<String, String> productNames = new HashMap<>();
            while (rs.next()) {
                productNames.put(rs.getString("ProductID"), rs.getString("ProductName"));
            }
            return productNames;
        });
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
