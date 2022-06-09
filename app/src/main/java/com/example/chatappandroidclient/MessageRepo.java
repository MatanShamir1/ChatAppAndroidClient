package com.example.chatappandroidclient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MessageRepo {
    private String session;
    private MessageDao dao;
    private ChatAppDB db;
    private MyApplication myApplication;
    private ApiContact apiContact;
    private MessageList messageList;
    private String idCurr;

    public MessageRepo(String session, String idCurr) {
        this.idCurr = idCurr;
        this.session = session;
        myApplication = new MyApplication();

        db = Room.databaseBuilder(myApplication.context, ChatAppDB.class, "ChatsDB")
                .allowMainThreadQueries()
                .build();

        dao = db.messagesDao();
        messageList = new MessageList();
        apiContact = new ApiContact();
    }

    public LiveData<List<Message>> getAll() {
        return messageList;
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
                if (dao.getMessageById(c.id)==null) {
                    c.setContactUsername(idCurr);
                    dao.insert(c);
                } else {
                    dao.update(c);
                }
            }
        }
    }
}
