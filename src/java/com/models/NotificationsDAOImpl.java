package com.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
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
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate FROM Notifications n INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID";
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
            return noti;
        });
    }

    @Override
    public void add(Notifications notification) {
        
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM Notifications WHERE NotificationID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public Notifications get(String id) {
        return null;
    }

    @Override
    public void update(Notifications notification) {
        
    }

    @Override
    public List<Notifications> findNotifications(String keyword) {
        String query = "SELECT n.NotificationID, n.CustomerID, ca.FullName AS CustomerName, n.EmployeeID, ea.FullName AS EmployeeName, n.Title, n.Message, n.SendDate FROM Notifications n INNER JOIN Accounts ca ON n.CustomerID = ca.AccountID INNER JOIN Accounts ea ON n.EmployeeID = ea.AccountID WHERE n.Title LIKE ?";
        keyword = "%" + keyword + "%";
        return jdbcTemplate.query(query, new Object[]{keyword}, BeanPropertyRowMapper.newInstance(Notifications.class));
    }
    
}
