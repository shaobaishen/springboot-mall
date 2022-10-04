package com.shaobaishen.rowmapper;

import com.shaobaishen.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setLastModifiedDate(rs.getTimestamp("last_modified_date"));
        user.setCreatedDate(rs.getTimestamp("created_date"));
        return user;
    }
}
