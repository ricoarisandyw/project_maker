package com.mrabid.pro_maker.Model;

/**
 * Created by Mr Abid on 9/15/2017.
 */

public class Task {
    public String name;
    public String desc;
    public String pemberi;
    public String status;
    public String projTask;

    public Task(String name, String desc, String pemberi, String status, String projTask) {
        this.name = name;
        this.desc = desc;
        this.pemberi = pemberi;
        this.status = status;
        this.projTask = projTask;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPemberi() {
        return pemberi;
    }

    public void setPemberi(String pemberi) {
        this.pemberi = pemberi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjTask() {
        return projTask;
    }

    public void setProjTask(String projTask) {
        this.projTask = projTask;
    }
}
