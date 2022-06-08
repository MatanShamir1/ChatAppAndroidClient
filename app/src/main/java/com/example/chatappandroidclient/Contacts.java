package com.example.chatappandroidclient;

import android.content.Intent;
import android.content.MutableContextWrapper;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import adapters.ContactListAdapter;

public class Contacts extends AppCompatActivity implements SelectListener {


    private ChatAppDB db;
    private ContactDao contactsDao;

    private List<Contact> contacts;
    RecyclerView Contacts;
    private ContactListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        String Session = getIntent().getExtras().getString("1");
        db = Room.databaseBuilder(getApplicationContext(), ChatAppDB.class, "ChatsDB")
                .allowMainThreadQueries()
                .build();

        contactsDao = db.contactsDao();

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContact.class);
            startActivity(intent);
        });

        ApiContact apiContact = new ApiContact();
        apiContact.get_all_contacts(this ,Session);
        contacts = contactsDao.getContacts();

//        for (int i=0;i<contacts.size()-1;i++) {
//            Contact contact = contacts.remove(i);
//            contactsDao.delete(contact);
//        }

        Contacts = findViewById(R.id.Contacts);

        adapter = new ContactListAdapter(this, this);
        Contacts.setAdapter(adapter);
        Contacts.setLayoutManager(new LinearLayoutManager(this));
        adapter.setContacts(contacts);

//        Contacts.setOnItemLongClickListener((adapterView, view, i, l) -> {
//            Contact contact = contacts.remove(i);
//            contactsDao.delete(contact);
//            adapter.notifyDataSetChanged();
//            return true;
//         });

//        Contacts.setOnItemClickListener((adapterView, view, i, l) -> {
//            Intent intent = new Intent(this, MessagesList.class);
//            intent.putExtra("contact_username", contacts.get(i).getUsername());
//            startActivity(intent);
//        });
    }
    public void fill_contacts(List<Contact> contacts1){
        this.contacts = contacts1;
        Contacts = findViewById(R.id.Contacts);
        adapter = new ContactListAdapter(this, this);
        Contacts.setAdapter(adapter);
        Contacts.setLayoutManager(new LinearLayoutManager(this));
        adapter.setContacts(contacts);
    }
    @Override
    protected void onResume() {
        super.onResume();
        contacts.clear();
        contacts.addAll(contactsDao.getContacts());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Contact contact) {
        Intent intent = new Intent(this, MessagesList.class);
        intent.putExtra("contact_username", contact.getUsername());
        startActivity(intent);
    }
}