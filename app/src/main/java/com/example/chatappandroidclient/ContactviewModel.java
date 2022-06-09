package com.example.chatappandroidclient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContactviewModel extends ViewModel {
    private ContactsRepo repo;
    private LiveData<List<Contact>> contacts;


    public ContactviewModel(String Session) {
        repo = new ContactsRepo(Session);
        contacts = repo.getAll();
    }

    public LiveData<List<Contact>> getContacts() {
        return contacts;
    }
}
