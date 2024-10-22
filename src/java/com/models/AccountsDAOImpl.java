package com.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class AccountsDAOImpl implements AccountsDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AccountsDAOImpl() {
    }

    public AccountsDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println("JdbcTemplate initialized: " + (this.jdbcTemplate != null));
    }

    @Override
    public List<Accounts> findAll() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT AccountID,"
                + " PhoneNumber,"
                + " Password,"
                + " Email,"
                + " Role,"
                + " Gender,"
                + " FullName,"
                + " Birthday,"
                + " Address,"
                + " HourlyRate FROM Accounts";

        List<Accounts> accountList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size());

        for (Map<String, Object> row : rows) {
            Accounts a = new Accounts(
                    (int) row.get("AccountID"),
                    (String) row.get("PhoneNumber"),
                    (String) row.get("Password"),
                    (String) row.get("Email"),
                    (int) row.get("Role"),
                    (String) row.get("Gender"),
                    (String) row.get("FullName"),
                    (Date) row.get("Birthday"),
                    (String) row.get("Address"),
                    (BigDecimal) row.get("HourlyRate"));
            accountList.add(a);
        }
        System.out.println(accountList);
        return accountList;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Accounts account) {
        String query = "INSERT INTO Accounts ("
                + "FullName, "
                + "Password, "
                + "PhoneNumber, "
                + "Email, "
                + "Role, "
                + "Gender, "
                + "Birthday, "
                + "Address, "
                + "HourlyRate) VALUES(?,?,?,?,?,?,?,?,?)";

        try {
            jdbcTemplate.update(query, account.getFullName(),
                    account.getPassword(),
                    account.getPhoneNumber(),
                    account.getEmail(),
                    account.getRole(),
                    account.getGender(),
                    account.getBirthday(),
                    account.getAddress(),
                    account.getHourlyRate());
        } catch (DataAccessException e) {
            System.err.println("Error occurred while saving account: " + e.getMessage());
            throw new RuntimeException("Failed to save account", e);
        }
    }

    @Override
    public Accounts findById(int id) {
        String query = "SELECT * FROM Accounts WHERE AccountID = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int i) throws SQLException {
                return new Accounts(
                        rs.getInt("AccountID"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getInt("Role"),
                        rs.getString("Gender"),
                        rs.getString("FullName"),
                        rs.getDate("Birthday"),
                        rs.getString("Address"),
                        rs.getBigDecimal("HourlyRate")
                );
            }
        });
    }

    @Override
    public void update(Accounts account) {
        String sql = "UPDATE Accounts SET FullName = ?,"
                + " Password = ?,"
                + " PhoneNumber = ?,"
                + " Email = ?,"
                + " Role = ?,"
                + " Gender = ?,"
                + " Birthday = ?,"
                + " Address = ?,"
                + " HourlyRate = ? "
                + "WHERE AccountID = ?";
        jdbcTemplate.update(sql, account.getFullName(),
                account.getPassword(),
                account.getPhoneNumber(),
                account.getEmail(),
                account.getRole(),
                account.getGender(),
                account.getBirthday(),
                account.getAddress(),
                account.getHourlyRate(),
                account.getAccountID());
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM Accounts WHERE AccountID = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        String query = "SELECT COUNT(*) FROM Accounts WHERE PhoneNumber = ?";
        Integer count = jdbcTemplate.queryForObject(query, new Object[]{phoneNumber}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        String query = "SELECT COUNT(*) FROM Accounts WHERE Email = ?";
        Integer count = jdbcTemplate.queryForObject(query, new Object[]{email}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public Accounts findByEmail(String email) {
        String sql = "SELECT * FROM Accounts WHERE Email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, new AccountRowMapper());
    }

    @Override
    public boolean validateUser(String email, String password) {
        String sql = "SELECT Password FROM Accounts WHERE Email = ?";
        try {
            // Lấy pass đã hash từ cơ sở dữ liệu
            String storedHashedPassword = jdbcTemplate.queryForObject(sql, new Object[]{email}, String.class);
            if (storedHashedPassword == null) {
                System.out.println("Password for email not found.");
                return false;
            }
            // In giá trị của mật khẩu đã mã hóa và mật khẩu cung cấp
            System.out.println("Stored Hashed Password: " + storedHashedPassword);
            System.out.println("Provided Password: " + password);
            boolean matches = passwordEncoder.matches(password, storedHashedPassword);
            System.out.println("Password matches: " + matches);
            return matches;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean registerUser(Accounts account) {
        try {
            // Hash mật khẩu
            String hashedPassword = passwordEncoder.encode(account.getPassword());

            String sql = "INSERT INTO Accounts (FullName, Email, Password, PhoneNumber, Address, Gender, Birthday, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(
                    sql,
                    account.getFullName(),
                    account.getEmail(),
                    hashedPassword,
                    account.getPhoneNumber(),
                    account.getAddress(),
                    account.getGender(),
                    account.getBirthday(),
                    account.getRole()
            );
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer getRoleByEmail(String email) {
        String sql = "SELECT Role FROM Accounts WHERE Email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getPasswordByEmail(String email) {
        String sql = "SELECT Password FROM Accounts WHERE Email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        String sql = "UPDATE Accounts SET Password = ? WHERE Email = ?";
        jdbcTemplate.update(sql, newPassword, email);
    }

    @Override
    public Accounts findByPhoneNumber(String phoneNumber) {
        String sql = "SELECT * FROM Accounts WHERE PhoneNumber = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{phoneNumber}, (rs, rowNum) -> {
                Accounts account = new Accounts();
                account.setAccountID(rs.getInt("AccountID"));
                account.setEmail(rs.getString("Email"));
                account.setFullName(rs.getString("FullName"));
                account.setPhoneNumber(rs.getString("PhoneNumber"));
                account.setGender(rs.getString("Gender"));
                account.setAddress(rs.getString("Address"));
                account.setBirthday(rs.getDate("Birthday"));
                account.setRole(rs.getInt("Role"));
                // Thêm các trường khác nếu cần
                return account;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Accounts> getAccountsByRoles(List<Integer> roles) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Accounts WHERE Role IN (");

        for (int i = 0; i < roles.size(); i++) {
            sql.append("?");
            if (i < roles.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");

        Object[] params = roles.toArray();
        return jdbcTemplate.query(sql.toString(), params, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                Accounts account = new Accounts();
                account.setAccountID(rs.getInt("accountID"));
                account.setFullName(rs.getString("fullName"));
                account.setPhoneNumber(rs.getString("phoneNumber"));
                account.setEmail(rs.getString("email"));
                account.setRole(rs.getInt("role"));
                account.setGender(rs.getString("gender"));
                account.setBirthday(rs.getDate("birthday"));
                account.setAddress(rs.getString("address"));
                account.setHourlyRate(rs.getBigDecimal("hourlyRate"));
                return account;
            }
        });
    }

    private static class AccountRowMapper implements RowMapper<Accounts> {

        @Override
        public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
            Accounts account = new Accounts();
            account.setAccountID(rs.getInt("accountID"));
            account.setPhoneNumber(rs.getString("phoneNumber"));
            account.setPassword(rs.getString("password"));
            account.setEmail(rs.getString("email"));
            account.setRole(rs.getInt("role"));
            account.setGender(rs.getString("gender"));
            account.setFullName(rs.getString("fullName"));
            account.setBirthday(rs.getDate("birthday"));
            account.setAddress(rs.getString("address"));
            account.setHourlyRate(rs.getBigDecimal("hourlyRate"));
            return account;
        }
    }

    @Override
    public boolean isPhoneNumberRegistered(String phone) {
        String sql = "SELECT COUNT(*) FROM Accounts WHERE PhoneNumber = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{phone}, Integer.class);
        return count != null && count > 0;
    }

    @Override
    public List<Accounts> searchAccounts(String keyword, int page, int pageSize) {
        // Đảm bảo rằng offset không bao giờ âm
        if (page < 1) {
            page = 1;
        }

        String query = "SELECT * FROM Accounts WHERE FullName LIKE ? OR Email LIKE ? ORDER BY AccountID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String searchKeyword = "%" + keyword + "%";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{searchKeyword, searchKeyword, offset, pageSize}, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Accounts(
                        rs.getInt("AccountID"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getInt("Role"),
                        rs.getString("Gender"),
                        rs.getString("FullName"),
                        rs.getDate("Birthday"),
                        rs.getString("Address"),
                        rs.getBigDecimal("HourlyRate")
                );
            }
        });
    }

    @Override
    public List<Accounts> getPagedAccounts(String keyword, int page, int pageSize) {
        String query = "SELECT * FROM Accounts WHERE FullName LIKE ? OR Email LIKE ? ORDER BY AccountID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String searchKeyword = "%" + keyword + "%";
        int offset = (page - 1) * pageSize;

        return jdbcTemplate.query(query, new Object[]{searchKeyword, searchKeyword, offset, pageSize}, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Accounts(
                        rs.getInt("AccountID"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getInt("Role"),
                        rs.getString("Gender"),
                        rs.getString("FullName"),
                        rs.getDate("Birthday"),
                        rs.getString("Address"),
                        rs.getBigDecimal("HourlyRate")
                );
            }
        });
    }

    @Override
    public int getTotalAccounts(String keyword) {
        String query = "SELECT COUNT(*) FROM Accounts WHERE FullName LIKE ? OR Email LIKE ?";
        String searchKeyword = "%" + keyword + "%";

        return jdbcTemplate.queryForObject(query, new Object[]{searchKeyword, searchKeyword}, Integer.class);
    }

    @Override
    public List<Accounts> findEmployeesByRoles(List<Integer> roles) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Accounts WHERE Role IN (");

        // Tạo câu truy vấn với số lượng parameter tương ứng với số role
        for (int i = 0; i < roles.size(); i++) {
            sql.append("?");
            if (i < roles.size() - 1) {
                sql.append(", ");
            }
        }
        sql.append(")");
        Object[] params = roles.toArray();
        return jdbcTemplate.query(sql.toString(), params, new RowMapper<Accounts>() {
            @Override
            public Accounts mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Accounts(
                        rs.getInt("AccountID"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Password"),
                        rs.getString("Email"),
                        rs.getInt("Role"),
                        rs.getString("Gender"),
                        rs.getString("FullName"),
                        rs.getDate("Birthday"),
                        rs.getString("Address"),
                        rs.getBigDecimal("HourlyRate")
                );
            }
        });
    }
@Override
public Accounts findShipperById(int orderID) {
    String sql = "SELECT a.AccountID, a.FullName FROM Accounts a " +
                 "JOIN Orders o ON a.AccountID = o.ShipperID " +
                 "WHERE o.OrderID = ?";
    
    return jdbcTemplate.queryForObject(sql, new Object[]{orderID}, (rs, rowNum) -> {
        Accounts account = new Accounts();
        account.setAccountID(rs.getInt("AccountID"));
        account.setFullName(rs.getString("FullName"));
        return account;
    });
}
@Override
    public List<Accounts> findShippers() {
        String sql = "SELECT AccountID, FullName FROM Accounts WHERE Role = 3";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Accounts account = new Accounts();
            account.setAccountID(rs.getInt("AccountID"));
            account.setFullName(rs.getString("FullName"));
            return account;
        });
    }
}
