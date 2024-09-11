package com.models;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class AccountsDAOImpl implements AccountsDAO {

    private JdbcTemplate jdbcTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AccountsDAO.class);

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
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        String query = "SELECT AccountID,"
                + " PhoneNumber,"
                + " Email,"
                + " Role,"
                + " Gender,"
                + " FullName,"
                + " Birthday,"
                + " Address,"
                + " HourlyRate FROM Accounts";

        List<Accounts> accountList = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        System.out.println("Rows returned: " + rows.size()); // Kiểm tra số lượng bản ghi

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

//    @Override
//    public String getRoleByEmailAndPassword(String email, String password) {
//        String sql = "SELECT Role FROM Accounts WHERE Email = ? AND Password = ?";
//        try {
//            return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, String.class);
//        } catch (Exception e) {
//            return null;
//        }
//    }
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
}
