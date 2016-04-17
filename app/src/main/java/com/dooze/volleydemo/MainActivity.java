package com.dooze.volleydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dooze.volleydemo.adapter.ChatListAdapter;
import com.dooze.volleydemo.bean.ChatMsg;
import com.dooze.volleydemo.utils.ChatManager;
import com.dooze.volleydemo.utils.Cons;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView chatList;
    TextView sendEt;
    Button sendBt;
    ChatListAdapter adapter;
    List<ChatMsg> msgs = new ArrayList<>();
    ChatMsgReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendEt = (EditText) findViewById(R.id.chat_et);
        sendBt = (Button) findViewById(R.id.chat_send_bt);
        chatList = (RecyclerView) findViewById(R.id.chat_list);
        chatList.setLayoutManager(new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false));
        adapter = new ChatListAdapter(this,msgs);
        chatList.setAdapter(adapter);
        chatList.setLayoutFrozen(false);
        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = sendEt.getText().toString();
                if(msg.equals(""))
                    return;
                adapter.addMsg(new ChatMsg(msg, Cons.CHAT_TYPE_TO));
                ChatManager.get().sendMsg(MainActivity.this,msg);
                sendEt.setText("");
                chatList.smoothScrollToPosition(msgs.size());
            }
        });
        receiver = new ChatMsgReceiver() {
            @Override
            public void onChat(String msg) {
                adapter.addMsg(new ChatMsg(msg, Cons.CHAT_TYPE_FROM));
                chatList.smoothScrollToPosition(msgs.size());
            }
        };
        ChatManager.get().initChatReceiver(receiver);
    }


}
