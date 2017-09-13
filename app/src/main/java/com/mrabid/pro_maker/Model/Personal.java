package com.mrabid.pro_maker.Model;

/**
 * Created by Mr Abid on 9/13/2017.
 */

public class Personal {
    private int image;
    private String nama;
    private String Status;
    private int image_about;

    public Personal(int image, String nama, String status, int image_about) {
        this.image = image;
        this.nama = nama;
        Status = status;
        this.image_about = image_about;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getImage_about() {
        return image_about;
    }

    public void setImage_about(int image_about) {
        this.image_about = image_about;
    }
}
