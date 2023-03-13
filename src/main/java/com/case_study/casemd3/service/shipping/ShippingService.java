package com.case_study.casemd3.service.shipping;

import com.case_study.casemd3.model.ShippingPartner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.case_study.casemd3.connect.ConnectDB.getConnection;

public class ShippingService implements IShipping {

    public static final String SELECT_ALL_FROM_SHIPPING_PARTNERS = "SELECT * FROM shipping_partners";
    public static final String SELECT_SHIPPING_PARTNER_BY_ID = "SELECT (id,name) FROM shipping_partners WHERE id = ?";
    public static final String INSERT_INTO_SHIPPING_PARTNERS = "INSERT INTO shipping_partners (id, name) values (?,?)";
    public static final String UPDATE_SHIPPING_PARTNER_BY_ID = "UPDATE shipping_partner SET name = ? WHERE id =?";
    public static final String DELETE_FROM_SHIPPING = "DELETE FROM shipping_partners WHERE id = ?";

    @Override
    public List<ShippingPartner> findAll() {
        List<ShippingPartner> partners = new ArrayList<>();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(SELECT_ALL_FROM_SHIPPING_PARTNERS);
            rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                partners.add(new ShippingPartner(id, name));
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
        return partners;
    }

    @Override
    public void save(ShippingPartner shippingPartner) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(INSERT_INTO_SHIPPING_PARTNERS);
            statement.setInt(1, shippingPartner.getId());
            statement.setString(2, shippingPartner.getName());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
    public ShippingPartner findById(int id) {
        ShippingPartner shippingPartner = null;
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(SELECT_SHIPPING_PARTNER_BY_ID);
            statement.setInt(1, id);
            rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                shippingPartner = new ShippingPartner(id, name);
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
        return shippingPartner;
    }

    @Override
    public boolean update(int id, ShippingPartner shippingPartner) {
        boolean rowUpdated = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(UPDATE_SHIPPING_PARTNER_BY_ID);
            statement.setString(1, shippingPartner.getName());
            statement.setInt(2, id);
            rowUpdated = statement.executeUpdate() > 0;
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowUpdated;
    }

    @Override
    public boolean remove(int id) {
        boolean rowDeleted = false;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            statement = conn.prepareStatement(DELETE_FROM_SHIPPING);
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowDeleted;
    }
}
