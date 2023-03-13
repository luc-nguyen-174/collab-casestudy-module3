package com.case_study.casemd3.model;

public class Address {
    private int id;
    private String address_name;

    public Address() {
    }

    public Address(int id, String address_name) {
        this.id = id;
        this.address_name = address_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }
}
