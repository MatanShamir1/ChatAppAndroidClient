package com.example.chatappandroidclient;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, Message.class}, version = 1)
public abstract class ChatAppDB extends RoomDatabase {
    public abstract ContactDao contactsDao();
    public abstract MessageDao messagesDao();
}
