package com.models;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class OrdersDAOImpl implements OrdersDAO {

    private JdbcTemplate jdbcTemplate;

    public OrdersDAOImpl() {
    }

    public OrdersDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Orders> getOrdersByShipper(int shipperID) {
        System.out.println("Getting orders for shipper ID: " + shipperID);
        String query = "SELECT o.*, c.FullName AS CustomerName, c.Email AS CustomerEmail, c.PhoneNumber AS CustomerPhone, c.Address AS CustomerAddress, "
                + "s.FullName AS ShipperName, s.Email AS ShipperEmail, s.PhoneNumber AS ShipperPhone, s.Address AS ShipperAddress "
                + "FROM Orders o "
                + "JOIN Accounts c ON o.CustomerID = c.AccountID "
                + "JOIN Accounts s ON o.ShipperID = s.AccountID "
                + "WHERE o.ShipperID = ?";
        return jdbcTemplate.query(query, new Object[]{shipperID}, new OrderRowMapper());
    }

    @Override
    public void updateOrder(int orderId, String newStatus, String paymentMethod) {
        System.out.println("Updating order ID: " + orderId + " to status: " + newStatus);
        String query = "UPDATE Orders SET OrderStatus = ?, "
                + "TransactionStatus = CASE "
                + "    WHEN ? = 'Approved' THEN 'Refunding' "
                + "    WHEN ? = 'Return Completed' THEN 'Refunded' "
                + "    WHEN PaymentMethod = 'Bank Transfer' AND ? IN ('Confirmed', 'On Delivering', 'Completed') THEN 'Completed' "
                + "    WHEN PaymentMethod = 'Cash' AND ? = 'Completed' THEN 'Completed' "
                + "    WHEN PaymentMethod = 'Cash' AND ? IN ('Confirmed', 'On Delivering') THEN 'Pending' "
                + "    ELSE TransactionStatus "
                + "END, "
                + "ShipperId = CASE WHEN ? = 'Confirmed' THEN ShipperId ELSE ShipperId END, "
                + "DeliveryDate = CASE WHEN ? = 'Completed' THEN CURRENT_TIMESTAMP ELSE DeliveryDate END "
                + "WHERE OrderId = ?";
        int rowsAffected = jdbcTemplate.update(query, newStatus, newStatus, newStatus, newStatus, newStatus, newStatus, newStatus, newStatus, orderId);
        System.out.println("Rows affected: " + rowsAffected);
    }

    @Override
    public List<Orders> searchAndFilterOrders(int shipperID, String orderIdQuery, String searchQuery, String status) {
        System.out.println("Searching and filtering orders for shipper ID: " + shipperID);
        StringBuilder queryBuilder = new StringBuilder(
                "SELECT o.*, c.FullName AS CustomerName, c.Email AS CustomerEmail, c.PhoneNumber AS CustomerPhone, c.Address AS CustomerAddress, "
                + "s.FullName AS ShipperName, s.Email AS ShipperEmail, s.PhoneNumber AS ShipperPhone, s.Address AS ShipperAddress "
                + "FROM Orders o "
                + "JOIN Accounts c ON o.CustomerID = c.AccountID "
                + "JOIN Accounts s ON o.ShipperID = s.AccountID "
                + "WHERE o.ShipperID = ? ");

        List<Object> params = new ArrayList<>();
        params.add(shipperID);

        if (orderIdQuery != null && !orderIdQuery.isEmpty()) {
            queryBuilder.append("AND o.OrderID = ? ");
            params.add(Integer.parseInt(orderIdQuery));
        }

        if (status != null && !status.isEmpty()) {
            queryBuilder.append("AND o.OrderStatus = ? ");
            params.add(status);
        }

        if (searchQuery != null && !searchQuery.isEmpty()) {
            queryBuilder.append("AND c.FullName LIKE ? ");
            params.add("%" + searchQuery + "%");
        }

        String query = queryBuilder.toString();
        return jdbcTemplate.query(query, params.toArray(), new OrderRowMapper());
    }

    private static class OrderRowMapper implements RowMapper<Orders> {

        @Override
        public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
            Orders order = new Orders();
            order.setOrderID(rs.getInt("OrderID"));
            order.setCustomerID(rs.getInt("CustomerID"));
            order.setShipperID(rs.getInt("ShipperID"));
            order.setOrderDate(rs.getObject("OrderDate", LocalDateTime.class));
            order.setFormattedOrderDate(rs.getString("OrderDate"));
            order.setOrderStatus(rs.getString("OrderStatus"));
            order.setTotalAmount(rs.getBigDecimal("TotalAmount"));
            order.setPaymentMethod(rs.getString("PaymentMethod"));
            order.setShippingAddress(rs.getString("ShippingAddress"));
            order.setTransactionDate(rs.getObject("TransactionDate", LocalDateTime.class));
            order.setFormattedTransactionDate(rs.getString("TransactionDate"));
            order.setTransactionStatus(rs.getString("TransactionStatus"));
            order.setTransactionDescription(rs.getString("TransactionDescription"));
            order.setReturnRequestDate(rs.getObject("ReturnRequestDate", LocalDateTime.class));
            order.setFormattedReturnRequestDate(rs.getString("ReturnRequestDate"));
            order.setReturnRequestStatus(rs.getString("ReturnRequestStatus"));
            order.setReturnRequestReason(rs.getString("ReturnRequestReason"));
            order.setDeliveryDate(rs.getDate("DeliveryDate"));
            order.setNotes(rs.getString("Notes"));
            order.setCustomerName(rs.getString("CustomerName"));
            order.setCustomerEmail(rs.getString("CustomerEmail"));
            order.setCustomerPhone(rs.getString("CustomerPhone"));
            order.setCustomerAddress(rs.getString("CustomerAddress"));
            order.setShipperName(rs.getString("ShipperName"));
            order.setShipperEmail(rs.getString("ShipperEmail"));
            order.setShipperPhone(rs.getString("ShipperPhone"));
            order.setShipperAddress(rs.getString("ShipperAddress"));
            // Note: OrderDetails are not being populated here. You may need to fetch them separately if required.

            return order;
        }
    }

    @Override
    public List<Orders> findAllOrders(int customerID) {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT O.OrderID, O.CustomerID, O.OrderDate, O.OrderStatus, "
                + "O.TotalAmount, O.PaymentMethod, O.ShippingAddress, O.TransactionDate, O.TransactionStatus, "
                + "O.DeliveryDate, O.Notes, P.ProductID, P.ProductName, MIN(PI.Image) AS Image, OD.Quantity, "
                + "OD.UnitPrice, OD.TotalPrice, O.TotalAmount, P.Discount, COUNT(F.FeedbackID) AS FeedbackCount "
                + "FROM Orders O JOIN Accounts A ON O.CustomerID = A.AccountID "
                + "JOIN OrderDetails OD ON O.OrderID = OD.OrderID JOIN Products P ON OD.ProductID = P.ProductID "
                + "LEFT JOIN ProductImage PI ON P.ProductID = PI.ProductID LEFT JOIN Feedback F ON F.ProductID = OD.ProductID "
                + "WHERE O.CustomerID = ? "
                + "GROUP BY O.OrderID, O.CustomerID, O.OrderDate, O.OrderStatus, O.TotalAmount, O.PaymentMethod, O.ShippingAddress, "
                + "O.TransactionDate, O.TransactionStatus, O.DeliveryDate, O.Notes, P.ProductID, P.ProductName, OD.Quantity, OD.UnitPrice, OD.TotalPrice, P.Discount "
                + "ORDER BY O.OrderDate DESC, O.OrderID, P.ProductID;";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, customerID);

        System.out.println("Rows returned: " + rows.size());

        Map<Integer, Orders> orderMap = new HashMap<>();

        for (Map<String, Object> rs : rows) {
            int orderId = (int) rs.get("OrderID");
            Orders order = orderMap.get(orderId);
            if (order == null) {
                order = new Orders();
                order.setOrderID(orderId);
                order.setCustomerID((int) rs.get("CustomerID"));

                Timestamp timestampOrderDate = (Timestamp) rs.get("OrderDate");
                LocalDateTime dateTimeOrderDate = timestampOrderDate != null ? timestampOrderDate.toLocalDateTime() : null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTimeOrderDate = (dateTimeOrderDate != null) ? dateTimeOrderDate.format(formatter) : null;
                order.setFormattedOrderDate(formattedDateTimeOrderDate);
                order.setOrderDate(dateTimeOrderDate);

                order.setOrderStatus((String) rs.get("OrderStatus"));
                order.setTotalAmount((BigDecimal) rs.get("TotalAmount"));
                order.setPaymentMethod((String) rs.get("PaymentMethod"));
                order.setShippingAddress((String) rs.get("ShippingAddress"));

                Timestamp timestampTransactionDate = (Timestamp) rs.get("TransactionDate");
                LocalDateTime dateTimeTransactionDate = timestampTransactionDate != null ? timestampTransactionDate.toLocalDateTime() : null;
                String formattedDateTimeTransactionDate = (dateTimeTransactionDate != null) ? dateTimeTransactionDate.format(formatter) : null;
                order.setFormattedTransactionDate(formattedDateTimeTransactionDate);
                order.setTransactionDate(dateTimeTransactionDate);

                order.setTransactionStatus((String) rs.get("TransactionStatus"));
                order.setDeliveryDate((Date) rs.get("DeliveryDate"));
                order.setNotes((String) rs.get("Notes"));

                orderMap.put(orderId, order);
            }

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setQuantity((int) rs.get("Quantity"));
            orderDetails.setUnitPrice((BigDecimal) rs.get("UnitPrice"));
            orderDetails.setTotalPrice((BigDecimal) rs.get("TotalPrice"));

            Products product = new Products();
            product.setProductID((String) rs.get("ProductID"));
            product.setProductName((String) rs.get("ProductName"));
            product.setDiscount((BigDecimal) rs.get("ProductDiscount"));

            List<ProductImage> images = new ArrayList<>();
            String imagePath = (String) rs.get("Image");
            if (imagePath != null) {
                ProductImage image = new ProductImage();
                image.setImages(imagePath);
                images.add(image);
            }
            orderDetails.setImages(images);
            orderDetails.setProduct(product);

            List<OrderDetails> orderDetailsList = order.getOrderDetails();
            if (orderDetailsList == null) {
                orderDetailsList = new ArrayList<>();
                order.setOrderDetails(orderDetailsList);
            }
            orderDetailsList.add(orderDetails);
        }

        return new ArrayList<>(orderMap.values());
    }

   @Override
public List<Orders> getOrdersByStatus(String status, int customerID) {
    String query = "SELECT O.OrderID, O.CustomerID, O.OrderDate, O.OrderStatus, "
            + "O.TotalAmount, O.PaymentMethod, O.ShippingAddress, O.TransactionDate, O.TransactionStatus, "
            + "O.DeliveryDate, O.Notes, P.ProductID, P.ProductName, MIN(PI.Image) AS Image, OD.Quantity, "
            + "OD.UnitPrice, OD.TotalPrice, O.TotalAmount, P.Discount, COUNT(F.FeedbackID) AS FeedbackCount "
            + "FROM Orders O JOIN Accounts A ON O.CustomerID = A.AccountID "
            + "JOIN OrderDetails OD ON O.OrderID = OD.OrderID JOIN Products P ON OD.ProductID = P.ProductID "
            + "LEFT JOIN ProductImage PI ON P.ProductID = PI.ProductID LEFT JOIN Feedback F ON F.ProductID = OD.ProductID "
            + "WHERE O.CustomerID = ? AND O.OrderStatus = ? "
            + "GROUP BY O.OrderID, O.CustomerID, O.OrderDate, O.OrderStatus, O.TotalAmount, O.PaymentMethod, O.ShippingAddress, "
            + "O.TransactionDate, O.TransactionStatus, O.DeliveryDate, O.Notes, P.ProductID, P.ProductName, OD.Quantity, OD.UnitPrice, OD.TotalPrice, P.Discount "
            + "ORDER BY O.OrderDate DESC, O.OrderID, P.ProductID";

    List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, customerID, status);

        System.out.println("Rows returned: " + rows.size());

        Map<Integer, Orders> orderMap = new HashMap<>();

        for (Map<String, Object> rs : rows) {
            int orderId = (int) rs.get("OrderID");
            Orders order = orderMap.get(orderId);
            if (order == null) {
                order = new Orders();
                order.setOrderID(orderId);
                order.setCustomerID((int) rs.get("CustomerID"));

                Timestamp timestampOrderDate = (Timestamp) rs.get("OrderDate");
                LocalDateTime dateTimeOrderDate = timestampOrderDate != null ? timestampOrderDate.toLocalDateTime() : null;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTimeOrderDate = (dateTimeOrderDate != null) ? dateTimeOrderDate.format(formatter) : null;
                order.setFormattedOrderDate(formattedDateTimeOrderDate);
                order.setOrderDate(dateTimeOrderDate);

                order.setOrderStatus((String) rs.get("OrderStatus"));
                order.setTotalAmount((BigDecimal) rs.get("TotalAmount"));
                order.setPaymentMethod((String) rs.get("PaymentMethod"));
                order.setShippingAddress((String) rs.get("ShippingAddress"));

                Timestamp timestampTransactionDate = (Timestamp) rs.get("TransactionDate");
                LocalDateTime dateTimeTransactionDate = timestampTransactionDate != null ? timestampTransactionDate.toLocalDateTime() : null;
                String formattedDateTimeTransactionDate = (dateTimeTransactionDate != null) ? dateTimeTransactionDate.format(formatter) : null;
                order.setFormattedTransactionDate(formattedDateTimeTransactionDate);
                order.setTransactionDate(dateTimeTransactionDate);

                order.setTransactionStatus((String) rs.get("TransactionStatus"));
                order.setDeliveryDate((Date) rs.get("DeliveryDate"));
                order.setNotes((String) rs.get("Notes"));

                orderMap.put(orderId, order);
            }

            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setQuantity((int) rs.get("Quantity"));
            orderDetails.setUnitPrice((BigDecimal) rs.get("UnitPrice"));
            orderDetails.setTotalPrice((BigDecimal) rs.get("TotalPrice"));

            Products product = new Products();
            product.setProductID((String) rs.get("ProductID"));
            product.setProductName((String) rs.get("ProductName"));
            product.setDiscount((BigDecimal) rs.get("ProductDiscount"));

            List<ProductImage> images = new ArrayList<>();
            String imagePath = (String) rs.get("Image");
            if (imagePath != null) {
                ProductImage image = new ProductImage();
                image.setImages(imagePath);
                images.add(image);
            }
            orderDetails.setImages(images);
            orderDetails.setProduct(product);

            List<OrderDetails> orderDetailsList = order.getOrderDetails();
            if (orderDetailsList == null) {
                orderDetailsList = new ArrayList<>();
                order.setOrderDetails(orderDetailsList);
            }
            orderDetailsList.add(orderDetails);
        }

        return new ArrayList<>(orderMap.values());
    }

    @Override
    public Integer getFeedback(String productID, int customerID) {
        String query = "SELECT TOP 1 f.FeedbackID FROM Feedback f "
                + "JOIN OrderDetails od ON f.ProductID = od.ProductID "
                + "JOIN Orders o ON od.OrderID = o.OrderID "
                + "JOIN Accounts a ON o.CustomerID = a.AccountID "
                + "WHERE f.ProductID = ? "
                + "AND o.CustomerID = ? AND OrderStatus = 'Completed'";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{productID, customerID}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Orders get(String id) {
        String sql = "SELECT o.*, c.FullName AS CustomerName, c.Email AS CustomerEmail, "
                + "c.PhoneNumber AS CustomerPhone, c.Address AS CustomerAddress, "
                + "s.FullName AS ShipperName, s.Email AS ShipperEmail, "
                + "s.PhoneNumber AS ShipperPhone, s.Address AS ShipperAddress "
                + "FROM Orders o "
                + "JOIN Accounts c ON o.CustomerID = c.AccountID "
                + "LEFT JOIN Accounts s ON o.ShipperID = s.AccountID "
                + "WHERE o.OrderID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderRowMapper());
    }

    @Override
    public void update(Orders order) {
        String sql = "UPDATE Orders SET OrderStatus = ?, ReturnRequestDate = ?, ReturnRequestStatus = ?, ReturnRequestReason = ? WHERE OrderID = ?";
        jdbcTemplate.update(sql,
                order.getOrderStatus(),
                order.getReturnRequestDate(),
                order.getReturnRequestStatus(),
                order.getReturnRequestReason(),
                order.getOrderID());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveOrder(int customerId, String shippingAddress, String notes, String paymentMethod,
            String name, String phone, BigDecimal totalAmount) {

        // Câu lệnh SQL để thêm dữ liệu vào bảng Orders
        String query = "INSERT INTO Orders (CustomerID, OrderDate, TotalAmount, ShippingAddress, Notes, PaymentMethod, Name, Phone, OrderStatus, ShipperID) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        // Thực thi câu lệnh SQL với JdbcTemplate
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(query, new String[]{"OrderID"});
                    ps.setInt(1, customerId);
                    ps.setTimestamp(2, new Timestamp(new Date().getTime()));
                    ps.setBigDecimal(3, totalAmount);
                    ps.setString(4, shippingAddress);
                    ps.setString(5, notes);
                    ps.setString(6, paymentMethod);
                    ps.setString(7, name);
                    ps.setString(8, phone);
                    ps.setString(9, "Pending");
                    ps.setInt(10, 0);
                    ps.setNull(10, Types.INTEGER);
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Orders> findAll() {
        String query = "SELECT OrderID, Name, Phone, ShippingAddress, PaymentMethod, Notes, TotalAmount, OrderDate, OrderStatus, ShipperID FROM Orders";
        List<Orders> orderList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        for (Map<String, Object> row : rows) {
            Orders order = new Orders();
            order.setOrderID((Integer) row.get("OrderID"));
            order.setName((String) row.get("Name"));
            order.setPhone((String) row.get("Phone"));
            order.setShippingAddress((String) row.get("ShippingAddress"));
            order.setPaymentMethod((String) row.get("PaymentMethod"));
            order.setNotes((String) row.get("Notes"));
            order.setTotalAmount((BigDecimal) row.get("TotalAmount"));

            Timestamp timestamp = (Timestamp) row.get("OrderDate");
            if (timestamp != null) {
                order.setOrderDate(timestamp.toLocalDateTime());
            }

            order.setOrderStatus((String) row.get("OrderStatus"));
            order.setShipperID((Integer) row.get("ShipperID"));

            orderList.add(order);
        }

        return orderList;
    }

    @Override
    public void updateShipperAndStatus(int orderID, int shipperID) {
        String query = "UPDATE Orders SET ShipperID = ?, OrderStatus = 'Confirmed' WHERE OrderID = ?";
        int rowsAffected = jdbcTemplate.update(query, shipperID, orderID);
        System.out.println("Rows affected: " + rowsAffected);
    }

    @Override
    public void updateCancalledStatus(int orderID) {
        String query = "UPDATE Orders SET OrderStatus = 'Cancelled' WHERE OrderID = ?";
        int rowsAffected = jdbcTemplate.update(query, orderID);
        System.out.println("Rows affected: " + rowsAffected);
    }
}
