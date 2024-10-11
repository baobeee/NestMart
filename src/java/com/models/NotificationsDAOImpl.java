package com.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class NotificationsDAOImpl implements NotificationsDAO {

    private JdbcTemplate jdbcTemplate;

    public NotificationsDAOImpl() {
    }

    public NotificationsDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Notifications> findAllNotifications() {
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, "
                + "n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate, n.NotificationType, n.Status "
                + "FROM Notifications n INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID "
                + "INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Notifications noti = new Notifications();
            noti.setNotificationID(rs.getInt("NotificationID"));
            noti.setCustomerID(rs.getInt("CustomerID"));
            noti.setEmployeeID(rs.getInt("EmployeeID"));
            noti.setCustomerName(rs.getString("CustomerName"));
            noti.setEmployeeName(rs.getString("EmployeeName"));
            noti.setTitle(rs.getString("Title"));
            noti.setMessage(rs.getString("Message"));
            noti.setSendDate(rs.getDate("SendDate"));
            noti.setNotificationType(rs.getString("NotificationType"));
            noti.setStatus(rs.getString("Status"));
            return noti;
        });
    }

    @Override
    public List<Notifications> findNotificationsByCustomerId(int customerId) {
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, "
                + "n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate, "
                + "n.NotificationType, n.Status "
                + "FROM Notifications n "
                + "INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID "
                + "INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID "
                + "WHERE n.CustomerID = ?";
        return jdbcTemplate.query(query, new Object[]{customerId}, (rs, rowNum) -> {
            Notifications noti = new Notifications();
            noti.setNotificationID(rs.getInt("NotificationID"));
            noti.setCustomerID(rs.getInt("CustomerID"));
            noti.setEmployeeID(rs.getInt("EmployeeID"));
            noti.setCustomerName(rs.getString("CustomerName"));
            noti.setEmployeeName(rs.getString("EmployeeName"));
            noti.setTitle(rs.getString("Title"));
            noti.setMessage(rs.getString("Message"));
            noti.setSendDate(rs.getDate("SendDate"));
            noti.setNotificationType(rs.getString("NotificationType"));
            noti.setStatus(rs.getString("Status"));
            return noti;
        });
    }

    @Override
    public List<Notifications> findNotificationsByCustomerIdAndStatus(int customerId, String status) {
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, "
                + "n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate, "
                + "n.NotificationType, n.Status "
                + "FROM Notifications n "
                + "INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID "
                + "INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID "
                + "WHERE n.CustomerID = ? AND n.Status = ?";
        return jdbcTemplate.query(query, new Object[]{customerId, status}, (rs, rowNum) -> {
            Notifications noti = new Notifications();
            noti.setNotificationID(rs.getInt("NotificationID"));
            noti.setCustomerID(rs.getInt("CustomerID"));
            noti.setEmployeeID(rs.getInt("EmployeeID"));
            noti.setCustomerName(rs.getString("CustomerName"));
            noti.setEmployeeName(rs.getString("EmployeeName"));
            noti.setTitle(rs.getString("Title"));
            noti.setMessage(rs.getString("Message"));
            noti.setSendDate(rs.getDate("SendDate"));
            noti.setNotificationType(rs.getString("NotificationType"));
            noti.setStatus(rs.getString("Status"));
            return noti;
        });
    }

    @Override
    public List<Notifications> findNotificationsByEmployeeId(int employeeId) {
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, "
                + "n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate, "
                + "n.NotificationType, n.Status "
                + "FROM Notifications n "
                + "INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID "
                + "INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID "
                + "WHERE n.EmployeeID = ?";
        return jdbcTemplate.query(query, new Object[]{employeeId}, (rs, rowNum) -> {
            Notifications noti = new Notifications();
            noti.setNotificationID(rs.getInt("NotificationID"));
            noti.setCustomerID(rs.getInt("CustomerID"));
            noti.setEmployeeID(rs.getInt("EmployeeID"));
            noti.setCustomerName(rs.getString("CustomerName"));
            noti.setEmployeeName(rs.getString("EmployeeName"));
            noti.setTitle(rs.getString("Title"));
            noti.setMessage(rs.getString("Message"));
            noti.setSendDate(rs.getDate("SendDate"));
            noti.setNotificationType(rs.getString("NotificationType"));
            noti.setStatus(rs.getString("Status"));
            return noti;
        });
    }

    @Override
    public List<Notifications> findNotificationsByEmployeeIdAndStatus(int employeeId, String status) {
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, "
                + "n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate, "
                + "n.NotificationType, n.Status "
                + "FROM Notifications n "
                + "INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID "
                + "INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID "
                + "WHERE n.EmployeeID = ? AND n.Status = ?";
        return jdbcTemplate.query(query, new Object[]{employeeId, status}, (rs, rowNum) -> {
            Notifications noti = new Notifications();
            noti.setNotificationID(rs.getInt("NotificationID"));
            noti.setCustomerID(rs.getInt("CustomerID"));
            noti.setEmployeeID(rs.getInt("EmployeeID"));
            noti.setCustomerName(rs.getString("CustomerName"));
            noti.setEmployeeName(rs.getString("EmployeeName"));
            noti.setTitle(rs.getString("Title"));
            noti.setMessage(rs.getString("Message"));
            noti.setSendDate(rs.getDate("SendDate"));
            noti.setNotificationType(rs.getString("NotificationType"));
            noti.setStatus(rs.getString("Status"));
            return noti;
        });
    }

    @Override
    public void add(Notifications notification) {
        String query = "INSERT INTO Notifications (CustomerID, EmployeeID, Title, Message, SendDate, NotificationType, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query,
                notification.getCustomerID(),
                notification.getEmployeeID(),
                notification.getTitle(),
                notification.getMessage(),
                new java.sql.Date(notification.getSendDate().getTime()),
                notification.getNotificationType(),
                notification.getStatus());
    }
    
    @Override
    public List<Accounts> getAllCustomer() {
        String query = "SELECT AccountID, PhoneNumber, Email, Password, Role, Gender, FullName, Birthday, Address, HourlyRate FROM Accounts WHERE Role = 4;";
        try {
            return jdbcTemplate.query(query, (rs, rowNum) -> {
                Accounts ac = new Accounts();
                ac.setAccountID(rs.getInt("AccountID"));
                ac.setPhoneNumber(rs.getString("PhoneNumber"));
                ac.setEmail(rs.getString("Email"));
                ac.setPassword(rs.getString("Password"));
                ac.setRole(rs.getInt("Role"));
                ac.setGender(rs.getString("Gender"));
                ac.setFullName(rs.getString("FullName"));
                ac.setAddress(rs.getString("Address"));
                ac.setHourlyRate(rs.getBigDecimal("HourlyRate"));
                ac.setBirthday(rs.getDate("Birthday"));
                return ac;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Notifications WHERE NotificationID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Notifications get(int id) {
        String query = "SELECT n.NotificationID, n.CustomerID, n.EmployeeID, n.Title, n.Message, n.Status, n.NotificationType, n.SendDate, c.FullName AS CustomerName " +
                "FROM Notifications n " +
                "INNER JOIN Accounts c ON n.CustomerID = c.AccountID " +
                "WHERE n.NotificationID = ?;";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            Notifications noti = new Notifications();
            noti.setNotificationID(rs.getInt("NotificationID"));
            noti.setCustomerID(rs.getInt("CustomerID"));
            noti.setEmployeeID(rs.getInt("EmployeeID"));
            noti.setTitle(rs.getString("Title"));
            noti.setMessage(rs.getString("Message"));
            noti.setSendDate(rs.getDate("SendDate"));
            noti.setNotificationType(rs.getString("NotificationType"));
            noti.setStatus(rs.getString("Status"));
            noti.setCustomerName(rs.getString("CustomerName"));
            return noti;
        });
    }
    
    @Override
    public void update(Notifications notification) {
        String query = "UPDATE Notifications SET Title = ?, Message = ?, SendDate = ?, NotificationType = ? WHERE NotificationID = ?";
        jdbcTemplate.update(query,
                notification.getTitle(),
                notification.getMessage(),
                new java.sql.Date(notification.getSendDate().getTime()),
                notification.getNotificationType(),
                notification.getNotificationID());
    }

    @Override
    public List<Notifications> findNotifications(String keyword) {
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate FROM Notifications n INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID WHERE n.Title LIKE ?";
        keyword = "%" + keyword + "%";
        return jdbcTemplate.query(query, new Object[]{keyword}, BeanPropertyRowMapper.newInstance(Notifications.class));
    }

    @Override
    public List<Notifications> findNotificationsByEmployeeID(int employeeID, String keyword) {
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate FROM Notifications n INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID WHERE n.EmployeeID = ? AND n.Title LIKE ?";
        keyword = "%" + keyword + "%";
        return jdbcTemplate.query(query, new Object[]{employeeID, keyword}, BeanPropertyRowMapper.newInstance(Notifications.class));
    }
}
