package com.example.chatappandroidclient;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ImagesDao {

    @Query("SELECT * FROM ProfilePicture WHERE username = :username")
    ProfilePicture getPictureById(String username);

    @Insert
    void insert(ProfilePicture... profilePictures);
    @Update
    void update(ProfilePicture... profilePictures);
    @Delete
    void delete(ProfilePicture... profilePictures);
}
