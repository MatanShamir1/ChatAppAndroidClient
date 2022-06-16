package com.example.chatappandroidclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.io.IOException;

import converters.DataConverter;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private ChatAppDB db;
    private ImagesDao imagesDao;
    private ImageView imageView;
    MyApplication myApplication;
    private static final int RESULT_LOAD_IMAGE = 1;
    private Bitmap selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myApplication = new MyApplication();
        db = Room.databaseBuilder(myApplication.context, ChatAppDB.class, "ChatsDB")
                .allowMainThreadQueries()
                .build();

        imagesDao = db.imagesDao();
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
        TextView username = findViewById(R.id.Username);
        imagesDao.insert(new ProfilePicture(username.getText().toString(),
                DataConverter.convertBitmapToByteArray(selected)));
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
            Uri imageUri = data.getData();
            try {
                selected = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            ContentResolver contentResolver = myApplication.context.getContentResolver();
//            myApplication.context.grantUriPermission(myApplication.context.getPackageName(), selected,Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
//            contentResolver.takePersistableUriPermission(selected, takeFlags);
            imageView.setImageBitmap(selected);
        }
    }
}