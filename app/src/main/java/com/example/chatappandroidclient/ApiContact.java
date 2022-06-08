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
//        // MutableLiveData<List<Contact>> postListData, ContactDao dao
//        this.postListData = postListData;
//        this.dao = dao;
        retrofit = new Retrofit.Builder().
                baseUrl(MyApplication.context.getString(R.string.BaseUrl)).
                addConverterFactory(GsonConverterFactory.create()).build();
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
                System.out.println(response);
                register.response();
            }
        });
    }
    public void get_all_contacts( Contacts contacts , String Session){
        Call<List<Contact>> call = webServiceAPI.getContact(Session);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }

            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contacts.fill_contacts(response.body());
            }
        });
    }
}