package com.dooze.volleydemo.bean;

/**
 * Created by doo on 16-4-17.
 */
public class ChatMsg {
    private String msg;
    private int type;

    public ChatMsg(){}

    public ChatMsg(String msg, int type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
