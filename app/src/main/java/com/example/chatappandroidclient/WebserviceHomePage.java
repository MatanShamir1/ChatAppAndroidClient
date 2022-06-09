package com.example.chatappandroidclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface WebserviceHomePage {

    @GET("contacts")
    Call<List<Contact>> getContact(@Header ("Cookie") String session);

    @POST("contacts")
    Call<Void> createContact(@Body Contact contact);

    @POST("users/register")
    Call<Void> createUser(@Body User user);


    @POST("users/login")
    Call<Void> checkLogin(@Body User user);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMessages(@Header ("Cookie") String session ,@Path("id")String id);


}
