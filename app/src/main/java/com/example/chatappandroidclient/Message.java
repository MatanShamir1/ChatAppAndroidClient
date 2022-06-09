package com.example.chatappandroidclient;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = false)
    int id;
    private String content;
    private String contactUsername;
    private boolean sent;
    private String created;

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Message(int id, String content, String contactUsername, boolean sent, String created) {
        this.id = id;
        this.content = content;
        this.contactUsername = contactUsername;
        this.sent = sent;
        this.created = created;
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
        return created;
    }

    public void setTime(String time) {
        this.created = time;
    }
}
