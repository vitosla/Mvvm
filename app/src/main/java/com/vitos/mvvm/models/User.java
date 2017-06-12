package com.vitos.mvvm.models;

import com.vitos.mvvm.MvvmApp;

/**
 * Created by Victor on 05.06.2017.
 */

public class User {

    private String id;
    private String provider;
    private String email;
    private String password;
    private String data;
    private String thumb;
    private String image;

    public User() { }

    public User(String provider) {
        this.id = MvvmApp.getAndroidID();
        this.provider = provider;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
