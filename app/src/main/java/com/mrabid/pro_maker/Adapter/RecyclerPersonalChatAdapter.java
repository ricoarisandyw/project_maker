package com.mrabid.pro_maker.Adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mrabid.pro_maker.Model.ChatMessage;
import com.mrabid.pro_maker.Model.Chatroom;
import com.mrabid.pro_maker.Model.User;
import com.mrabid.pro_maker.R;

import java.util.List;

/**
 * Created by Mr Abid on 10/3/2017.
 */

public class RecyclerPersonalChatAdapter extends RecyclerView.Adapter<RecyclerPersonalChatAdapter.ViewHolder> {

    Context context;
    List<ChatMessage> listMessages;
    List<Chatroom> listRoom;
    User user;

    public RecyclerPersonalChatAdapter(Context context, List<ChatMessage> listMessage){
        this.context = context;
        this.listMessages = listMessage;
    }

    @Override
    public RecyclerPersonalChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        for(int i=0;i<listMessages.size();i++){
            if(listMessages.get(i).getId_sender()== user.getId()){
                return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.message_sent_view,null));
            }else {
            }
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.message_received_view,null));
    }

    @Override
    public void onBindViewHolder(final RecyclerPersonalChatAdapter.ViewHolder holder,final int position) {
        final ChatMessage p = listMessages.get(position);
    }

    @Override
    public int getItemCount() {
        return listMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView messageReceived,messageSent,name;
        ImageView profilChat;
        TextView dateTimeSent,dateTimeReceived;

        public ViewHolder(View itemView) {
            super(itemView);
            messageReceived = (TextView)itemView.findViewById(R.id.txt_content_received);
            messageSent = (TextView)itemView.findViewById(R.id.txt_content_sent);
            name = (TextView)itemView.findViewById(R.id.txt_name_received);
            profilChat = (ImageView) itemView.findViewById(R.id.img_profile_received);
            dateTimeSent = (TextView)itemView.findViewById(R.id.txt_date_received);
            dateTimeReceived = (TextView)itemView.findViewById(R.id.txt_date_sent);

        }
    }
}
