package com.rajesh.garaajwala.models;

public class FeaturedHelper {

    int image;
    String title;
    String des;
    String checkout;
    String email;

    public FeaturedHelper(int image, String title, String des,String checkout,String email) {
        this.image = image;
        this.title = title;
        this.des = des;
        this.checkout = checkout;
        this.email = email;
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

    public String getCheckout() {
        return checkout;
    }

    public String getEmail() {
        return email;
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

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
