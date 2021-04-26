package com.rajesh.garaajwala.models;

public class MostPopularHelper {

    int image;
    String title;
    String des;

    public MostPopularHelper(int image, String title, String des) {
        this.image = image;
        this.title = title;
        this.des = des;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
