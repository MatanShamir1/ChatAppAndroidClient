package com.example.chatappandroidclient;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import adapters.ContactListAdapter;

public class Contacts extends AppCompatActivity implements SelectListener {
    List<Contact> contactList;
    private String session;
    private ContactviewModel contactviewModel;
    RecyclerView Contacts;
    private ContactListAdapter adapter;
    private String myName;
    private String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //make the actionbar prettier
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Chat App");
            actionBar.setIcon(R.drawable.ic_action_name);
            ColorDrawable cd = new ColorDrawable(Color.parseColor("#606060"));
            actionBar.setBackgroundDrawable(cd);
        }

        contactviewModel = new ContactviewModel(getIntent().getExtras().getString("1"));


        this.session = getIntent().getExtras().getString("1");
        //get the logged in user's username, for add contact activity.
        this.myName = getIntent().getExtras().getString("myName");

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContact.class);
            intent.putExtra("1",this.session);
            intent.putExtra("myName",this.myName);
            startActivity(intent);
        });

        FloatingActionButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(view -> {
            Intent intent = new Intent(this, Settings.class);
            intent.putExtra("myName",this.myName);
            startActivity(intent);
        });

//        contacts = contactsDao.getContacts();

//        for (int i=0;i<contacts.size()-1;i++) {
//            Contact contact = contacts.remove(i);
//            contactsDao.delete(contact);
//        }
        Contacts = findViewById(R.id.Contacts);
        adapter = new ContactListAdapter(this, this);
        Contacts.setAdapter(adapter);
        Contacts.setLayoutManager(new LinearLayoutManager(this));
        contactviewModel.getContacts().observe(
                this, contacts -> {
                    contactList = new ArrayList<>(contacts);
                    adapter.setContacts(contactList);
                }
        );
        EditText editText = findViewById(R.id.search_sx);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                str = editText.getText().toString();
                List<Contact> contacts = new ArrayList<>();
                for (int i = 0; i < contactList.size(); i++)
                {
                    if (contactList.get(i).getName().contains(str))
                    {
                        contacts.add(contactList.get(i));
                    }
                }
                adapter.setContacts(contacts);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ApiContact  delete = new ApiContact();
        delete.deleteToken(session , this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        int a = 1;
//        contacts.clear();
//        contacts.addAll(contactsDao.getContacts());
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Contact contact) {
        Intent intent = new Intent(this, MessagesList.class);
        intent.putExtra("contact_username", contact.getUsername());
        intent.putExtra("contact_nickname", contact.getName());
        intent.putExtra("myName",this.myName);
        intent.putExtra("contact_server",contact.getServer());
        intent.putExtra("1", session);
        startActivity(intent);
    }


    public void delete_suc() {
  contactList = null;
        session = null;
        contactviewModel = null;
        adapter= null;
        myName = null;
        str ="";
    }
}