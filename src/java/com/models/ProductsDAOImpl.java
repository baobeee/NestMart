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
        System.out.println("Database connection successfulzz.");
    } catch (Exception e) {
        System.out.println("Database connection failed: " + e.getMessage());
    }

    String query = "SELECT p.ProductID, p.CategoryID, p.BrandID, p.ProductName, p.ProductDescription, p.UnitPrice, " +
                   "pi.Image AS ProductImage, p.DateAdded, p.Discount " +
                   "FROM Products p " +
                   "LEFT JOIN ProductImage pi ON p.ProductID = pi.ProductID";

    List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

    System.out.println("Rows returned: " + rows.size());

    Map<String, Products> productMap = new HashMap<>();

    for (Map<String, Object> row : rows) {
        String productId = (String) row.get("ProductID");

        // Tạo hoặc lấy sản phẩm từ bản đồ
        Products product = productMap.get(productId);
        if (product == null) {
            product = new Products(
                    productId,
                    (Integer) row.get("CategoryID"),
                    (Integer) row.get("BrandID"),
                    (String) row.get("ProductName"),
                    (String) row.get("ProductDescription"),
                    (BigDecimal) row.get("UnitPrice"),
                    new ArrayList<>(), // Khởi tạo danh sách hình ảnh
                    (Date) row.get("DateAdded"),
                    (BigDecimal) row.get("Discount")
            );
            productMap.put(productId, product);
        }

        String images = (String) row.get("ProductImage");
        if (images != null) {
            ProductImage productImage = new ProductImage();
            productImage.setImages(images);

            product.getImages().add(productImage);
        }
    }

    List<Products> productList = new ArrayList<>(productMap.values());

    System.out.println(productList);
    return productList;
}
@Override
public void save(Products product, List<MultipartFile> imageFiles, ServletContext servletContext) {
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

   

    String query = "SELECT ProductID FROM Products WHERE ProductID = ?";
    List<String> existingIds = jdbcTemplate.query(query, new Object[]{product.getProductID()},
            (rs, rowNum) -> rs.getString("ProductID"));

    if (!existingIds.isEmpty()) {
        throw new RuntimeException("Sản phẩm với ID này đã tồn tại.");
    }

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
    String insertProductQuery = "INSERT INTO Products (ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, DateAdded, Discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        jdbcTemplate.update(insertProductQuery,
                product.getProductID(),
                product.getCategoryID(),
                product.getBrandID(),
                product.getProductName(),
                product.getProductDescription(),
                product.getUnitPrice(),
                formattedDateAdded,
                product.getDiscount()
                
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
public void update(Products product, List<MultipartFile> imageFiles, List<String> imagesToDelete, ServletContext servletContext) {
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
    String updateProductQuery = "UPDATE Products SET CategoryID = ?, BrandID = ?, ProductName = ?, ProductDescription = ?, UnitPrice = ?, DateAdded = ?, Discount = ? WHERE ProductID = ?";
    jdbcTemplate.update(updateProductQuery,
            product.getCategoryID(),
            product.getBrandID(),
            product.getProductName(),
            product.getProductDescription(),
            product.getUnitPrice(),
            product.getDateAdded(),
            product.getDiscount(),
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
public Products findById(String id) {
    // Truy vấn thông tin sản phẩm
    String productQuery = "SELECT ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, DateAdded, Discount " +
                          "FROM Products WHERE ProductID = ?";
    
    Products product = jdbcTemplate.queryForObject(productQuery, new Object[]{id}, new RowMapper<Products>() {
        @Override
        public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Products(
                rs.getString("ProductID"),
                rs.getInt("CategoryID"),
                rs.getInt("BrandID"),
                rs.getString("ProductName"),
                rs.getString("ProductDescription"),
                rs.getBigDecimal("UnitPrice"),
                new ArrayList<>(), // Khởi tạo danh sách hình ảnh rỗng
                rs.getDate("DateAdded"),
                rs.getBigDecimal("Discount")
            );
        }
    });
    
    // Truy vấn hình ảnh của sản phẩm
    String imageQuery = "SELECT ProductImageID, ProductID, Image " +
                        "FROM ProductImage WHERE ProductID = ?";
    
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
  @Override
    public int  getTotalProducts() {
        String query = "SELECT COUNT(*) FROM Products";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
@Override
public List<Products> findPaginated(int page, int pageSize) {
    if (page < 1 || pageSize <= 0) {
        throw new IllegalArgumentException("Invalid page number or page size");
    }

    String query = "SELECT ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, DateAdded, Discount " +
                   "FROM Products " +
                   "ORDER BY ProductID " +
                   "OFFSET ? ROWS " +
                   "FETCH NEXT ? ROWS ONLY";
    int offset = (page - 1) * pageSize;

    List<Products> products = jdbcTemplate.query(query, new Object[]{offset, pageSize}, new RowMapper<Products>() {
        @Override
        public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Products(
                rs.getString("ProductID"),
                rs.getInt("CategoryID"),
                rs.getInt("BrandID"),
                rs.getString("ProductName"),
                rs.getString("ProductDescription"),
                rs.getBigDecimal("UnitPrice"),
                new ArrayList<>(), // Khởi tạo danh sách hình ảnh rỗng
                rs.getDate("DateAdded"),
                rs.getBigDecimal("Discount")
            );
        }
    });

    // Cập nhật danh sách hình ảnh cho từng sản phẩm
    for (Products product : products) {
        String imageQuery = "SELECT ProductImageID, ProductID, Image " +
                            "FROM ProductImage WHERE ProductID = ?";
        
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
public List<Products> searchByKeyword(String keyword) {
    String query = "SELECT ProductID, CategoryID, BrandID, ProductName, ProductDescription, UnitPrice, DateAdded, Discount " + // Added space after Discount
                   "FROM Products " +
                   "WHERE ProductName COLLATE Latin1_General_CI_AI LIKE ? " +
                   "OR SOUNDEX(ProductName COLLATE Latin1_General_CI_AI) = SOUNDEX(?)";

    List<Products> products = jdbcTemplate.query(query, new Object[]{"%" + keyword + "%", keyword}, new RowMapper<Products>() {
        @Override
        public Products mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Products(
                rs.getString("ProductID"),
                rs.getInt("CategoryID"),
                rs.getInt("BrandID"),
                rs.getString("ProductName"),
                rs.getString("ProductDescription"),
                rs.getBigDecimal("UnitPrice"),
                new ArrayList<>(), // Initialize empty image list
                rs.getDate("DateAdded"),
                rs.getBigDecimal("Discount")
            );
        }
    });

    // Update image list for each product
    for (Products product : products) {
        String imageQuery = "SELECT ProductImageID, ProductID, Image " +
                            "FROM ProductImage WHERE ProductID = ?";
        
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
    keyword = keyword.toLowerCase();
    
    // Xử lý đặc biệt cho từ viết tắt
    if (keyword.length() <= 5) {
        String exactMatchQuery = "SELECT ProductName FROM Products WHERE LOWER(ProductName) LIKE ?";
        List<String> exactMatches = jdbcTemplate.queryForList(exactMatchQuery, new Object[]{"%" + keyword + "%"}, String.class);
        if (!exactMatches.isEmpty()) {
            return exactMatches.get(0);
        }
        
        // Tìm kiếm dựa trên việc so khớp các ký tự đầu tiên
        String prefixMatchQuery = "SELECT ProductName FROM Products WHERE LOWER(ProductName) LIKE ?";
        List<String> prefixMatches = jdbcTemplate.queryForList(prefixMatchQuery, new Object[]{keyword.charAt(0) + "%"}, String.class);
        
        String bestMatch = null;
        int maxMatchingChars = 0;
        
        for (String productName : prefixMatches) {
            int matchingChars = countMatchingCharacters(keyword, productName.toLowerCase());
            if (matchingChars > maxMatchingChars) {
                maxMatchingChars = matchingChars;
                bestMatch = productName;
            }
        }
        
        if (bestMatch != null) {
            return bestMatch;
        }
    }
    
    // Nếu không tìm thấy kết quả phù hợp, sử dụng phương pháp Levenshtein
    String query = "SELECT ProductName FROM Products";
    List<String> allProductNames = jdbcTemplate.queryForList(query, String.class);
    String closestMatch = null;
    int minDistance = Integer.MAX_VALUE;

    for (String productName : allProductNames) {
        String normalizedProductName = removeAccents(productName.toLowerCase());
        int distance = levenshteinDistance(keyword, normalizedProductName);
        
        if (distance < minDistance) {
            minDistance = distance;
            closestMatch = productName;
        }
    }

    if (minDistance > Math.min(2, keyword.length() - 1)) {
        return null;
    }

    return closestMatch;
}

private int countMatchingCharacters(String keyword, String productName) {
    int count = 0;
    int minLength = Math.min(keyword.length(), productName.length());
    for (int i = 0; i < minLength; i++) {
        if (keyword.charAt(i) == productName.charAt(i)) {
            count++;
        } else {
            break;
        }
    }
    return count;
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

  
}
