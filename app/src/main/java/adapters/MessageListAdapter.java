package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatappandroidclient.Message;
import com.example.chatappandroidclient.R;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.MessageViewHolder> {

    private final LayoutInflater minflater;
    private List<Message> messageList;

    public MessageListAdapter(Context context) {
        minflater = LayoutInflater.from(context);
    }

    abstract class MessageViewHolder extends RecyclerView.ViewHolder{

        public MessageViewHolder(@NonNull View viewItem) {
            super(viewItem);
        }
    }

    class MessageSendViewHolder extends MessageViewHolder {
        private TextView time;
        private TextView content;
        // need to add image and check if need to create layout for both receive and send.
        public MessageSendViewHolder(View viewItem) {
            super(viewItem);
            //notice the "me" and the "other"! these are different view components!!!
            time = viewItem.findViewById(R.id.text_gchat_timestamp_other);
            content = viewItem.findViewById(R.id.text_gchat_message_other);
        }
    }

    class MessageReceiveViewHolder extends MessageViewHolder {
        private TextView time;
        private TextView content;
        // need to add image and check if need to create layout for both receive and send.
        public MessageReceiveViewHolder(View viewItem, String type) {
            super(viewItem);
            time = viewItem.findViewById(R.id.text_gchat_timestamp_me);
            content = viewItem.findViewById(R.id.text_gchat_message_me);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messageList.get(position).isSent()){
            return 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ItemView;
        if(viewType == 0) {
            ItemView = minflater.inflate(R.layout.message_receive_layout, parent, false);
            return new MessageReceiveViewHolder(ItemView,"receive");
        }
        else {
            ItemView = minflater.inflate(R.layout.message_send_layout, parent, false);
            return new MessageSendViewHolder(ItemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        if (messageList != null) {
            Message message = messageList.get(position);
            if(holder instanceof MessageSendViewHolder){
                ((MessageSendViewHolder)holder).content.setText(message.getContent());
                ((MessageSendViewHolder)holder).time.setText(message.getCreated());
            } else {
                ((MessageReceiveViewHolder)holder).content.setText(message.getContent());
                ((MessageReceiveViewHolder)holder).time.setText(message.getCreated());
            }

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
