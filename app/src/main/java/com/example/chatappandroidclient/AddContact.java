package com.example.chatappandroidclient;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {
    private ApiContact apiContactMyServer;
    private ApiContact apiContactOtherServer;
    private String session;
    private String myName;
    private Contact newContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //make the actionbar prettier
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Chat App");
            actionBar.setIcon(R.drawable.ic_action_name);
            ColorDrawable cd = new ColorDrawable(Color.parseColor("#606060"));
            actionBar.setBackgroundDrawable(cd);
        }

        apiContactMyServer = new ApiContact();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        this.session = getIntent().getExtras().getString("1");
        this.myName = getIntent().getExtras().getString("myName");
        Button btnAddHim = findViewById(R.id.btnAddHim);
        btnAddHim.setOnClickListener(view -> {
            EditText contact_username = findViewById(R.id.contact_username_text);
            EditText server_address = findViewById(R.id.server_address_text);
            this.newContact = new Contact(contact_username.getText().toString(),
                    server_address.getText().toString(), null, null,
                    contact_username.getText().toString());
            apiContactOtherServer = new ApiContact("http://" + newContact.getServer() + "/api/");
            apiContactOtherServer.Post_Invitation(myName, newContact.getId(),this, newContact);

        });
    }

    public void connectionInvitation() {
        TextView errorView = findViewById(R.id.error_add_contact);
        errorView.setVisibility(View.VISIBLE);
    }

    public void responseInvitation(int code) {
        if(code == 201){
            apiContactMyServer.Post_Add_Contact(newContact,session, this);
        } else{
            this.connection();
        }
    }

    public void connection() {
        TextView errorView = findViewById(R.id.error_add_contact);
        errorView.setVisibility(View.VISIBLE);
    }

    public void response(int code) {
        if(code == 201){
            this.finish();
        } else{
            this.connection();
        }
    }
}