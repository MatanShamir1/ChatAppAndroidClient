package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatappandroidclient.Contact;
import com.example.chatappandroidclient.R;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>{

    public ContactListAdapter(Context context) {this.mInflater = LayoutInflater.from(context);}

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contact_username;
        private final TextView last_message;
        private final ImageView contact_image;

        private ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contact_username = itemView.findViewById(R.id.contact_username);
            last_message = itemView.findViewById(R.id.last_message);
            contact_image = itemView.findViewById(R.id.contact_image);
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> contacts;
    @NonNull
    @Override
    public ContactListAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = mInflater.inflate(R.layout.contact_layout, parent, false);
        return new ContactViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListAdapter.ContactViewHolder holder, int position) {
        if(contacts != null){
            final Contact current = contacts.get(position);
            holder.last_message.setText(current.getLastMessage());
            holder.contact_username.setText(current.getName());
            //holder.profile_pic.setImageResource(current.getImage());
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
