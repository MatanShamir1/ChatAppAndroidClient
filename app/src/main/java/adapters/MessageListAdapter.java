package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatappandroidclient.Contact;
import com.example.chatappandroidclient.Message;
import com.example.chatappandroidclient.R;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    private final LayoutInflater minflater;
    private List<Message> messageList;

    public MessageListAdapter(Context context) {
        minflater = LayoutInflater.from(context);
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private TextView time;
        private TextView content;

        // need to add image and check if need to create layout for both receive and send.
        private MessageViewHolder(View viewItem) {
            super(viewItem);
            date = viewItem.findViewById(R.id.text_gchat_date_me);
            time = viewItem.findViewById(R.id.text_gchat_timestamp_me);
            content = viewItem.findViewById(R.id.text_gchat_message_me);
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView;
        if(viewType == 0) {
             ItemView = minflater.inflate(R.layout.message_receive_layout, parent, false);
        }
        else {
            ItemView = minflater.inflate(R.layout.message_send_layout, parent, false);
        }
        return new MessageViewHolder(ItemView);
    }

    @Override
    public int getItemViewType(int position) {
        if(messageList.get(position).isSent()){
            return 1;
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        if (messageList != null) {
            Message message = messageList.get(position);
            holder.content.setText(message.getContent());
            holder.date.setText(message.getCreated());
        }
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (messageList != null) {
            return messageList.size();
        } else return 0;
    }
}
