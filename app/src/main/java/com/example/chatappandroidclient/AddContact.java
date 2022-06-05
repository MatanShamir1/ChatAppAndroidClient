package com.example.chatappandroidclient;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddContact extends AppCompatActivity {

    private ChatAppDB db;
    private ContactDao contactsDao;
    private MessageDao messagesDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        db = Room.databaseBuilder(getApplicationContext(), ChatAppDB.class, "ChatsDB")
                .allowMainThreadQueries()
                .build();

        contactsDao = db.contactsDao();

        messagesDao = db.messagesDao();

        Button btnAddHim = findViewById(R.id.btnAddHim);
        btnAddHim.setOnClickListener(view -> {
            EditText contact_username = findViewById(R.id.contact_username_text);
            EditText server_address = findViewById(R.id.server_address_text);
            Contact newContact = new Contact(contact_username.getText().toString(),
                    server_address.getText().toString(),"","",
                    contact_username.getText().toString());
            contactsDao.insert(newContact);
            finish();
        });
    }
}