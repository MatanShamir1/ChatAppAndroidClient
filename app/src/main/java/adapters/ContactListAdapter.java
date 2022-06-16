package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.chatappandroidclient.ChatAppDB;
import com.example.chatappandroidclient.Contact;
import com.example.chatappandroidclient.ImagesDao;
import com.example.chatappandroidclient.MyApplication;
import com.example.chatappandroidclient.ProfilePicture;
import com.example.chatappandroidclient.R;
import com.example.chatappandroidclient.SelectListener;

import java.util.List;

import converters.DataConverter;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>{

    public ContactListAdapter(Context context , SelectListener listener ) {this.mInflater = LayoutInflater.from(context);
    this.listener = listener;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final CardView cardView;
        private final TextView contact_username;
        private final  TextView time;
        private final TextView last_message;
        private final ImageView contact_image;
        private ContactViewHolder(@NonNull View itemView ) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_gchat_message_other);
            contact_username = itemView.findViewById(R.id.contact_username);
            last_message = itemView.findViewById(R.id.last_message);
            contact_image = itemView.findViewById(R.id.contact_image);
            time = itemView.findViewById(R.id.last_message_date);
        }
    }

    //adapter fields
    private SelectListener listener;
    private final LayoutInflater mInflater;
    private List<Contact> contacts;
    private ChatAppDB db;
    private ImagesDao imagesDao;
    private MyApplication myApplication;

    @NonNull
    @Override
    public ContactListAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = mInflater.inflate(R.layout.contact_layout, parent, false);
        myApplication = new MyApplication();
        db = Room.databaseBuilder(myApplication.context, ChatAppDB.class, "ChatsDB")
                .allowMainThreadQueries()
                .build();

        imagesDao = db.imagesDao();
        return new ContactViewHolder(viewItem);
    }


    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(contacts != null){
            final Contact current = contacts.get(position);
            holder.last_message.setText(current.getLast());
            holder.contact_username.setText(current.getName());
            holder.time.setText(current.getLastDate());

            //try loading the contact image from db. if can't, load jon snow.
            ProfilePicture pp = imagesDao.getPictureById(current.getUsername());
            //if the user has a profile picture uploaded
            if(pp!=null){
                byte[] imageBytes = pp.getPicture();
                Bitmap imageBitmap = DataConverter.convertByteArrayToBitmap(imageBytes);
                holder.contact_image.setImageBitmap(imageBitmap);
                holder.contact_image.getLayoutParams().height = 180;
                holder.contact_image.getLayoutParams().width = 130;
            } else{
                //the user did not upload an image.
                holder.contact_image.setImageResource(R.drawable.jon_snow);
            }
            holder.cardView.setOnClickListener(v -> listener.onItemClick(contacts.get(position)));
        }
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(contacts!=null){
            return contacts.size();
        } else return 0;
    }

    public List<Contact> getContacts() { return contacts; }
}
