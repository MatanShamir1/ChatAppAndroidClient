package com.example.chatappandroidclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn;
        Button reg = findViewById(R.id.btnRegister);
        imageView = findViewById(R.id.IVPreviewImage);
        btn = findViewById(R.id.BSelectImage);
        btn.setOnClickListener(this);
        Button btnToLogin = findViewById(R.id.btnToLogin);
        btnToLogin.setOnClickListener(v -> {
            Intent i = new Intent(this, Login.class); // this has information of where i am and where do i want to act on.
            startActivity(i);
        });
        reg.setOnClickListener(v -> {
            TextView Name = findViewById(R.id.Name);
            String nickName = Name.getText().toString();
            TextView Username = findViewById(R.id.Username);
            String username = Username.getText().toString();
            TextView Password = findViewById(R.id.Password);
            String password = Password.getText().toString();
            ApiContact apiContact = new ApiContact();
            User new_user = new User(username, password, nickName);
            boolean flag = check_input(username, password, password, nickName);
            if (!flag) {
                return;
            }
            apiContact.Post_Register(new_user, this);
        });

    }

    public void connection() {
        TextView text = (TextView) findViewById(R.id.visible);
        text.setText("connection lost");
        text.setVisibility(View.VISIBLE);
    }

    public void response() {
//        Intent i = new Intent(this, Login.class); // this has information of where i am and where do i want to act on.
//        startActivity(i);
        this.finish();
    }

    public boolean check_input(String userName, String password, String password2, String Nickname) {
        boolean flag = true;
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=!])"
                + "(?=\\S+$).{8,20}$";
        String regex2 = "^[0-9\\-]+$";
        String s;
        if (!password.equals(password2)) {
            flag = false;
            TextView text = (TextView) findViewById(R.id.visible);
            text.setVisibility(View.VISIBLE);
        }
        if (!password.matches(regex)){
            flag = false;
            TextView text = (TextView) findViewById(R.id.visible);
            text.setVisibility(View.VISIBLE);
        }
        if (!userName.matches(regex2)) {
            flag = false;
            TextView text = (TextView) findViewById(R.id.visible);
            text.setVisibility(View.VISIBLE);
        }
        if (!Nickname.matches("[a-zA-Z]+")) {
            flag = false;
            TextView text = (TextView) findViewById(R.id.visible);
            text.setVisibility(View.VISIBLE);
        }
        return flag;
    }

    @Override
    public void onClick(View v) {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selected = data.getData();
            imageView.setImageURI(selected);
        }
    }
}