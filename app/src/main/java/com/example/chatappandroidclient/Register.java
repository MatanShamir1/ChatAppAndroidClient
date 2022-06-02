package com.example.chatappandroidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnToLogin = findViewById(R.id.btnToLogin);
        btnToLogin.setOnClickListener(v -> {
            Intent i = new Intent(this, Login.class); // this has information of where i am and where do i want to act on.
            startActivity(i);
        });

    }
}