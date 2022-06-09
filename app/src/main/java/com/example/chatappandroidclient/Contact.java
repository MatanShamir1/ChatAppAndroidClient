package com.example.chatappandroidclient;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;
    private String name;
    private String server;
    private String last;
    private String lastDate;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @NonNull
    public String getUserName() {
        return id;
    }

    public void setUserName(@NonNull String userName) {
        this.id = userName;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public Contact(String name, String server, String last, String lastDate, @NonNull String id) {
        this.name = name;
        this.server = server;
        this.last = last;
        this.lastDate = lastDate;
        this.id = id;
    }

    public String getLastdate() {
        return lastDate;
    }

    public void setLastdate(String lastdate) {
        this.lastDate = lastdate;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }



    public String getUsername() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }



    public void setUsername(String username) {
        this.id = username;
    }

}
