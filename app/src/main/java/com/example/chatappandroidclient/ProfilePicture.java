package com.example.chatappandroidclient;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ProfilePicture {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    String username;
    private byte[] picture;

    public ProfilePicture(String username, byte[] picture) {
        this.username = username;
        this.picture = picture;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }


}
