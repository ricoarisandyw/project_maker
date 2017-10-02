package com.mrabid.pro_maker.Model;

/**
 * Created by Mr Abid on 9/27/2017.
 */

public class ChatMessage {
    private long id_sender;
    private long id_chatroom;
    private String message;
    private long status;
    private String send_at;

    public ChatMessage(long id_sender, long id_chatroom, String message, long status, String send_at) {
        this.id_sender = id_sender;
        this.id_chatroom = id_chatroom;
        this.message = message;
        this.status = status;
        this.send_at = send_at;
    }

    public long getId_sender() {
        return id_sender;
    }

    public void setId_sender(long id_sender) {
        this.id_sender = id_sender;
    }

    public long getId_chatroom() {
        return id_chatroom;
    }

    public void setId_chatroom(long id_chatroom) {
        this.id_chatroom = id_chatroom;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getSend_at() {
        return send_at;
    }

    public void setSend_at(String send_at) {
        this.send_at = send_at;
    }
}
