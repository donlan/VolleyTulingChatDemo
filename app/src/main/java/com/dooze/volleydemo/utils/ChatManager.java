package com.dooze.volleydemo.utils;

import android.content.Context;
import android.graphics.Paint;

import com.android.volley.VolleyError;
import com.dooze.volleydemo.ChatMsgReceiver;
import com.dooze.volleydemo.VolleyInterface;
import com.dooze.volleydemo.VolleyManager;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by doo on 16-4-17.
 */
public class ChatManager {

    private static ChatManager manager;
    private ChatMsgReceiver receiver;
    public static ChatManager get()
    {
        if(manager == null)
        synchronized (ChatManager.class){
            if(manager == null)
                manager = new ChatManager();
        }
        return manager;
    }

    public void initChatReceiver(ChatMsgReceiver chatMsgReceiver)
    {
        receiver = chatMsgReceiver;
    }

    public void sendMsg(Context context,String msg)
    {
        VolleyManager.GET(Cons.URL+msg,"SEND",new VolleyInterface(context,VolleyInterface.mLoadingListener,VolleyInterface.mErrorListener){
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    receiver.onChat(result.getString("text"));
                } catch (JSONException e) {
                    receiver.onChat("ERROR");
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError err) {
                receiver.onChat(err.toString());
            }
        });
    }


}
