package com.example.chatappandroidclient;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProfilePicture {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    String username;
    private String picture;

    public ProfilePicture(String username, String picture) {
        this.username = username;
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


}
