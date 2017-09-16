package com.mrabid.pro_maker.Model;

/**
 * Created by Mr Abid on 9/15/2017.
 */

public class Task {
    private String id_task;
    public String name;
    public String desc;
    public String id_doer;
    public String status;
    public String projTask;

    public Task(String name, String desc, String id_doer, String status, String projTask) {
        this.name = name;
        this.desc = desc;
        this.id_doer = id_doer;
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

    public String getid_doer() {
        return id_doer;
    }

    public void setid_doer(String id_doer) {
        this.id_doer = id_doer;
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

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }
}
