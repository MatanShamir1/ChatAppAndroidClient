package com.example.chatappandroidclient;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String username;
    private String name;
    private String server;
    private String lastMessage;
    private String time;

    public Contact(String name, String server, String lastMessage, String time, @NonNull String username) {
        this.name = name;
        this.server = server;
        this.lastMessage = lastMessage;
        this.time = time;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
