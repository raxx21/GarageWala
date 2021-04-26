package com.rajesh.garaajwala.models;

public class Service2 {
    private int id;
    private int tyres;
    private int lights;
    private int horn;
    private int suspension;
    private int brakes;
    private int fuel_system;
    private int exhaust;
    private int battery;
    private int full_body;
    private int engine;

    public Service2(int id) {
        this.id = id;
    }

    public Service2() {
    }

    //setter

    public void setId(int id) {
        this.id = id;
    }

    public void setTyres(int tyres) {
        this.tyres = tyres;
    }

    public void setLights(int lights) {
        this.lights = lights;
    }

    public void setHorn(int horn) {
        this.horn = horn;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public void setBrakes(int brakes) {
        this.brakes = brakes;
    }

    public void setFuel_system(int fuel_system) {
        this.fuel_system = fuel_system;
    }

    public void setExhaust(int exhaust) {
        this.exhaust = exhaust;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void setFull_body(int full_body) {
        this.full_body = full_body;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    //getter
    public int getId() {
        return id;
    }

    public int getTyres() {
        return tyres;
    }

    public int getLights() {
        return lights;
    }

    public int getHorn() {
        return horn;
    }

    public int getSuspension() {
        return suspension;
    }

    public int getBrakes() {
        return brakes;
    }

    public int getFuel_system() {
        return fuel_system;
    }

    public int getExhaust() {
        return exhaust;
    }

    public int getBattery() {
        return battery;
    }

    public int getFull_body() {
        return full_body;
    }

    public int getEngine() {
        return engine;
    }
}
