package com.example.chatappandroidclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebserviceHomePage {

    @GET("contacts")
    Call<List<Contact>> getContact(@Header ("Cookie") String session);

    @POST("users/register")
    Call<Void> createUser(@Body User user);

    @POST("users/login")
    Call<Void> checkLogin(@Body User user);

    @POST("contacts")
    Call<Void> addContact(@Header ("Cookie") String session ,@Body Contact newContact);

    @POST("invitations")
    Call<Void> invitation(@Body Invitation invitation);

    @POST("transfer")
    Call<Void> transfer(@Body Transfer transfer);

    @GET("contacts/{id}/messages")
    Call<List<Message>> getMessages(@Header ("Cookie") String session ,@Path("id")String id);

    @POST("users/addToken")
    Call<Void> SendToken(@Header ("Cookie") String session , @Body Token token);

    @POST("contacts/{contact}/messages")
    Call<Void> sendMessage(@Header ("Cookie") String session ,@Path("contact")String contact_username, @Body Content content);
}
