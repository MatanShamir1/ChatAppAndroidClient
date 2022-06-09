package com.example.chatappandroidclient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddContact extends AppCompatActivity {
    private ApiContact apiContactMyServer;
    private ApiContact apiContactOtherServer;
    private String session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        apiContactMyServer = new ApiContact();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        this.session = getIntent().getExtras().getString("1");
        Button btnAddHim = findViewById(R.id.btnAddHim);
        btnAddHim.setOnClickListener(view -> {
            EditText contact_username = findViewById(R.id.contact_username_text);
            EditText server_address = findViewById(R.id.server_address_text);
            Contact newContact = new Contact(contact_username.getText().toString(),
                    server_address.getText().toString(), null, null,
                    contact_username.getText().toString());
//            apiContactOtherServer = new ApiContact("http://" + newContact.getServer() + "/api/");
//            apiContactOtherServer.
            apiContactMyServer.Post_Add_Contact(newContact,session, this);
        });
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