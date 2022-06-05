package com.example.chatappandroidclient;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    int id;
    private String content;
    private String contactUsername;
    private boolean sent;
    private String time;

    public Message(int id, String content, String contactUsername, boolean sent, String time) {
        this.id = id;
        this.content = content;
        this.contactUsername = contactUsername;
        this.sent = sent;
        this.time = time;
    }

    public String getContactUsername() {
        return contactUsername;
    }

    public void setContactUsername(String contactUsername) {
        this.contactUsername = contactUsername;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contactUsername;
    }

    public void setContact(String contactUsername) {
        this.contactUsername = contactUsername;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
