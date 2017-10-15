package com.mrabid.pro_maker.Model;

/**
 * Created by Mr Abid on 10/4/2017.
 */

public class User {
    private int id;
    private String username;
    private String token;
    private String passwor_hash;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPasswor_hash() {
        return passwor_hash;
    }

    public void setPasswor_hash(String passwor_hash) {
        this.passwor_hash = passwor_hash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
