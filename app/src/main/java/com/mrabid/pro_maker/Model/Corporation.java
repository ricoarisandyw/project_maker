package com.mrabid.pro_maker.Model;

/**
 * Created by Reaper on 9/16/2017.
 */

public class Corporation {
    private String id_corporation;
    private String name;
    private String id_owner;
    private String address;
    private String description;
    private String id_parent;

    public Corporation(){}

    public Corporation(String id_corporation, String name, String description, String address, String id_owner, String id_parent) {
        this.id_corporation = id_corporation;
        this.name = name;
        this.description = description;
        this.address = address;
        this.id_owner = id_owner;
        this.id_parent = id_parent;
    }


    public String getId_corporation() {
        return id_corporation;
    }

    public void setId_corporation(String id_corporation) {
        this.id_corporation = id_corporation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_owner() {
        return id_owner;
    }

    public void setId_owner(String id_owner) {
        this.id_owner = id_owner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_parent() {
        return id_parent;
    }

    public void setId_parent(String id_parent) {
        this.id_parent = id_parent;
    }
}
