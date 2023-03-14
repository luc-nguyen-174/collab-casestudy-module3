package com.case_study.casemd3.model;

public class Merchant extends Login {
    private int id;
    private String name;
    private int age;
    private String id_number;
    private int address_id;
    private Address address;
    private String phone;
    private String email;
    private boolean is_active;

    public Merchant() {
    }

    public Merchant(int id, String name, int age, String id_number, Address address, String phone, String email, boolean is_active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.id_number = id_number;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.is_active = is_active;
    }
    public Merchant(int id, String name, int age, String id_number, int address_id, String phone, String email, boolean is_active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.id_number = id_number;
        this.address_id = address_id;
        this.phone = phone;
        this.email = email;
        this.is_active = is_active;
    }

    public Merchant(String username, String password, int id, String name, int age, String id_number, Address address, String phone, String email, boolean is_active) {
        super(username, password);
        this.id = id;
        this.name = name;
        this.age = age;
        this.id_number = id_number;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
