package com.mrabid.pro_maker.Model;

/**
 * Created by Mr Abid on 9/15/2017.
 */

public class Task {
    private String id_task;
    private String id_doer;
    private String id_parent_task;
    private String id_project;
    private String name;
    private String description;
    private String status;
    private String deadline;
    private String created_at;

    public Task(Task task){
        this.setDescription(task.getDescription());
        this.setName(task.getName());
        this.setCreated_at(task.getCreated_at());
        this.setDeadline(task.getDeadline());
        this.setName(task.getName());
        this.setStatus(task.getStatus());
        this.setId_parent_task(task.getId_parent_task());
        this.setId_doer(task.getId_doer());
        this.setId_project(task.getId_project());
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

    public String getId_doer() {
        return id_doer;
    }

    public void setId_doer(String id_doer) {
        this.id_doer = id_doer;
    }

    public String getId_parent_task() {
        return id_parent_task;
    }

    public void setId_parent_task(String id_parent_task) {
        this.id_parent_task = id_parent_task;
    }

    public String getId_project() {
        return id_project;
    }

    public void setId_project(String id_project) {
        this.id_project = id_project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
