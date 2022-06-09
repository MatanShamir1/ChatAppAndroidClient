package com.example.chatappandroidclient;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    List<Contact> getContacts();

    @Query("SELECT * FROM Contact WHERE id = :id")
    Contact getContactById(String id);

    @Insert
    void insert(Contact... contacts);
    @Update
    void update(Contact... contacts);
    @Delete
    void delete(Contact... contacts);
}
