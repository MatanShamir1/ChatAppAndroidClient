package com.example.chatappandroidclient;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import adapters.MessageListAdapter;

public class MessagesList extends AppCompatActivity {

    private ChatAppDB db;

    private MessageDao messagesDao;
    private String contact_username;
    private List<Message> messages;
    private RecyclerView Messages;
    private MessageViewModel viewModel;
    private String myName;
    private String contact_server;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);
        if (getIntent().getExtras() != null) {
            contact_username = getIntent().getExtras().getString("contact_username");
            myName = getIntent().getExtras().getString("myName");
            contact_server = getIntent().getExtras().getString("contact_server");
        }

        viewModel = new MessageViewModel(getIntent().getExtras().getString("1"), contact_username);
        FloatingActionButton btnSend = findViewById(R.id.send_btn);
        btnSend.setOnClickListener(view -> {
            viewModel.sendMessage(myName,contact_username,contact_server,
                    ((EditText)findViewById(R.id.textInput)).getText().toString());
        });

        Messages = findViewById(R.id.messages_list);
        final MessageListAdapter adapter = new MessageListAdapter(this);
        Messages.setAdapter(adapter);
        Messages.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getMessages().observe(this , messages -> {
            adapter.setMessageList(messages);
        });

//        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);


    }
}