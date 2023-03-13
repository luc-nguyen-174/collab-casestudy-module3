package com.case_study.casemd3.model;

public class Coupon {
    private int id;
    private String name;
    private double value;
    private boolean is_active;

    public Coupon() {
    }

    public Coupon(int id, String name, double value, boolean is_active) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.is_active = is_active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}
