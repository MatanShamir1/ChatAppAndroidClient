package com.example.chatappandroidclient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MessageViewModel extends ViewModel {

    private MessageRepo repo;
    private LiveData<List<Message>> messageList;

    public MessageViewModel(String Session, String idCurr) {
        repo = new MessageRepo(Session, idCurr);
        messageList = repo.getAll();
    }

    public LiveData<List<Message>> getMessages() {
        return messageList;
    }

}
