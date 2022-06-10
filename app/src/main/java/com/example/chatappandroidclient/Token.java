package com.example.chatappandroidclient;

public class Token {
    private String newtoken;

    public String getToken() {
        return newtoken;
    }

    public void setToken(String token) {
        this.newtoken = token;
    }

    public Token(String token) {
        this.newtoken = token;
    }
}
