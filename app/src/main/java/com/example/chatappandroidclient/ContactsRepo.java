package com.example.chatappandroidclient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Application;
import android.content.Context;

public class ContactsRepo {
    private ApiContact apiContact;
    private ContactsList contactsList;
    private ChatAppDB db;
    private ContactDao contactsDao;
    MyApplication myApplication;
    String Session;

    public ContactsRepo(String session) {
        myApplication = new MyApplication();

        db = Room.databaseBuilder(myApplication.context, ChatAppDB.class, "ChatsDB")
                .allowMainThreadQueries()
                .build();

        contactsDao = db.contactsDao();
        contactsList = new ContactsList();
        apiContact = new ApiContact();
        Session = session;
    }

    public LiveData<List<Contact>> getAll() {
        return contactsList;
    }


    public class ContactsList extends MutableLiveData<List<Contact>> {
        public ContactsList()  {
            super();
            List<Contact> l = new ArrayList<>();
            l = contactsDao.getContacts();
            setValue(l);
        }

        @Override
        protected void onActive() {
            super.onActive();
            apiContact.get_all_contacts(this ,Session);
        }

        public void fill_contacts(List<Contact> body) {
           setValue(body);
           for (Contact c : body){
               if(contactsDao.getContactById(c.getId()) == null){
                   contactsDao.insert(c);
               }
               else {
                   contactsDao.update(c);
               }
           }
        }
    }
}
