/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

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

        String query = "SELECT o.offerID, o.productID, d.discountID,o.offerName, o.description, o.startDate, o.endDate,  p.productName, d.discountName \n"
                + "FROM Offers o \n"
                + "JOIN Products p ON o.productID = p.productID \n"
                + "JOIN Discounts d ON o.discountID = d.discountID";
        List<Offers> offerList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); // Kiểm tra số lượng bản ghi

        for (Map row : rows) {
            Offers c = new Offers(
                    (int) row.get("OfferID"),
                    (String) row.get("ProductID"),
                    (int) row.get("DiscountID"),
                    (String) row.get("OfferName"),
                    (String) row.get("Description"),
                    (Date) row.get("StartDate"),
                    (Date) row.get("EndDate"),
                    (String) row.get("ProductName"),
                    (String) row.get("DiscountName")
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedStartDate = sdf.format(offers.getStartDate());
        String formattedEndDate = sdf.format(offers.getEndDate());
        String query = "INSERT INTO Offers (ProductID, DiscountID, OfferName, Description, StartDate, EndDate) VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query,
                offers.getProductID(),
                offers.getDiscountID(),
                offers.getOfferName(),
                offers.getDescription(),
                formattedStartDate,
                formattedEndDate
        );
    }

    @Override
    public void update(Offers offers) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedStartDate = sdf.format(offers.getStartDate());
        String formattedEndDate = sdf.format(offers.getEndDate());
        String query = "UPDATE Offers SET ProductID = ?, DiscountID = ?, OfferName = ?, Description = ?, StartDate = ?, EndDate = ? WHERE OfferID = ?";
        jdbcTemplate.update(query,
                offers.getProductID(),
                offers.getDiscountID(),
                offers.getOfferName(),
                offers.getDescription(),
                formattedStartDate,
                formattedEndDate,
                offers.getOfferID()
        );
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM Offers WHERE OfferID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Offers findById(int id) {
        String query = "SELECT o.offerID, o.productID, d.discountID,o.offerName, o.description, o.startDate, o.endDate,  p.productName, d.discountName \n"
                + "FROM Offers o \n"
                + "JOIN Products p ON o.productID = p.productID \n"
                + "JOIN Discounts d ON o.discountID = d.discountID WHERE o.OfferID=?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Offers>() {
            @Override
            public Offers mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Offers(
                        rs.getInt("OfferID"),
                        rs.getString("ProductID"),
                        rs.getInt("DiscountID"),
                        rs.getString("OfferName"),
                        rs.getString("Description"),
                        rs.getDate("StartDate"),
                        rs.getDate("EndDate"),
                        rs.getString("ProductName"),
                        rs.getString("DiscountName")
                );
            }
        });
    }
}
