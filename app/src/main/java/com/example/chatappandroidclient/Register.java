package com.example.chatappandroidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn;
        imageView = findViewById(R.id.IVPreviewImage);
        btn = findViewById(R.id.BSelectImage);
        btn.setOnClickListener(this);
        Button btnToLogin = findViewById(R.id.btnToLogin);
        btnToLogin.setOnClickListener(v -> {
            Intent i = new Intent(this, Login.class); // this has information of where i am and where do i want to act on.
            startActivity(i);
        });

    }

    @Override
    public void onClick(View v) {
        Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery , RESULT_LOAD_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data ) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selected = data.getData();
            imageView.setImageURI(selected);
        }
    }
}