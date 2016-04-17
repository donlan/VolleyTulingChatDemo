package com.dooze.volleydemo.adapter;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dooze.volleydemo.R;
import com.dooze.volleydemo.bean.ChatMsg;
import com.dooze.volleydemo.utils.Cons;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doo on 16-4-17.
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.Holder> {

    private List<ChatMsg> datas;
    private LayoutInflater inflater;

    public ChatListAdapter(Context context, List<ChatMsg> datas) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
        if(this.datas ==null)
            this.datas = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType== Cons.CHAT_TYPE_FROM)
        {
            return new Holder(inflater.inflate(R.layout.item_chat_from,null),Cons.CHAT_TYPE_FROM);
        }else{
            return new Holder(inflater.inflate(R.layout.item_chat_to,null),Cons.CHAT_TYPE_TO);
        }

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ChatMsg msg = datas.get(position);
        if(msg==null)
            return;
        //holder.head.setImageResource(android.support.v7.appcompat.R.drawable.abc_btn_default_mtrl_shape);
        holder.chatText.setText(msg.getMsg());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public int addMsg(ChatMsg msg)
    {

        datas.add(msg);
        notifyItemInserted(datas.size());
        return datas.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        private ImageView head;
        private TextView chatText;
        public Holder(View itemView,int type) {
            super(itemView);
            if(type==Cons.CHAT_TYPE_FROM)
            {
                head = (ImageView) itemView.findViewById(R.id.item_chat_from_head);
                chatText = (TextView) itemView.findViewById(R.id.item_chat_from_text);
            }else
            {
                head = (ImageView) itemView.findViewById(R.id.item_chat_to_head);
                chatText = (TextView) itemView.findViewById(R.id.item_chat_to_text);
            }
        }
    }

}


