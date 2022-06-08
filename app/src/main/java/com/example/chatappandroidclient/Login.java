package com.example.chatappandroidclient;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnToRegister = findViewById(R.id.btnToRegister);
        btnToRegister.setOnClickListener(v -> {
            Intent i = new Intent(this, Register.class);
            startActivity(i);
        });
        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            TextView Username = findViewById(R.id.text_username);
            String username = Username.getText().toString();
            TextView Password = findViewById(R.id.text_password);
            String password = Password.getText().toString();
            User user = new User(username , password);
            ApiContact apiContact = new ApiContact();
            apiContact.Post_Login(user , this);
        });
    }
    public void response(String session){
        Intent i = new Intent(this, Contacts.class);
        i.putExtra("1", session);
        startActivity(i);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}