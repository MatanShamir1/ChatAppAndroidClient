package com.example.chatappandroidclient;

import static com.example.chatappandroidclient.MyApplication.context;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import adapters.MessageListAdapter;

public class MessagesList extends AppCompatActivity {
    private String contact_username;
    private List<Message> messages;
    private RecyclerView Messages;
    private MessageViewModel viewModel;
    private String myName;
    private String contact_server;
    private String contact_nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages_list);
        if (getIntent().getExtras() != null) {
            contact_username = getIntent().getExtras().getString("contact_username");
            myName = getIntent().getExtras().getString("myName");
            contact_server = getIntent().getExtras().getString("contact_server");
            contact_nickname = getIntent().getExtras().getString("contact_nickname");
        }

        viewModel = new MessageViewModel(getIntent().getExtras().getString("1"), contact_username);
        FloatingActionButton btnSend = findViewById(R.id.send_btn);
        btnSend.setOnClickListener(view -> {
            viewModel.sendMessage(myName, contact_username, contact_server,
                    ((EditText) findViewById(R.id.textInput)).getText().toString());
            EditText paddle = ((EditText) findViewById(R.id.textInput));
            paddle.setText("");
        });

        FloatingActionButton btn_back = findViewById(R.id.btn_back_to_contacts);
        btn_back.setOnClickListener(view -> {
            finish();
        });

        //make the contact username visible
        TextView contact_name = findViewById(R.id.contact_name);
        contact_name.setText("Chat with: " + this.contact_nickname);

        //make the contact profile picture visible
        ImageView contact_image = findViewById(R.id.contact_image);
        Bitmap imgBitmap = viewModel.getContactImageByUsername(this.contact_username);
        if(imgBitmap != null){
            contact_image.setImageBitmap(imgBitmap);
            contact_image.getLayoutParams().height = 180;
            contact_image.getLayoutParams().width = 130;
        } else {
            contact_image.setImageResource(R.drawable.jon_snow);
        }

        Messages = findViewById(R.id.messages_list);
        final MessageListAdapter adapter = new MessageListAdapter(this);
        Messages.setAdapter(adapter);
        Messages.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getMessages().observe(this, messages -> {
            adapter.setMessageList(messages);
            Messages.scrollToPosition(messages.size() - 1);
            View view = this.getCurrentFocus();

            // if nothing is currently
            // focus then this will protect
            // the app from crash
            if (view != null) {

                // now assign the system
                // service to InputMethodManager
                InputMethodManager manager
                        = (InputMethodManager)
                        getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                manager
                        .hideSoftInputFromWindow(
                                view.getWindowToken(), 0);
                EditText paddle = ((EditText) findViewById(R.id.textInput));
                paddle.setText("");
            }});

//        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);

        }


        @Override
        protected void onResume () {
            super.onResume();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Messages.getWindowToken(), 0);
        }
    }