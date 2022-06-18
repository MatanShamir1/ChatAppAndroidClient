package com.example.chatappandroidclient;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MessageViewModel extends ViewModel {

    public static MessageRepo repo;
    private LiveData<List<Message>> messageList;

    public MessageViewModel(String Session, String idCurr) {
        if(repo == null){
            repo = new MessageRepo(Session, idCurr);
        }
        messageList = repo.getAll();
    }

    public MessageViewModel() {
    }

    public void newMessage(){
        repo.response();
    }

    public void sendMessage(String myName, String contact_username, String contact_server, String content) {
        repo.sendMessage(myName, contact_username, contact_server, content);
    }

    public LiveData<List<Message>> getMessages() {
        return messageList;
    }

    public Bitmap getContactImageByUsername(String contact_name) {
        return repo.getContactImageByUsername(contact_name);
    }
}
