package com.example.chatappandroidclient;

import android.graphics.Bitmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import converters.DataConverter;

public class MessageRepo {
    private String session;
    private MessageDao dao;
    private ImagesDao imagesDao;
    private ChatAppDB db;
    private MyApplication myApplication;
    private ApiContact apiContact;
    private MessageList messageList;
    private String idCurr;
    private ApiContact apiMessageOtherServer;

    public MessageRepo(String session, String idCurr) {
        this.idCurr = idCurr;
        this.session = session;
        myApplication = new MyApplication();

        db = Room.databaseBuilder(myApplication.context, ChatAppDB.class, "ChatsDB")
                .allowMainThreadQueries()
                .build();

        dao = db.messagesDao();
        imagesDao = db.imagesDao();
        messageList = new MessageList();
        apiContact = new ApiContact();
    }

    public void sendMessage(String myName, String contact_username, String contact_server, String content) {
        //need to change to that when the server will become real!!!
        String new_ip = "";
        String User[] = contact_server.split(":");
        if (User[0].equals("localhost")) {
            new_ip = "10.0.2.2:";
        }
        if (new_ip.equals("")) {
            apiMessageOtherServer = new ApiContact("http://" + contact_server + "/api/");
            apiMessageOtherServer.postTransfer(myName, contact_username, content, this);
        } else {
            apiMessageOtherServer = new ApiContact("http://" + "10.0.2.2:5243" + "/api/");
            apiMessageOtherServer.postTransfer(myName, contact_username, content, this);
        }
    }

    public LiveData<List<Message>> getAll() {
        return messageList;
    }

    public void connectionTransfer() {
        //need to notify the user somehow
    }

    public void responseTransfer(String content, String contact_username) {
        apiContact.postSendMessage(session, content, contact_username, this);
    }

    public void connection() {
        //need to notify the user somehow
    }

    public void response() {
        apiContact.get_messages(messageList, session, idCurr);
    }

    public Bitmap getContactImageByUsername(String contact_name) {
        ProfilePicture pp = imagesDao.getPictureById(contact_name);
        if(pp != null){
            return DataConverter.convertByteArrayToBitmap(pp.getPicture());
        } else {
            return null;
        }

    }

    public class MessageList extends MutableLiveData<List<Message>> {
        public MessageList() {
            super();
            List<Message> l = new ArrayList<>();
            l = dao.getMessages(idCurr);
            setValue(l);
        }

        @Override
        protected void onActive() {
            super.onActive();
            apiContact.get_messages(this, session, idCurr);
        }


        public void fill_messages(List<Message> body) {
            setValue(body);
            for (Message c : body) {
                if (dao.getMessageById(c.id) == null) {
                    c.setContactUsername(idCurr);
                    dao.insert(c);
                } else {
                    dao.update(c);
                }
            }
        }
    }
}
