package com.mrabid.pro_maker.Model;

import android.widget.TextView;

import java.util.Date;

/**
 * Created by Mr Abid on 9/15/2017.
 */

public class Projects {
    public String nama;
    public String desc;
    public String dateLine;

    public Projects(String nama, String desc, String dateLine) {
        this.nama = nama;
        this.desc = desc;
        this.dateLine = dateLine;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDateLine() {
        return dateLine;
    }

    public void setDateLine(String dateLine) {
        this.dateLine = dateLine;
    }
}
