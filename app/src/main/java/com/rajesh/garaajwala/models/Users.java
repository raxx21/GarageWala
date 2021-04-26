package com.rajesh.garaajwala.models;

public class Users {
private int id;
private String name;
private String phone;
private String email;
private String password;
    private String house_no;
    private String street;
    private String landmark;
    private String state;
    private String city;
    private String area;

    public Users(int id) {
        this.id = id;
    }

    public Users(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Users(String email) {
        this.email = email;
    }

    public Users() {
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHouse_no(String house_no) {
        this.house_no = house_no;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setArea(String area) {
        this.area = area;
    }

    //getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getHouse_no() {
        return house_no;
    }

    public String getStreet() {
        return street;
    }

    public String getLandmark() {
        return landmark;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getArea() {
        return area;
    }
}
