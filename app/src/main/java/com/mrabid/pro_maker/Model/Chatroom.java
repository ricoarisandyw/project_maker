package com.mrabid.pro_maker.Model;

/**
 * Created by Mr Abid on 9/27/2017.
 */

public class Chatroom {
    private long id_chatroom;
    private long id_owner;

    public Chatroom(long id_chatroom, long id_owner) {
        this.id_chatroom = id_chatroom;
        this.id_owner = id_owner;
    }

    public long getId_chatroom() {
        return id_chatroom;
    }

    public void setId_chatroom(long id_chatroom) {
        this.id_chatroom = id_chatroom;
    }

    public long getId_owner() {
        return id_owner;
    }

    public void setId_owner(long id_owner) {
        this.id_owner = id_owner;
    }
}
