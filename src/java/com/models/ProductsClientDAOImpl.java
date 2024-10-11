package com.models;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;

public class ProductsClientDAOImpl implements ProductsClientDAO {

    private JdbcTemplate jdbcTemplate;

    public ProductsClientDAOImpl() {
    }

    public ProductsClientDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public List<ProductsClient> findAll() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        // Truy vấn SQL để lấy thông tin sản phẩm cùng với hình ảnh
        String query = "SELECT p.ProductID, p.CategoryID, p.BrandID, p.ProductName, p.ProductDescription, p.UnitPrice, "
                + "pi.Image AS ProductImage, p.DateAdded, p.Discount "
                + "FROM Products p "
                + "LEFT JOIN ProductImage pi ON p.ProductID = pi.ProductID";

        // Lấy dữ liệu từ cơ sở dữ liệu
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size());

        // Sử dụng Map để lưu trữ thông tin sản phẩm và hình ảnh
        Map<String, ProductsClient> productMap = new HashMap<>();

        for (Map<String, Object> row : rows) {
            String productId = (String) row.get("ProductID");

            // Tạo hoặc lấy sản phẩm từ bản đồ
            ProductsClient product = productMap.get(productId);
            if (product == null) {
                product = new ProductsClient(
                        productId,
                        (Integer) row.get("CategoryID"),
                        (Integer) row.get("BrandID"),
                        (String) row.get("ProductName"),
                        (String) row.get("ProductDescription"),
                        (BigDecimal) row.get("UnitPrice"),
                        new ArrayList<>(), // Khởi tạo danh sách hình ảnh
                        (Date) row.get("DateAdded"),
                        (BigDecimal) row.get("Discount"),
                        null,
                        0
                );
                productMap.put(productId, product);
            }

            // Thêm thông tin hình ảnh nếu có
            String images = (String) row.get("ProductImage");
            if (images != null) {
                ProductImage productImage = new ProductImage();
                productImage.setImages(images);

                // Thêm hình ảnh vào danh sách hình ảnh của sản phẩm
                product.getImages().add(productImage);
            }
        }

        // Chuyển đổi bản đồ sản phẩm thành danh sách
        List<ProductsClient> productList = new ArrayList<>(productMap.values());

        System.out.println(productList);
        return productList;
    }

    @Override
    public void save(ProductsClient product, List<MultipartFile> imageFiles, ServletContext servletContext) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateAdded = sdf.format(product.getDateAdded());

        // Kiểm tra thông tin sản phẩm
        if (product.getProductID() == null || product.getProductID().isEmpty()) {
            throw new RuntimeException("ProductID không được để trống.");
        }

        // Xử lý UnitPrice
        BigDecimal unitPrice;
        try {
            unitPrice = new BigDecimal(product.getUnitPrice().toString().replace(",", ""));
            if (unitPrice.compareTo(BigDecimal.ZERO) <= 0 || unitPrice.compareTo(new BigDecimal("1000000000")) > 0) {
                throw new RuntimeException("UnitPrice phải lớn hơn 0 và nhỏ hơn hoặc bằng 1,000,000,000.");
            }
            product.setUnitPrice(unitPrice);
        } catch (NumberFormatException e) {
            throw new RuntimeException("UnitPrice không hợp lệ.");
        }

        if (product.getDiscount() == null || product.getDiscount().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Discount không thể âm.");
        }

        if (product.getAverageRating() == null || product.getAverageRating().intValue() < 0 || product.getAverageRating().intValue() > 5) {
            throw new RuntimeException("AverageRating phải từ 0 đến 5.");
        }

        // Kiểm tra ID sản phẩm đã tồn tại
        String query = "SELECT ProductID FROM Products WHERE ProductID = ?";
        List<String> existingIds = jdbcTemplate.query(query, new Object[]{product.getProductID()},
                (rs, rowNum) -> rs.getString("ProductID"));

        if (!existingIds.isEmpty()) {
            throw new RuntimeException("Sản phẩm với ID này đã tồn tại.");
        }

        // Xử lý file hình
        List<ProductImage> image = new ArrayList<>();
        String uploadPath = new File(servletContext.getRealPath("")).getParentFile().getParentFile().getAbsolutePath() + "/web/assets/admin/images/uploads/products/";

        try {
            if (imageFiles != null && !imageFiles.isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                    throw new RuntimeException("Không thể tạo thư mục: " + uploadDir.getAbsolutePath());
                }

                for (MultipartFile imageFile : imageFiles) {
                    if (!imageFile.isEmpty()) {
                        String fileName = Paths.get(imageFile.getOriginalFilename()).getFileName().toString();
                        if (fileName == null || fileName.isEmpty()) {
                            throw new RuntimeException("Tên file không hợp lệ.");
                        }

                        String filePath = uploadPath + File.separator + fileName;
                        imageFile.transferTo(new File(filePath));
                        System.out.println("File saved successfully: " + filePath);

                        // Thêm thông tin hình ảnh vào danh sách
                        ProductImage productImage = new ProductImage();
                        productImage.setProductImageID(UUID.randomUUID().toString()); // Generate a unique ID for the image
                        productImage.setProductID(product.getProductID());
                        productImage.setImages(fileName);
                        image.add(productImage);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
        }

        // Lưu thông tin sản phẩm
        String insertProductQuery = "INSERT INTO Products (ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, DateAdded, Discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(insertProductQuery,
                    product.getProductID(),
                    product.getCategoryID(),
                    product.getBrandID(),
                    product.getProductName(),
                    product.getProductDescription(),
                    product.getUnitPrice(),
                    formattedDateAdded,
                    product.getDiscount(),
                    null
            );

            // Lưu thông tin hình ảnh
            String insertImageQuery = "INSERT INTO ProductImage (ProductID, Image) VALUES (?, ?)";
            for (ProductImage img : image) {
                jdbcTemplate.update(insertImageQuery,
                        img.getProductID(),
                        img.getImages()
                );
            }

        } catch (Exception e) {
            // Xóa các file hình nếu có lỗi xảy ra
            for (ProductImage img : image) {
                String filePath = uploadPath + File.separator + img.getImages();
                File fileToDelete = new File(filePath);
                if (fileToDelete.exists() && !fileToDelete.delete()) {
                    System.out.println("Không thể xóa tệp hình ảnh: " + fileToDelete.getAbsolutePath());
                }
            }
            throw e;
        }
    }

    @Override
    public List<ProductsClient> select8random() {
        String query = "SELECT   \n"
                + "    p.ProductID,   \n"
                + "    p.CategoryID,   \n"
                + "    p.BrandID,   \n"
                + "    p.ProductName,   \n"
                + "    p.ProductDescription,   \n"
                + "    p.UnitPrice,   \n"
                + "    p.DateAdded,   \n"
                + "    p.Discount,   \n"
                + "    COALESCE(avgFeedback.AverageRating, 0) AS AverageRating,    \n"
                + "    COALESCE(SUM(od.Quantity), 0) AS TotalQuantitySold  \n"
                + "FROM   \n"
                + "    Products p  \n"
                + "LEFT JOIN   \n"
                + "    (  \n"
                + "        SELECT   \n"
                + "            f.ProductID,   \n"
                + "            AVG(CAST(f.Rating AS FLOAT)) AS AverageRating  \n"
                + "        FROM   \n"
                + "            Feedback f  \n"
                + "        GROUP BY   \n"
                + "            f.ProductID  \n"
                + "    ) AS avgFeedback ON p.ProductID = avgFeedback.ProductID  \n"
                + "LEFT JOIN   \n"
                + "    OrderDetails od ON p.ProductID = od.ProductID  \n"
                + "LEFT JOIN   \n"
                + "    Orders o ON od.OrderID = o.OrderID  \n"
                + "WHERE   \n"
                + "    o.OrderStatus = 'Completed' OR o.OrderStatus IS NULL   \n"
                + "GROUP BY   \n"
                + "    p.ProductID,   \n"
                + "    p.CategoryID,   \n"
                + "    p.BrandID,   \n"
                + "    p.ProductName,   \n"
                + "    p.ProductDescription,   \n"
                + "    p.UnitPrice,   \n"
                + "    p.DateAdded,   \n"
                + "    p.Discount,  \n"
                + "    avgFeedback.AverageRating  \n"
                + "ORDER BY   \n"
                + "    NEWID() \n"
                + "OFFSET 0 ROWS FETCH NEXT 8 ROWS ONLY;";

        Map<String, ProductsClient> productMap = new HashMap<>();

        jdbcTemplate.query(query, rs -> {
            String productId = rs.getString("ProductID");

            // Kiểm tra nếu sản phẩm đã tồn tại trong Map
            ProductsClient product = productMap.get(productId);
            if (product == null) {
                // Nếu sản phẩm chưa tồn tại, tạo mới sản phẩm
                product = new ProductsClient();
                product.setProductID(productId);
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setBrandID(rs.getInt("BrandID"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                product.setDateAdded(rs.getDate("DateAdded"));
product.setDiscount(rs.getBigDecimal("Discount"));
                product.setAverageRating(rs.getBigDecimal("AverageRating") != null ? rs.getBigDecimal("AverageRating") : BigDecimal.ZERO);
                product.setTotalQuantitySold(rs.getInt("TotalQuantitySold")); // Thiết lập số lượng đã bán

                productMap.put(productId, product);
            }
        });

        // Lấy hình ảnh cho từng sản phẩm
        for (ProductsClient product : productMap.values()) {
            String imageQuery = "SELECT ProductImageID, ProductID, Image "
                    + "FROM ProductImage WHERE ProductID = ?";

            List<ProductImage> images = jdbcTemplate.query(imageQuery, new Object[]{product.getProductID()}, new RowMapper<ProductImage>() {
                @Override
                public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ProductImage(
                            rs.getString("ProductImageID"),
                            rs.getString("ProductID"),
                            rs.getString("Image")
                    );
                }
            });

            product.setImages(images);
        }

        return new ArrayList<>(productMap.values());
    }
    @Override
    public void update(ProductsClient product, List<MultipartFile> imageFiles, List<String> imagesToDelete, ServletContext servletContext) {
        // Validate input data like before
        if (product == null || product.getProductID() == null || product.getProductID().isEmpty()) {
            throw new RuntimeException("ProductID không được để trống.");
        }
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new RuntimeException("ProductName không được để trống.");
        }

        // Validate and process UnitPrice
        BigDecimal unitPrice;
        try {
            unitPrice = new BigDecimal(product.getUnitPrice().toString().replace(",", ""));
            if (unitPrice.compareTo(BigDecimal.ZERO) <= 0 || unitPrice.compareTo(new BigDecimal("1000000000")) > 0) {
                throw new RuntimeException("UnitPrice phải lớn hơn 0 và nhỏ hơn hoặc bằng 1,000,000,000.");
            }
            product.setUnitPrice(unitPrice);
        } catch (NumberFormatException e) {
            throw new RuntimeException("UnitPrice không hợp lệ.");
        }

        // Validate Discount
        if (product.getDiscount() == null || product.getDiscount().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Discount không thể âm.");
        }

        // Validate AverageRating
        if (product.getAverageRating() == null || product.getAverageRating().intValue() < 0 || product.getAverageRating().intValue() > 5) {
            throw new RuntimeException("AverageRating phải từ 0 đến 5.");
        }

        // Determine the path for image uploads
        String relativePath = "/web/assets/admin/images/uploads/products/";
        String uploadPath = new File(servletContext.getRealPath("")).getParentFile().getParentFile().getAbsolutePath() + relativePath;

        // Get the list of existing images from the database
        List<String> existingImages = jdbcTemplate.queryForList(
                "SELECT Image FROM ProductImage WHERE ProductID = ?",
                new Object[]{product.getProductID()},
                String.class
        );

        // Handle image deletion
        if (imagesToDelete != null && !imagesToDelete.isEmpty()) {
            for (String imageToDelete : imagesToDelete) {
                // Remove from database
                jdbcTemplate.update("DELETE FROM ProductImage WHERE ProductID = ? AND Image = ?",
                        product.getProductID(), imageToDelete);

                // Remove file from server
                File fileToDelete = new File(uploadPath + File.separator + imageToDelete);
                if (fileToDelete.exists()) {
                    if (!fileToDelete.delete()) {
                        System.err.println("Failed to delete file: " + fileToDelete.getAbsolutePath());
                    }
                }

                // Remove from existing images list
                existingImages.remove(imageToDelete);
            }
        }

        // Handle new images
        List<ProductImage> newImages = new ArrayList<>();
        try {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + uploadDir.getAbsolutePath());
            }

            for (MultipartFile imageFile : imageFiles) {
                if (!imageFile.isEmpty()) {
                    String originalFileName = Paths.get(imageFile.getOriginalFilename()).getFileName().toString();
                    if (originalFileName == null || originalFileName.isEmpty()) {
                        throw new RuntimeException("Invalid file name.");
                    }

                    // Generate a unique file name
                    String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
                    String filePath = uploadPath + File.separator + uniqueFileName;
                    imageFile.transferTo(new File(filePath));
                    System.out.println("File saved successfully: " + filePath);

                    // Add image information to the list
                    ProductImage productImage = new ProductImage();
                    productImage.setProductImageID(UUID.randomUUID().toString()); // Generate a unique ID for the image
                    productImage.setProductID(product.getProductID());
                    productImage.setImages(uniqueFileName);
                    newImages.add(productImage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving images: " + e.getMessage());
        }

        // Update the database with new images
        String insertImageQuery = "INSERT INTO ProductImage (ProductID, Image) VALUES (?, ?)";
        for (ProductImage img : newImages) {
            jdbcTemplate.update(insertImageQuery, img.getProductID(), img.getImages());
        }

        // Update product information in the database
        String updateProductQuery = "UPDATE Products SET CategoryID = ?, BrandID = ?, ProductName = ?, ProductDescription = ?, UnitPrice = ?, DateAdded = ?, Discount = ?, AverageRating = ? WHERE ProductID = ?";
        jdbcTemplate.update(updateProductQuery,
                product.getCategoryID(),
                product.getBrandID(),
                product.getProductName(),
                product.getProductDescription(),
                product.getUnitPrice(),
                product.getDateAdded(),
                product.getDiscount(),
                product.getAverageRating(),
                product.getProductID()
        );
    }

    @Override
    public void deleteById(String id) {
        // Kiểm tra xem sản phẩm đã được bán hay chưa bằng cách kiểm tra bảng OrderDetails
        String checkSoldQuery = "SELECT COUNT(*) FROM OrderDetails WHERE ProductID = ?";
        int count = jdbcTemplate.queryForObject(checkSoldQuery, new Object[]{id}, Integer.class);

        if (count > 0) {
            throw new RuntimeException("Product has been sold and cannot be deleted.");
        }

        // Xóa các bản ghi liên quan trong bảng Inventory trước khi xóa sản phẩm
        String deleteInventoryQuery = "DELETE FROM Inventory WHERE ProductID = ?";
        jdbcTemplate.update(deleteInventoryQuery, id);

        // Tiếp tục xóa các bản ghi liên quan khác
        String deleteCategoryDetailsQuery = "DELETE FROM CategoryDetails WHERE ProductID = ?";
        jdbcTemplate.update(deleteCategoryDetailsQuery, id);

        String deleteImagesQuery = "DELETE FROM ProductImage WHERE ProductID = ?";
        jdbcTemplate.update(deleteImagesQuery, id);

        // Cuối cùng, xóa sản phẩm khỏi bảng Products
        String deleteProductQuery = "DELETE FROM Products WHERE ProductID = ?";
        jdbcTemplate.update(deleteProductQuery, id);
    }

    @Override
    public ProductsClient findById(String id) {
        // Truy vấn thông tin sản phẩm
        String productQuery = "SELECT ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, DateAdded, Discount "
                + "FROM Products WHERE ProductID = ?";

        ProductsClient product = jdbcTemplate.queryForObject(productQuery, new Object[]{id}, new RowMapper<ProductsClient>() {
            @Override
            public ProductsClient mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ProductsClient(
                        rs.getString("ProductID"),
                        rs.getInt("CategoryID"),
                        rs.getInt("BrandID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getBigDecimal("UnitPrice"),
                        new ArrayList<>(), // Khởi tạo danh sách hình ảnh rỗng
                        rs.getDate("DateAdded"),
                        rs.getBigDecimal("Discount"),
                        null,
                        0
                );
            }
        });

        // Truy vấn hình ảnh của sản phẩm
        String imageQuery = "SELECT ProductImageID, ProductID, Image "
                + "FROM ProductImage WHERE ProductID = ?";

        List<ProductImage> images = jdbcTemplate.query(imageQuery, new Object[]{id}, new RowMapper<ProductImage>() {
            @Override
            public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ProductImage(
                        rs.getString("ProductImageID"),
                        rs.getString("ProductID"),
                        rs.getString("Image") // Sử dụng cột "Image" như đường dẫn hình ảnh
                );
            }
        });

        // Cập nhật danh sách hình ảnh cho sản phẩm
        product.setImages(images);

        return product;
    }

    @Override
    public List<ProductsClient> findByCategoryId(int categoryId) {
        String query = "SELECT * FROM Products WHERE CategoryID = ?";
        return jdbcTemplate.query(query, new Object[]{categoryId}, new BeanPropertyRowMapper<>(ProductsClient.class));
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

    @Override
    public int getTotalProducts() {
        String query = "SELECT COUNT(*) FROM Products";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    @Override
    public List<ProductsClient> findPaginated(int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page number or page size");
        }

        String query = "SELECT   \n" +
"                       p.ProductID,   \n" +
"                       p.CategoryID,   \n" +
"                       p.BrandID,   \n" +
"                       p.ProductName,   \n" +
"                       p.ProductDescription,   \n" +
"                       p.UnitPrice,   \n" +
"                       p.DateAdded,   \n" +
"                       p.Discount,   \n" +
"                       COALESCE(avgFeedback.AverageRating, 0) AS AverageRating,    \n" +
"                       COALESCE(SUM(od.Quantity), 0) AS TotalQuantitySold  \n" +
"                   FROM   \n" +
"                       Products p  \n" +
"                   LEFT JOIN   \n" +
"                       (  \n" +
"                           SELECT   \n" +
"                               f.ProductID,   \n" +
"                               AVG(CAST(f.Rating AS FLOAT)) AS AverageRating  \n" +
"                           FROM   \n" +
"                               Feedback f  \n" +
"                           GROUP BY   \n" +
"                               f.ProductID  \n" +
"                       ) AS avgFeedback ON p.ProductID = avgFeedback.ProductID  \n" +
"                   LEFT JOIN   \n" +
"                       OrderDetails od ON p.ProductID = od.ProductID  \n" +
"                   LEFT JOIN   \n" +
"                       Orders o ON od.OrderID = o.OrderID  \n" +
"                   WHERE   \n" +
"                       o.OrderStatus = 'Completed' OR o.OrderStatus IS NULL   \n" +
"                   GROUP BY   \n" +
"                       p.ProductID,   \n" +
"                       p.CategoryID,   \n" +
"                       p.BrandID,   \n" +
"                       p.ProductName,   \n" +
"                       p.ProductDescription,   \n" +
"                       p.UnitPrice,   \n" +
"                       p.DateAdded,   \n" +
"                       p.Discount,  \n" +
"                       avgFeedback.AverageRating  \n" +
"                   ORDER BY   \n" +
"                       p.ProductID   \n" +
"                   OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        int offset = (page - 1) * pageSize;

        Map<String, ProductsClient> productMap = new HashMap<>();

        jdbcTemplate.query(query, new Object[]{offset, pageSize}, rs -> {
            String productId = rs.getString("ProductID");

            // Kiểm tra nếu sản phẩm đã tồn tại trong Map
            ProductsClient product = productMap.get(productId);
            if (product == null) {
                // Nếu sản phẩm chưa tồn tại, tạo mới sản phẩm
                product = new ProductsClient();
                product.setProductID(productId);
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setBrandID(rs.getInt("BrandID"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                product.setDateAdded(rs.getDate("DateAdded"));
                product.setDiscount(rs.getBigDecimal("Discount"));
                product.setAverageRating(rs.getBigDecimal("averageRating") != null ? rs.getBigDecimal("averageRating") : BigDecimal.ZERO);
                product.setTotalQuantitySold(rs.getInt("TotalQuantitySold")); // Thiết lập số lượng đã bán

                productMap.put(productId, product);
            }
        });

        // Lấy hình ảnh cho từng sản phẩm
        for (ProductsClient product : productMap.values()) {
            String imageQuery = "SELECT ProductImageID, ProductID, Image "
                    + "FROM ProductImage WHERE ProductID = ?";

            List<ProductImage> images = jdbcTemplate.query(imageQuery, new Object[]{product.getProductID()}, new RowMapper<ProductImage>() {
                @Override
                public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ProductImage(
                            rs.getString("ProductImageID"),
                            rs.getString("ProductID"),
                            rs.getString("Image")
                    );
                }
            });

            product.setImages(images);
        }

        return new ArrayList<>(productMap.values());
    }

    @Override
    public List<ProductsClient> findByCategory(int categoryID, int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page number or page size");
        }

        String query = "SELECT \n"
                + "    p.ProductID, \n"
                + "    p.CategoryID, \n"
                + "    p.BrandID, \n"
                + "    p.ProductName, \n"
                + "    p.ProductDescription, \n"
                + "    p.UnitPrice, \n"
                + "    p.DateAdded, \n"
                + "    p.Discount, \n"
                + "    COALESCE(avgFeedback.AverageRating, 0) AS AverageRating,  \n"
                + "    COALESCE(SUM(od.Quantity), 0) AS TotalQuantitySold \n"
                + "FROM \n"
                + "    Products p\n"
                + "LEFT JOIN \n"
                + "    (SELECT \n"
                + "        f.ProductID, \n"
                + "        AVG(CAST(f.Rating AS FLOAT)) AS AverageRating \n"
                + "    FROM \n"
                + "        Feedback f \n"
                + "    GROUP BY \n"
                + "        f.ProductID) AS avgFeedback ON p.ProductID = avgFeedback.ProductID \n"
                + "LEFT JOIN \n"
                + "    OrderDetails od ON p.ProductID = od.ProductID \n"
                + "LEFT JOIN \n"
                + "    Orders o ON od.OrderID = o.OrderID \n"
                + "WHERE \n"
                + "    (o.OrderStatus = 'Completed' OR o.OrderStatus IS NULL) \n"
                + "    AND p.CategoryID = ? -- Điều kiện lọc theo CategoryID\n"
                + "GROUP BY \n"
                + "    p.ProductID, \n"
                + "    p.CategoryID, \n"
                + "    p.BrandID, \n"
                + "    p.ProductName, \n"
                + "    p.ProductDescription, \n"
                + "    p.UnitPrice, \n"
                + "    p.DateAdded, \n"
                + "    p.Discount, \n"
                + "    avgFeedback.AverageRating \n"
                + "ORDER BY \n"
                + "    p.ProductID \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        int offset = (page - 1) * pageSize;

        List<ProductsClient> products = jdbcTemplate.query(query, new Object[]{categoryID, offset, pageSize}, new RowMapper<ProductsClient>() {
            @Override
            public ProductsClient mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProductsClient product = new ProductsClient();
                product.setProductID(rs.getString("ProductID"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setBrandID(rs.getInt("BrandID"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductDescription(rs.getString("ProductDescription"));
                product.setUnitPrice(rs.getBigDecimal("UnitPrice"));
                product.setDateAdded(rs.getDate("DateAdded"));
                product.setDiscount(rs.getBigDecimal("Discount"));
                product.setAverageRating(rs.getBigDecimal("averageRating") != null ? rs.getBigDecimal("averageRating") : BigDecimal.ZERO);
                product.setTotalQuantitySold(rs.getInt("TotalQuantitySold")); // Thiết lập số lượng đã bán
                product.setImages(new ArrayList<>()); // Khởi tạo danh sách hình ảnh rỗng

                return product;
            }
        });

        // Cập nhật danh sách hình ảnh cho từng sản phẩm
        for (ProductsClient product : products) {
            String imageQuery = "SELECT ProductImageID, ProductID, Image FROM ProductImage WHERE ProductID = ?";

            List<ProductImage> images = jdbcTemplate.query(imageQuery, new Object[]{product.getProductID()}, new RowMapper<ProductImage>() {
                @Override
                public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ProductImage(
                            rs.getString("ProductImageID"),
                            rs.getString("ProductID"),
                            rs.getString("Image")
                    );
                }
            });

            product.setImages(images);
        }

        return products;
    }

    @Override
    public int countByCategory(int categoryID) {
        String query = "SELECT COUNT(*) FROM Products WHERE CategoryID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{categoryID}, Integer.class);
    }

    @Override
    public List<ProductsClient> searchByKeyword(String keyword) {
        String query = "SELECT \n"
                + "    p.ProductID, \n"
                + "    p.CategoryID, \n"
                + "    p.BrandID, \n"
                + "    p.ProductName, \n"
                + "    p.ProductDescription,  \n"
                + "    p.UnitPrice, \n"
                + "    p.DateAdded, \n"
                + "    p.Discount,  \n"
                + "    COALESCE(AVG(CAST(f.Rating AS FLOAT)), 0) AS AverageRating,   \n"
                + "    COUNT(f.ProductID) AS FeedbackCount,  \n"
                + "    COALESCE(SUM(od.Quantity), 0) AS TotalQuantitySold,\n"
                + "    (SELECT STRING_AGG(pi.Image, ', ')  \n"
                + "     FROM ProductImage pi \n"
                + "     WHERE pi.ProductID = p.ProductID) AS Images  \n"
                + "FROM \n"
                + "    Products p  \n"
                + "LEFT JOIN \n"
                + "    Feedback f ON p.ProductID = f.ProductID   \n"
                + "LEFT JOIN \n"
                + "    OrderDetails od ON p.ProductID = od.ProductID\n"
                + "LEFT JOIN \n"
                + "    Orders o ON od.OrderID = o.OrderID AND o.OrderStatus = 'Completed'\n"
                + "WHERE \n"
                + "    p.ProductName COLLATE Latin1_General_CI_AI LIKE ? \n"
                + "    OR SOUNDEX(p.ProductName COLLATE Latin1_General_CI_AI) = SOUNDEX(?)\n"
                + "GROUP BY \n"
                + "    p.ProductID, \n"
                + "    p.CategoryID, \n"
                + "    p.BrandID, \n"
                + "    p.ProductName, \n"
                + "    p.ProductDescription,  \n"
                + "    p.UnitPrice, \n"
                + "    p.DateAdded, \n"
                + "    p.Discount";

        List<ProductsClient> products = jdbcTemplate.query(query, new Object[]{"%" + keyword + "%", keyword}, new RowMapper<ProductsClient>() {
            @Override
            public ProductsClient mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ProductsClient(
                        rs.getString("ProductID"),
                        rs.getInt("CategoryID"),
                        rs.getInt("BrandID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getBigDecimal("UnitPrice"),
                        new ArrayList<>(), // Khởi tạo danh sách hình ảnh rỗng
                        rs.getDate("DateAdded"),
                        rs.getBigDecimal("Discount"),
                        rs.getBigDecimal("averageRating") != null ? rs.getBigDecimal("averageRating") : BigDecimal.ZERO,
                        rs.getInt("TotalQuantitySold")
                );
            }
        });

        // Cập nhật danh sách hình ảnh cho từng sản phẩm
        for (ProductsClient product : products) {
            String imageQuery = "SELECT ProductImageID, ProductID, Image "
                    + "FROM ProductImage WHERE ProductID = ?";

            List<ProductImage> images = jdbcTemplate.query(imageQuery, new Object[]{product.getProductID()}, new RowMapper<ProductImage>() {
                @Override
                public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return new ProductImage(
                            rs.getString("ProductImageID"),
                            rs.getString("ProductID"),
                            rs.getString("Image")
                    );
                }
            });

            product.setImages(images);
        }

        return products;
    }

    @Override
    public String findClosestMatch(String keyword) {
        String query = "SELECT ProductName FROM Products";
        List<String> allProductNames = jdbcTemplate.queryForList(query, String.class);
        String closestMatch = null;
        double maxSimilarity = 0.0;

        String normalizedKeyword = removeAccents(keyword.toLowerCase());

        System.out.println("Searching for closest match to: " + normalizedKeyword);

        for (String productName : allProductNames) {
            String normalizedProductName = removeAccents(productName.toLowerCase());

            double similarity = calculateFuzzySimilarity(normalizedKeyword, normalizedProductName);

            System.out.println("Comparing with: " + productName + ", similarity: " + similarity);

            if (similarity > maxSimilarity) {
                maxSimilarity = similarity;
                closestMatch = productName;
            }
        }

        System.out.println("Closest match found: " + closestMatch + ", with similarity: " + maxSimilarity);

        // Adjust the threshold
        double threshold = 0.4; // 40% similarity
        if (maxSimilarity >= threshold) {
            System.out.println("Close match found above threshold: " + threshold);
            return closestMatch;
        } else {
            System.out.println("No close match found (threshold: " + threshold + ")");
            return null;
        }
    }

    private double calculateFuzzySimilarity(String keyword, String productName) {
        // Split the product name into words
        String[] productWords = productName.split("\\s+");

        // Find the best matching word in the product name
        double bestWordSimilarity = 0.0;
        for (String word : productWords) {
            double wordSimilarity = wordSimilarity(keyword, word);
            if (wordSimilarity > bestWordSimilarity) {
                bestWordSimilarity = wordSimilarity;
            }
        }

        // Calculate overall similarity
        double overallSimilarity = 0.7 * bestWordSimilarity + 0.3 * stringSimilarity(keyword, productName);

        return overallSimilarity;
    }

    private double wordSimilarity(String s1, String s2) {
        int distance = levenshteinDistance(s1, s2);
        int maxLength = Math.max(s1.length(), s2.length());
        return 1.0 - ((double) distance / maxLength);
    }

    private double stringSimilarity(String s1, String s2) {
        int distance = levenshteinDistance(s1, s2);
        int maxLength = Math.max(s1.length(), s2.length());
        return 1.0 - ((double) distance / maxLength);
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
    public boolean isProductSold(String productId) {
        String sql = "SELECT COUNT(*) FROM OrderDetails WHERE ProductID = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{productId}, Integer.class);
        return count != null && count > 0;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ProductsClient> searchByProductName(String productName, int page, int pageSize) {
        if (page < 1 || pageSize <= 0) {
            throw new IllegalArgumentException("Invalid page number or page size");
        }

        String query = "SELECT \n"
                + "    p.ProductID, \n"
                + "    p.CategoryID, \n"
                + "    p.BrandID, \n"
                + "    p.ProductName, \n"
                + "    p.ProductDescription, \n"
                + "    p.UnitPrice, \n"
                + "    p.DateAdded, \n"
                + "    p.Discount, \n"
                + "    COALESCE(avgFeedback.AverageRating, 0) AS AverageRating,  \n"
                + "    COALESCE(SUM(od.Quantity), 0) AS TotalQuantitySold \n"
                + "FROM \n"
                + "    Products p\n"
                + "LEFT JOIN \n"
                + "    (SELECT \n"
                + "        f.ProductID, \n"
                + "        AVG(CAST(f.Rating AS FLOAT)) AS AverageRating \n"
                + "    FROM \n"
                + "        Feedback f \n"
                + "    GROUP BY \n"
                + "        f.ProductID) AS avgFeedback ON p.ProductID = avgFeedback.ProductID \n"
                + "LEFT JOIN \n"
                + "    OrderDetails od ON p.ProductID = od.ProductID \n"
                + "LEFT JOIN \n"
                + "    Orders o ON od.OrderID = o.OrderID \n"
                + "WHERE \n"
                + "    (o.OrderStatus = 'Completed' OR o.OrderStatus IS NULL) \n"
                + "    AND p.ProductName LIKE ? -- Điều kiện tìm kiếm theo ProductName\n"
                + "GROUP BY \n"
                + "    p.ProductID, \n"
                + "    p.CategoryID, \n"
                + "    p.BrandID, \n"
                + "    p.ProductName, \n"
                + "    p.ProductDescription, \n"
                + "    p.UnitPrice, \n"
                + "    p.DateAdded, \n"
                + "    p.Discount, \n"
                + "    avgFeedback.AverageRating \n"
                + "ORDER BY \n"
                + "    p.ProductID \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        String searchKeyword = "%" + productName + "%";
        int offset = (page - 1) * pageSize;

        List<ProductsClient> products = jdbcTemplate.query(query, new Object[]{searchKeyword, offset, pageSize},
                (rs, rowNum) -> new ProductsClient(
                        rs.getString("ProductID"),
                        rs.getInt("CategoryID"),
                        rs.getInt("BrandID"),
                        rs.getString("ProductName"),
                        rs.getString("ProductDescription"),
                        rs.getBigDecimal("UnitPrice"),
                        new ArrayList<>(), // Initialize empty image list
                        rs.getDate("DateAdded"),
                        rs.getBigDecimal("Discount"),
                        rs.getBigDecimal("averageRating") != null ? rs.getBigDecimal("averageRating") : BigDecimal.ZERO,
                        rs.getInt("TotalQuantitySold")
                )
        );

        // Fetch and set images for each product
        for (ProductsClient product : products) {
            String imageQuery = "SELECT ProductImageID, ProductID, Image "
                    + "FROM ProductImage WHERE ProductID = ?";

            List<ProductImage> images = jdbcTemplate.query(imageQuery,
                    new Object[]{product.getProductID()},
                    (rs, rowNum) -> new ProductImage(
                            rs.getString("ProductImageID"),
                            rs.getString("ProductID"),
                            rs.getString("Image")
                    )
            );

            product.setImages(images);
        }

        return products;
    }

    @Override
    public int countByKeyword(String keyword) {
        String query = "SELECT COUNT(*) FROM Products WHERE ProductName LIKE ?";
        return jdbcTemplate.queryForObject(query, new Object[]{"%" + keyword + "%"}, Integer.class);
    }

}
