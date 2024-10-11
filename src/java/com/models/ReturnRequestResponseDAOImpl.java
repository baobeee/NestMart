package com.models;

import com.models.ReturnRequestResponse;
import com.models.ReturnRequestResponseDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ReturnRequestResponseDAOImpl implements ReturnRequestResponseDAO {

    private JdbcTemplate jdbcTemplate;

    public ReturnRequestResponseDAOImpl() {
    }

    public ReturnRequestResponseDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

 @Override
    public List<Orders> getOrdersWithReturnRequests() {
        String sql = "SELECT o.*, c.FullName AS CustomerName, c.Email AS CustomerEmail, " +
                     "c.PhoneNumber AS CustomerPhone, c.Address AS CustomerAddress, " +
                     "s.FullName AS ShipperName, s.Email AS ShipperEmail, " +
                     "s.PhoneNumber AS ShipperPhone, s.Address AS ShipperAddress, " +
                     "od.ProductID, p.ProductName, od.Quantity, od.UnitPrice, od.TotalPrice " +
                     "FROM Orders o " +
                     "JOIN Accounts c ON o.CustomerID = c.AccountID " +
                     "LEFT JOIN Accounts s ON o.ShipperID = s.AccountID " +
                     "JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                     "JOIN Products p ON od.ProductID = p.ProductID " +
                     "WHERE o.OrderStatus IN ('Return Requested')";
        
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }




@Override
public void addReturnRequestResponse(ReturnRequestResponse response) {
    String sql = "INSERT INTO ReturnRequestResponse (OrderID, EmployeeID, ReturnRequestResponseDate, Message) " +
                 "VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(sql, response.getOrderID(), response.getEmployeeID(),
                        Timestamp.valueOf(response.getReturnRequestResponseDate()), response.getMessage());
    System.out.println("Inserted into ReturnRequestResponse table successfully");
}
   @Override
    public List<Orders> getCompletedReturnOrders() {
        String sql = "SELECT o.*, c.FullName AS CustomerName, c.Email AS CustomerEmail, " +
                     "c.PhoneNumber AS CustomerPhone, c.Address AS CustomerAddress, " +
                     "s.FullName AS ShipperName, s.Email AS ShipperEmail, " +
                     "s.PhoneNumber AS ShipperPhone, s.Address AS ShipperAddress, " +
                     "od.ProductID, p.ProductName, od.Quantity, od.UnitPrice, od.TotalPrice " +
                     "FROM Orders o " +
                     "JOIN Accounts c ON o.CustomerID = c.AccountID " +
                     "LEFT JOIN Accounts s ON o.ShipperID = s.AccountID " +
                     "JOIN OrderDetails od ON o.OrderID = od.OrderID " +
                     "JOIN Products p ON od.ProductID = p.ProductID " +
                     "WHERE o.OrderStatus IN ('Return Completed')";
        return jdbcTemplate.query(sql, new OrderRowMapper());
    }
   @Override
    public void deleteCompletedReturnOrder(int orderID) {
        String sql = "DELETE FROM Orders WHERE OrderID = ? AND OrderStatus = 'Return Completed'";
        int rowsAffected = jdbcTemplate.update(sql, orderID);
        if (rowsAffected > 0) {
            System.out.println("Deleted completed return order with ID: " + orderID);
        } else {
            System.out.println("No completed return order found with ID: " + orderID);
        }
    }
 @Override
    public void updateOrderStatus(int orderID, String newOrderStatus, String newReturnRequestStatus) {
        String sql = "UPDATE Orders SET OrderStatus = ?, ReturnRequestStatus = ? WHERE OrderID = ?";
        jdbcTemplate.update(sql, newOrderStatus, newReturnRequestStatus, orderID);
        System.out.println("Updated Order status and Return Request status successfully");
    }



private static class OrderRowMapper implements RowMapper<Orders> {
    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders order = new Orders();
        order.setOrderID(rs.getInt("OrderID"));
        order.setCustomerName(rs.getString("CustomerName"));
        order.setCustomerEmail(rs.getString("CustomerEmail"));
        
        Timestamp orderTimestamp = rs.getTimestamp("OrderDate");
        if (orderTimestamp != null) {
            order.setOrderDate(orderTimestamp.toLocalDateTime());
        }
            order.setFormattedOrderDate(rs.getString("OrderDate"));

        Timestamp returnRequestTimestamp = rs.getTimestamp("ReturnRequestDate");
        if (returnRequestTimestamp != null) {
            order.setReturnRequestDate(returnRequestTimestamp.toLocalDateTime());
        }
            order.setFormattedReturnRequestDate(rs.getString("ReturnRequestDate"));

        order.setTotalAmount(rs.getBigDecimal("TotalAmount"));
        order.setPaymentMethod(rs.getString("PaymentMethod"));
        order.setShippingAddress(rs.getString("ShippingAddress"));
        order.setReturnRequestReason(rs.getString("ReturnRequestReason"));

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        OrderDetails orderDetail = new OrderDetails();
        orderDetail.setProductID(rs.getString("ProductID"));
        orderDetail.setQuantity(rs.getInt("Quantity"));
        orderDetail.setUnitPrice(rs.getBigDecimal("UnitPrice"));
        orderDetail.setTotalPrice(rs.getBigDecimal("TotalPrice"));

        Products product = new Products();
        product.setProductID(rs.getString("ProductID"));
        product.setProductName(rs.getString("ProductName"));

        orderDetail.setProduct(product);
        orderDetailsList.add(orderDetail);
        
        order.setOrderDetails(orderDetailsList);

        return order;
    }
}



    // Getter and Setter for JdbcTemplate
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
