package com.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SupportDAOImpl implements SupportDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SupportDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public void saveMessage(Support support) {
        String sql = "INSERT INTO Support (customerID, employeeID, message, status, sendDate) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, support.getCustomerID(), support.getEmployeeID(), support.getMessage(), support.getStatus(), new Timestamp(support.getSendDate().getTime()));
    }

    @Override
    public List<Support> getMessagesByCustomerId(int customerId) {
        String sql = "SELECT * FROM Support WHERE customerID = ? ORDER BY sendDate ASC";
        return jdbcTemplate.query(sql, new Object[]{customerId}, new SupportRowMapper());
    }

    @Override
    public List<Support> getMessagesByCustomerIdAfterId(int customerId, int lastMessageId) {
        String sql = "SELECT * FROM Support WHERE customerID = ? AND supportID > ? ORDER BY sendDate ASC";
        return jdbcTemplate.query(sql, new Object[]{customerId, lastMessageId}, new SupportRowMapper());
    }
@Override
public List<Support> getCustomersWithNewMessages() {
    String sql = "SELECT s.*, a.FullName, a.PhoneNumber " +
                 "FROM Support s " +
                 "JOIN Accounts a ON s.CustomerID = a.AccountID " +
                 "WHERE  (a.Role = 4 OR a.Role = 2) " + 
                 "AND s.SendDate = (" +
                 "    SELECT MAX(SendDate) " +
                 "    FROM Support " +
                 "    WHERE CustomerID = s.CustomerID" +
                 ") " +
                 "ORDER BY s.SendDate DESC";

    return jdbcTemplate.query(sql, new SupportRowMapperWithCustomerInfo());
}
@Override
public List<Support> getMessagesByCustomerIdAndEmployeeId(Integer customerId, Integer employeeId) {
    String sql;
    Object[] params;

    sql = "SELECT * FROM Support WHERE CustomerID = ? " +
          (employeeId != null ? "AND (EmployeeID = ? OR EmployeeID IS NULL) " : "") +
          "ORDER BY SendDate";

    // Gán tham số
    if (employeeId != null) {
        params = new Object[]{customerId, employeeId};
    } else {
        params = new Object[]{customerId};
    }

    try {
        List<Support> messages = jdbcTemplate.query(sql, params, new SupportRowMapper());

        System.out.println("Retrieved " + messages.size() + " messages for customerId: " + customerId +
(employeeId != null ? " and employeeId: " + employeeId : ""));

        for (int i = 0; i < Math.min(messages.size(), 3); i++) {
            Support message = messages.get(i);
            System.out.println("Message " + i + ": ID=" + message.getSupportID() + 
                               ", Content=" + message.getMessage().substring(0, Math.min(message.getMessage().length(), 20)) + 
                               "..., Date=" + message.getSendDate());
        }

        return messages;
    } catch (Exception e) {
        System.err.println("Error retrieving messages for customerId: " + customerId + 
                           (employeeId != null ? " and employeeId: " + employeeId : ""));
        e.printStackTrace();
        return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
    }
}


    @Override
    public Support getMessageById(int messageId) {
        String sql = "SELECT * FROM Support WHERE supportID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{messageId}, new SupportRowMapper());
    }

    @Override
    public void updateMessage(Support support) {
        String sql = "UPDATE Support SET customerID = ?, employeeID = ?, message = ?, status = ?, sendDate = ? WHERE supportID = ?";
        jdbcTemplate.update(sql, support.getCustomerID(), support.getEmployeeID(), support.getMessage(), support.getStatus(), new Timestamp(support.getSendDate().getTime()), support.getSupportID());
    }

    @Override
    public List<Support> getNewMessages() {
        String sql = "SELECT * FROM Support WHERE status = 'New' OR status = 'Processing' ORDER BY sendDate ASC";
        List<Support> messages = jdbcTemplate.query(sql, new SupportRowMapper());
        System.out.println("Retrieved " + messages.size() + " new/processing messages from database");
        return messages;
    }

 

    private static class SupportRowMapper implements RowMapper<Support> {
        @Override
        public Support mapRow(ResultSet rs, int rowNum) throws SQLException {
            Support support = new Support();
            support.setSupportID(rs.getInt("supportID"));
            support.setCustomerID(rs.getObject("customerID", Integer.class));
            support.setEmployeeID(rs.getObject("employeeID", Integer.class));
            support.setMessage(rs.getString("message"));
            support.setStatus(rs.getString("status"));
            support.setSendDate(rs.getTimestamp("sendDate"));
            return support;
        }
    }

    private static class SupportRowMapperWithCustomerInfo implements RowMapper<Support> {
        @Override
        public Support mapRow(ResultSet rs, int rowNum) throws SQLException {
            Support support = new SupportRowMapper().mapRow(rs, rowNum);
            support.setFullName(rs.getString("FullName"));
            support.setPhoneNumber(rs.getString("PhoneNumber"));
            return support;
        }
    }
    
     @Override
    public int getUnreadMessageCount(int customerId) {
String sql = "SELECT COUNT(*) FROM Support WHERE customerID = ? AND status = 'Sent'";
        return jdbcTemplate.queryForObject(sql, new Object[]{customerId}, Integer.class);
    }
     @Override
    public int deleteMessagesByCustomerIdAndEmployeeId(int customerId, int employeeId) {
        String sql = "DELETE FROM Support WHERE customerID = ? ";
        return jdbcTemplate.update(sql, customerId);
    }
}