package com.case_study.casemd3.model;

public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String phone;
    private int address_id;

    public User() {
    }

    public User(int id, String userName, String password, String email, String name, String phone, int address_id) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address_id = address_id;
    }

    public User(int id, String email, String name, String phone, int address_id) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address_id = address_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
}
