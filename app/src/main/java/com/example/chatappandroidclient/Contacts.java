package com.example.chatappandroidclient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Contacts extends AppCompatActivity {

    List<String> tempContacts = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        tempContacts.add("Corali");
        tempContacts.add("Itamarmar");
        tempContacts.add("Amitush");

        ListView Contacts = findViewById(R.id.Contacts);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tempContacts);

        Contacts.setAdapter(adapter);

    }
}