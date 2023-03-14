package com.case_study.casemd3.service.merchant;

import com.case_study.casemd3.connect.ConnectDB;
import com.case_study.casemd3.model.Address;
import com.case_study.casemd3.model.Merchant;
import com.case_study.casemd3.service.address.AddressService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.case_study.casemd3.connect.ConnectDB.getConnection;

public class MerchantService implements IMerchant {
    public static final String SELECT_ALL_MERCHANTS;
    AddressService addressService = new AddressService();
    public static final String INSERT_INTO_MERCHANT;
    public static final String GET_MERCHANT_BY_ID;
    public static final String DELETE_MERCHANT;
    public static final String UPDATE_MERCHANT;
    static {
        INSERT_INTO_MERCHANT = "insert into merchant (id, name, age, id_number, address_id, phone, email, is_active) values (?, ?, ?, ?, ?, ?, ?, ?)";
        GET_MERCHANT_BY_ID = "SELECT * FROM merchant where id = ?";
        DELETE_MERCHANT = "UPDATE merchant SET is_active = false WHERE id = ?";
        SELECT_ALL_MERCHANTS = "CALL select_all_merchants()";
        UPDATE_MERCHANT = "UPDATE merchant SET name = ?, age = ?, id_number = ?, address_id = ?, phone = ?, email = ?, is_active = ? WHERE id = ?";
    }

    @Override
    public List<Merchant> findAll() {
        List<Merchant> merchants = new ArrayList<>();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(SELECT_ALL_MERCHANTS);
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
                boolean is_active = res.getBoolean("is_active");
                merchants.add(new Merchant(id, name, age, id_number, address, phone, email, is_active));
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
            pre.setInt(5, merchant.getAddress_id());
            pre.setString(6, merchant.getPhone());
            pre.setString(7, merchant.getEmail());
            pre.setBoolean(8, merchant.isIs_active());
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
        Merchant merchant = null;
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;
        try {
            con = ConnectDB.getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(GET_MERCHANT_BY_ID);
            pre.setInt(1, id);
            res = pre.executeQuery();
            while (res.next()) {
                String name = res.getString("name");
                int age = res.getInt("age");
                String id_number = res.getString("id_number");
                int province_id = res.getInt("province_id");
                Address address = addressService.findById(province_id);
                String phone = res.getString("phone");
                String email = res.getString("email");
                boolean is_active = res.getBoolean("is_active");
                merchant = new Merchant(id, name, age, id_number, address, phone, email, is_active);
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
        return merchant;
    }

    @Override
    public boolean update(int id, Merchant merchant) {
        boolean rowUpdated = false;
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(UPDATE_MERCHANT);
            pre.setString(1, merchant.getName());
            pre.setInt(2, merchant.getAge());
            pre.setString(3, merchant.getId_number());
            pre.setInt(4, merchant.getAddress_id());
            pre.setString(5, merchant.getPhone());
            pre.setString(6, merchant.getEmail());
            pre.setBoolean(7, merchant.isIs_active());
            pre.setInt(8, merchant.getId());
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
        boolean rowDeleted = false;
        Connection con = null;
        PreparedStatement pre = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);
            pre = con.prepareStatement(DELETE_MERCHANT);
            pre.setInt(1, id);
            rowDeleted = pre.executeUpdate() > 0;
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
        return rowDeleted;
    }
}
