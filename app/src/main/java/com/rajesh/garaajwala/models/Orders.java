package com.rajesh.garaajwala.models;

public class Orders {
    private int id;
    private String user;
    private String garage;

    public Orders() { }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getGarage() {
        return garage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }
}
