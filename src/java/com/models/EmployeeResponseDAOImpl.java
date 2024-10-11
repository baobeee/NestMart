package com.models;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

@Repository
public class EmployeeResponseDAOImpl implements EmployeeResponseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public EmployeeResponseDAOImpl() {
    }

    public EmployeeResponseDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addEmployeeResponse(EmployeeResponse employeeResponse) {
        String sql = "INSERT INTO EmployeeResponse (EmployeeID, FeedbackID, ResponseContent, ResponseDate) "
                   + "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, 
                employeeResponse.getEmployeeID(), 
                employeeResponse.getFeedbackID(), 
                employeeResponse.getResponseContent(), 
                employeeResponse.getResponseDate());
    }

    @Override
    public List<EmployeeResponse> getResponsesByFeedbackID(int feedbackID) {
        String sql = "SELECT * FROM EmployeeResponse WHERE FeedbackID = ?";
        return jdbcTemplate.query(sql, new Object[]{feedbackID}, new EmployeeResponseRowMapper());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class EmployeeResponseRowMapper implements RowMapper<EmployeeResponse> {
        @Override
        public EmployeeResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeResponse response = new EmployeeResponse();
            response.setResponseID(rs.getInt("ResponseID"));
            response.setEmployeeID(rs.getInt("EmployeeID"));
            response.setFeedbackID(rs.getInt("FeedbackID"));
            response.setResponseContent(rs.getString("ResponseContent"));
            response.setResponseDate(rs.getTimestamp("ResponseDate").toLocalDateTime());
            return response;
        }
    }

}

