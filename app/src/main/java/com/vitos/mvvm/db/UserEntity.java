package com.vitos.mvvm.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Victor on 07.06.2017.
 */

@Entity (tableName = "users")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    private long id;

    @ColumnInfo(name = "phoneId")
    private String phoneId;

    @ColumnInfo(name = "provider")
    private String provider;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "thumb")
    private String thumb;

    @ColumnInfo(name = "image")
    private String image;

    @Ignore
    public UserEntity() {
    }

    public UserEntity(long id, String phoneId, String provider, String email, String password, String data, String thumb, String image) {
        this.id = id;
        this.phoneId = phoneId;
        this.provider = provider;
        this.email = email;
        this.password = password;
        this.data = data;
        this.thumb = thumb;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
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
