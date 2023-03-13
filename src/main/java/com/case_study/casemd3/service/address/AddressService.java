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
    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement("SELECT * FROM address where id = ?");
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
    public void save(Address generic) {

    }

    @Override
    public Address findById(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Address generic) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
