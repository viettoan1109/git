package com.example.banhangonline.model;

public class LoaiVe {
    public int id;
    public String Tenloaisp;
    public String Hinhanhsp;


    public LoaiVe(int id, String tenloaisp, String hinhanhsp) {
        this.id = id;
        Tenloaisp = tenloaisp;
        Hinhanhsp = hinhanhsp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenloaisp() {
        return Tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        Tenloaisp = tenloaisp;
    }

    public String getHinhanhsp() {
        return Hinhanhsp;
    }

    public void setHinhanhsp(String hinhanhsp) {
        Hinhanhsp = hinhanhsp;
    }

}
