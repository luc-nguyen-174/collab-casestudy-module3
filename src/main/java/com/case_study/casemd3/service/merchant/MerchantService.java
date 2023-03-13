package com.case_study.casemd3.service.merchant;

import com.case_study.casemd3.model.Address;
import com.case_study.casemd3.model.Merchant;
import com.case_study.casemd3.service.address.AddressService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.case_study.casemd3.connect.Connect.getConnection;

public class MerchantService implements IMerchant {
    AddressService addressService = new AddressService();
    public static final String INSERT_INTO_MERCHANT = "insert into merchant (id, name, age, id_number, address_id, phone, email) values (?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Merchant> findAll() {
        List<Merchant> merchants = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement("{CALL select_all_merchants()}");
            res = pre.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                int age = res.getInt("age");
                String id_number = res.getString("id_number");
                int province_id = res.getInt("province_id");
                Address address = addressService.findById(province_id);
                String phone = res.getString("phone");
                String email = res.getString("email");
                merchants.add(new Merchant(id, name, age, id_number, address, phone, email));
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
        return merchants;
    }

    @Override
    public void save(Merchant merchant) {
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(INSERT_INTO_MERCHANT);
            pre.setInt(1, merchant.getId());
            pre.setString(2, merchant.getName());
            pre.setInt(3, merchant.getAge());
            pre.setString(4, merchant.getId_number());
            pre.setString(5, merchant.getAddress());
            pre.setString(6, merchant.getPhone());
            pre.setString(7, merchant.getEmail());
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
    public Merchant findById(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Merchant merchant) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
