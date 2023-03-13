package com.case_study.casemd3.service.merchant;

import com.case_study.casemd3.model.Merchant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.case_study.casemd3.connect.Connect.getConnection;

public class MerchantService implements IMerchant {

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
            pre = con.prepareStatement("SELECT * FROM merchant");
            res = pre.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String name = res.getString("name");
                int age = res.getInt("age");
                String id_number = res.getString("id_number");
                String address = res.getString("address");
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
