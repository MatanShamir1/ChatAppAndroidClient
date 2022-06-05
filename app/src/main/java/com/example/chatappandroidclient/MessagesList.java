package com.example.chatappandroidclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MessagesList extends AppCompatActivity {

    private ChatAppDB db;

    private MessageDao messagesDao;
    private String contact_username;
    private List<Message> messages;
    private ListView Messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);

        if(getIntent().getExtras() != null){
            contact_username = getIntent().getExtras().getString("contact_username");
        }

        FloatingActionButton btnSend = findViewById(R.id.send_btn);
        btnSend.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContact.class);
            startActivity(intent);
        });

        messagesDao = db.messagesDao();

        messages = messagesDao.getMessages(contact_username);

        Messages = findViewById(R.id.messages_list);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);

        Messages.setAdapter(adapter);
    }
}