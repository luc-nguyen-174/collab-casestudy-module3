package com.case_study.casemd3.service.user;

import com.case_study.casemd3.model.User;

import static com.case_study.casemd3.connect.ConnectDB.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements IUser {
    private final String SELECT_ALL_USERS = "select id, email, name, phone, address_id from user";
    private final String INSERT = "insert into user(id, email, name, phone, address_id) " +
            "values(?, ?, ?, ?, ?)";
    private final String SELECT_USER_BY_ID = "select id, email, name, phone, address_id " +
            "from user where id = ?";
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_ALL_USERS);
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                int address_id = rs.getInt("address_id");
                users.add(new User(id, email, name, phone, address_id));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public void save(User user) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getName());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getAddress_id());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);

        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                try {
                    if (statement != null) statement.close();
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public User findById(int id) {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_USER_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
            }
        }

        return user;
    }

    @Override
    public boolean update(int id, User user) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }


}
