package com.case_study.casemd3.model;

public class ShippingPartner {
    private int id;
    private String name;

    public ShippingPartner() {
    }

    public ShippingPartner(int id, String name) {
        this.id = id;
        this.name = name;
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
}
