package com.case_study.casemd3.service.coupon;

import static com.case_study.casemd3.connect.ConnectDB.getConnection;

import com.case_study.casemd3.model.Coupon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CouponImpl implements ICoupon {
    private static final String SELECT_ALL_COUPONS = "select * from coupon";
    private static final String SELECT_COUPON_BY_ID = "select id, name, value, is_active from coupon where id = ?";
    private static final String INSERT = "insert into coupon(id, name, value, is_active) values (?, ?, ?, ?)";
    private static final String DISABLE_COUPON = "UPDATE coupon SET is_active = false WHERE id = ?";
    private static final String UPDATE_COUPON = "update coupon set id = ?, name = ?, value = ?, is_active = ?";

    @Override
    public List<Coupon> findAll() {
        List<Coupon> coupons = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_ALL_COUPONS);
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double value = rs.getDouble("value");
                boolean is_active = rs.getBoolean("is_active");
                coupons.add(new Coupon(id, name, value, is_active));
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (Exception ex) {
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
        return coupons;
    }

    @Override
    public void save(Coupon coupon) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(INSERT);
            statement.setInt(1, coupon.getId());
            statement.setString(2, coupon.getName());
            statement.setDouble(3, coupon.getValue());
            statement.setBoolean(4, coupon.isIs_active());
            statement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
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
    public Coupon findById(int id) {
        Coupon coupon = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SELECT_COUPON_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double value = rs.getDouble("value");
                boolean is_active = rs.getBoolean("is_active");
                coupon = new Coupon(id, name, value, is_active);
            }
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
        return coupon;
    }
    @Override
    public boolean remove(int id) {
        boolean rowDisable;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement(DISABLE_COUPON);
            statement.setInt(1, id);
            rowDisable = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException();
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
    @Override
    public boolean update(int id, Coupon coupon) {
        boolean rowUpdate = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(UPDATE_COUPON);
            statement.setInt(1, coupon.getId());
            statement.setString(2, coupon.getName());
            statement.setDouble(3, coupon.getValue());
            statement.setBoolean(4, coupon.isIs_active());
        }
    }
}
