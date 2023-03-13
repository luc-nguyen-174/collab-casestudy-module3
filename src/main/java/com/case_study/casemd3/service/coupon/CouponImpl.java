package com.case_study.casemd3.service.coupon;

import com.case_study.casemd3.model.Coupon;

import java.util.List;

public class CouponImpl implements ICoupon{
    private final String SELECT_ALL_COUPONS = "select * from coupon";
    private final String SELECT_COUPON_BY_ID = "select id, name, value, is_active from coupon where id = ?";
    private final String INSERT = "insert into coupon(id, name, value, is_active) values (?, ?, ?, ?)";
    @Override
    public List<Coupon> findAll() {
        return null;
    }

    @Override
    public void save(Coupon coupon) {

    }

    @Override
    public Coupon findById(int id) {
        return null;
    }

    @Override
    public boolean update(int id, Coupon coupon) {
        return false;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }
}
