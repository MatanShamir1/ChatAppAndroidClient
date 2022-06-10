package com.example.chatappandroidclient;

import android.content.Context;


import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiContact {
    public static Context context;
    private MutableLiveData<List<Contact>> postListData;
    private ContactDao dao;
    Retrofit retrofit;
    WebserviceHomePage webServiceAPI;

    public ApiContact() {
        retrofit = new Retrofit.Builder().
                baseUrl(MyApplication.context.getString(R.string.BaseUrl)).
                addConverterFactory(GsonConverterFactory.create()).build();
        webServiceAPI = retrofit.create(WebserviceHomePage.class);
    }

    public ApiContact(String url) {
        retrofit = new Retrofit.Builder().
                baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        webServiceAPI = retrofit.create(WebserviceHomePage.class);
    }

    public void Post_Login(User user , Login log) {
        Call<Void> call = webServiceAPI.checkLogin(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                String session = response.headers().get("Set-Cookie");
                log.response(session);
            }
        });
    }

    public void Post_Register(User user , Register register) {
        Call<Void> call = webServiceAPI.createUser(user);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                register.connection();
            }

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                register.response();
            }
        });
    }

    public void Post_Add_Contact(Contact newContact, String session, AddContact addContact) {
        Call<Void> call = webServiceAPI.addContact(session, newContact);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                addContact.connection();
            }

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                addContact.response(response.code());
            }
        });
    }

    public void Post_Invitation(String from, String to, AddContact addContact) {
        Call<Void> call = webServiceAPI.invitation(new Invitation(from,to,MyApplication.context.getString(R.string.BaseUrl)));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                addContact.connectionInvitation();
            }

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                addContact.responseInvitation(response.code());
            }
        });
    }

    public void get_messages(MessageRepo.MessageList repo, String session, String id){
        Call<List<Message>> call = webServiceAPI.getMessages(session ,id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
            }

            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                repo.fill_messages(response.body());
            }
        });

    }
    public void get_all_contacts(ContactsRepo.ContactsList repo , String Session){
        Call<List<Contact>> call = webServiceAPI.getContact(Session);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
            }

            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                repo.fill_contacts(response.body());
            }
        });
    }
}