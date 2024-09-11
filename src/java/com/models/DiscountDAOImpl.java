package com.models;

import java.io.File;
import java.io.IOException;
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
import javax.servlet.http.Part;
import javax.sql.DataSource;
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

    public DiscountDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public List<Discount> findAll() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT DiscountID, DiscountName, DiscountType, Description, StartDate, EndDate, Image FROM Discounts";
        List<Discount> discountList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); // Kiểm tra số lượng bản ghi

        for (Map row : rows) {
            Discount c = new Discount((int) row.get("DiscountID"),
                    (String) row.get("DiscountName"),
                    (String) row.get("DiscountType"),
                    (Float) row.get("DiscountValue"),
                    (String) row.get("Description"),
                    (Date) row.get("StartDate"),
                    (Date) row.get("EndDate"),
                    (String) row.get("Image"));
            discountList.add(c);
        }
        System.out.println(discountList);
        return discountList;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
                    throw new RuntimeException("Tên file không hợp lệ.");
                }

                String relativePath = "/web/assets/admin/images/uploads/discount/";
                String uploadPath = new File(servletContext.getRealPath("")).getParentFile().getParentFile().getAbsolutePath() + relativePath;

                System.out.println("Upload Path: " + uploadPath);

                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    boolean dirsCreated = uploadDir.mkdirs();
                    if (dirsCreated) {
                        System.out.println("Directories created: " + uploadDir.getAbsolutePath());
                    } else {
                        System.out.println("Failed to create directories: " + uploadDir.getAbsolutePath());
                    }
                }

                String filePath = uploadPath + File.separator + fileName;
                System.out.println("File Path: " + filePath);

                imageFile.transferTo(new File(filePath));
                System.out.println("File saved successfully: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
            }
        } else {
            System.out.println("No image file provided or file is empty.");
        }
        String query = "INSERT INTO Discounts (DiscountName, DiscountType, DiscountValue, Description, StartDate, EndDate, Image) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query,
                discount.getDiscountName(),
                discount.getDiscountType(),
                discount.getDiscountValue(),
                discount.getDescription(),
                formattedStartDate,
                formattedEndDate,
                fileName // Lưu tên file ảnh vào cơ sở dữ liệu
        );
    }

   @Override
public void update(Discount discount, MultipartFile imageFile, ServletContext servletContext) {
    // Lấy tên file hiện tại từ discount
    String fileName = discount.getImage(); 

    if (imageFile != null && !imageFile.isEmpty()) {
        try {
            // Lấy tên file mới từ MultipartFile
            fileName = Paths.get(imageFile.getOriginalFilename()).getFileName().toString();
            if (fileName == null || fileName.isEmpty()) {
                throw new RuntimeException("Tên file không hợp lệ.");
            }

            // Đường dẫn lưu file ảnh tương đối
            String relativePath = "assets/admin/images/uploads/discount/";
            String uploadPath = servletContext.getRealPath("") + File.separator + relativePath;

            // Đảm bảo thư mục tồn tại
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                if (uploadDir.mkdirs()) {
                    System.out.println("Directories created: " + uploadDir.getAbsolutePath());
                } else {
                    throw new RuntimeException("Không thể tạo thư mục: " + uploadDir.getAbsolutePath());
                }
            }

            // Đường dẫn đầy đủ của file
            String filePath = uploadPath + File.separator + fileName;
            System.out.println("File path: " + filePath);

            // Xóa tệp cũ nếu tồn tại
            File oldFile = new File(uploadPath + File.separator + discount.getImage());
            if (oldFile.exists() && !oldFile.delete()) {
                System.out.println("Không thể xóa tệp cũ: " + oldFile.getAbsolutePath());
            }

            // Lưu file ảnh vào thư mục
            imageFile.transferTo(new File(filePath));
            System.out.println("File saved successfully: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lưu ảnh: " + e.getMessage());
        }
    } else {
        // Nếu không có tệp mới, giữ lại tên file hiện tại từ cơ sở dữ liệu
        if (fileName == null || fileName.isEmpty()) {
            fileName = jdbcTemplate.queryForObject(
                "SELECT Image FROM Discounts WHERE DiscountID = ?", 
                new Object[]{discount.getDiscountID()}, 
                String.class
            );
        }
    }

    // Cập nhật discount trong cơ sở dữ liệu với tên file ảnh (cũ hoặc mới)
    String query = "UPDATE Discounts SET DiscountName = ?, DiscountType = ?, DiscountValue = ?, Description = ?, StartDate = ?, EndDate = ?, Image = ? WHERE DiscountID = ?";
    jdbcTemplate.update(query,
            discount.getDiscountName(),
            discount.getDiscountType(),
            discount.getDiscountValue(),
            discount.getDescription(),
            discount.getStartDate(),
            discount.getEndDate(),
            fileName,  // sử dụng fileName đã được cập nhật hoặc lấy lại từ database
            discount.getDiscountID()
    );
}

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM Discounts WHERE DiscountID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Discount findById(int id) {
        String query = "SELECT * FROM Discounts WHERE DiscountiD = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Discount>() {
            @Override
            public Discount mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Discount(
                        rs.getInt("DiscountID"),
                        rs.getString("DiscountName"),
                        rs.getString("DiscountType"),
                        rs.getFloat("DiscountValue"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("Image")
                );

            }
        }
        );
    }
}
