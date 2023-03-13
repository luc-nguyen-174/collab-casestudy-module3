package com.case_study.casemd3.service.address;

import com.case_study.casemd3.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.case_study.casemd3.connect.Connect.getConnection;

public class AddressService implements IAddress {
    public static final String SELECT_ALL_ADDRESSES = "SELECT * FROM address";
    public static final String GET_ADDRESS_BY_ID = "SELECT * FROM address where id = ?";
    public static final String INSERT_INTO_ADDRESS = "insert into address(id, address_name) values(?, ?)";
    public static final String UPDATE_ADDRESS = "update address set address_name = ? where id = ?";

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(SELECT_ALL_ADDRESSES);
            res = pre.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String address = res.getString("address_name");
                addresses.add(new Address(id, address));
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (res != null) res.close();
                if (pre != null) pre.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return addresses;
    }

    @Override
    public void save(Address address) {
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(INSERT_INTO_ADDRESS);
            pre.setInt(1, address.getId());
            pre.setString(2, address.getAddress_name());
            con.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pre != null) pre.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Address findById(int id) {
        Address address = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(GET_ADDRESS_BY_ID);
            pre.setInt(1, id);
            res = pre.executeQuery();
            while (res.next()) {
                String address_name = res.getString("address_name");
                address = new Address(id, address_name);
            }
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            try {
                if (res != null) res.close();
                if (pre != null) pre.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return address;
    }

    @Override
    public boolean update(int id, Address address) {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(UPDATE_ADDRESS);
            pre.setInt(1, address.getId());
            pre.setString(2, address.getAddress_name());
            rowUpdated = pre.executeUpdate() > 0;
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(e);
            }
        } finally {
            try {
                if (pre != null) pre.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowUpdated;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
