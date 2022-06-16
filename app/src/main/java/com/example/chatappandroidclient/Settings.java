package com.example.chatappandroidclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import converters.DataConverter;

public class Settings extends AppCompatActivity implements View.OnClickListener {
    private ChatAppDB db;
    private ImagesDao imagesDao;
    MyApplication myApplication;
    final int CAMERA_INTENT = 51;
    private ImageView profilePicture;
    private Bitmap selected;
    private TextView savedChanges;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        db = Room.databaseBuilder(myApplication.context, ChatAppDB.class, "ChatsDB")
                .allowMainThreadQueries()
                .build();
        imagesDao = db.imagesDao();

        profilePicture = findViewById(R.id.profilePicture);

        ProfilePicture pp = imagesDao.getPictureById(getIntent().getExtras().getString("myName"));

        //if the user has a profile picture uploaded
        if (pp != null) {
            byte[] imageBytes = pp.getPicture();
            selected = DataConverter.convertByteArrayToBitmap(imageBytes);
            profilePicture.setImageBitmap(selected);
        } else {
            //the user did not upload an image.
            profilePicture.setImageResource(R.drawable.jon_snow);
        }

        FloatingActionButton btn_back = findViewById(R.id.btn_back_to_contacts);
        btn_back.setOnClickListener(view -> {
            finish();
        });

        //thi is the view that shows success
        savedChanges = findViewById(R.id.savedChanges);

        Button btnEditPic = findViewById(R.id.btnEditPic);
        btnEditPic.setOnClickListener(this);

        Button btnEditNewPic = findViewById(R.id.btnEditNewPic);
        btnEditNewPic.setOnClickListener(this);

        Button btnEditServer = findViewById(R.id.btnEditServer);
        //this enables setting the server address that we work with, to a different one.
        btnEditServer.setOnClickListener(v -> {
            EditText editServerText = findViewById(R.id.editServerText);
            ApiContact.baseUrl = editServerText.getText().toString();
            savedChanges.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onClick(View v) {
        //need to find out if the action was take picture or add image.
        if ((Button) v == findViewById(R.id.btnEditNewPic)) {
            takePicture(v);
        } else {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(gallery, Register.RESULT_LOAD_IMAGE);
        }
    }

    public void takePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAMERA_INTENT);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if took a picture
        if (requestCode == CAMERA_INTENT && resultCode == Activity.RESULT_OK) {
            selected = (Bitmap) data.getExtras().get("data");
        }
        //if chosen a picture from gallery
        else if (requestCode == Register.RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                selected = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selected != null) {
            profilePicture.setImageBitmap(selected);
            savedChanges.setVisibility(View.VISIBLE);
        }
    }
}