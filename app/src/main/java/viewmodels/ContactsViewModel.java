package viewmodels;

import androidx.lifecycle.LiveData;

import com.example.chatappandroidclient.Contact;

import java.util.List;

public class ContactsViewModel {
    //private ContactsRepository cRepository;
    private LiveData<List<Contact>> contacts;

    public ContactsViewModel (){
        //cRepository = new ContactsRepository();
        //contacts = cRepository.getAll();
    }

    public LiveData<List<Contact>> get() {return contacts;}

//    public void add(Contact contact) {cRepository.add(contact);}
//
//    public void delete(Contact contact) {cRepository.delete(contact);}
//
//    public void reload(Contact contact) {cRepository.reload(contact);}

}
