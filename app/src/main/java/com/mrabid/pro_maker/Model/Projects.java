package com.mrabid.pro_maker.Model;

import android.widget.TextView;

import java.util.Date;

/**
 * Created by Mr Abid on 9/15/2017.
 */

public class Projects {
    private int id_project;
    private int id_creator;
    public String name;
    public String deadline;
    private int id_corporation;
    public String description;

    public Projects(int id_project, int id_creator, String name, String deadline, int id_corporation, String description) {
        this.id_project = id_project;
        this.id_creator = id_creator;
        this.name = name;
        this.deadline = deadline;
        this.id_corporation = id_corporation;
        this.description = description;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public int getId_creator() {
        return id_creator;
    }

    public void setId_creator(int id_creator) {
        this.id_creator = id_creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getId_corporation() {
        return id_corporation;
    }

    public void setId_corporation(int id_corporation) {
        this.id_corporation = id_corporation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
