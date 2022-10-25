package com.db.dao;

import com.db.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStrategy implements StatementStrategy {
    private final User user;

    public AddStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement makeStatement(Connection c) throws SQLException {
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?)");

        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getPassword());

        return pstmt;
    }
}
