package com.case_study.casemd3.service.food;

import com.case_study.casemd3.model.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.case_study.casemd3.connect.ConnectDB.getConnection;

public class FoodService implements IFood {


    public static final String SELECT_ALL_FROM_FOOD = "SELECT * FROM food";
    public static final String INSERT_INTO_FOOD = "INSERT INTO food (id,name, price,detail,img_link,certificate)" +
            " values (?,?,?,?,?,?)";
    public static final String DISABLE_FOOD = "UPDATE food SET is_active = false WHERE id =?";
    public static final String UPDATE_FOOD_BY_ID = "UPDATE food SET name = ?, price = ?, detail = ?, img_link = ?, certificate = ?, is_active = ? WHERE id = ?";

    @Override
    public List<Food> findAll() {
        List<Food> foods = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(SELECT_ALL_FROM_FOOD);
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String details = rs.getString("detail");
                String img_link = rs.getString("img_link");
                boolean certificate = rs.getBoolean("certificate");
                boolean is_active = rs.getBoolean("is_active");
                foods.add(new Food(id, name, price, details, img_link, certificate, is_active));
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return foods;
    }

    @Override
    public void save(Food food) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(INSERT_INTO_FOOD);
            statement.setInt(1, food.getId());
            statement.setString(2, food.getName());
            statement.setDouble(3, food.getPrice());
            statement.setString(4, food.getImg_link());
            statement.setBoolean(5, food.isCertificate());
            statement.setBoolean(6, food.isIs_active());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Food findById(int id) {
        Food food = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("SELECT ");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String details = rs.getString("detail");
                String img_link = rs.getString("img_link");
                boolean certificate = rs.getBoolean("certificate");
                boolean is_active = rs.getBoolean("is_active");
                food = new Food(id, name, price, details, img_link, certificate, is_active);
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
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return food;
    }

    @Override
    public boolean update(int id, Food food) {
        boolean rowUpdated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(UPDATE_FOOD_BY_ID);
            statement.setString(1,food.getName());
            statement.setDouble(2,food.getPrice());
            statement.setString(3,food.getDetail());
            statement.setString(4,food.getImg_link());
            statement.setBoolean(5,food.isCertificate());
            statement.setBoolean(6,food.isIs_active());

            rowUpdated = statement.executeUpdate() > 0;
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowUpdated;
    }

    @Override
    public boolean remove(int id) {
        boolean rowDisable = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(DISABLE_FOOD);
            statement.setInt(1, id);
            rowDisable = statement.executeUpdate() > 0;
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowDisable;
    }


}
