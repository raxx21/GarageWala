package com.rajesh.garaajwala.models;

public class GarageHelper {

    String title;
    String des;
    String phone;

    public GarageHelper(String title, String des,String phone) {
        this.title = title;
        this.des = des;
        this.phone = phone;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public String getPhone() {
        return phone;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
