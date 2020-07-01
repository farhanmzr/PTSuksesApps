package com.example.ptsuksesapp.Model;

public class User {

    private int id;
    private String nama;
    private String nohp;
    private  String pendidikan;
    private  String email;

    public User(int id, String nama, int nohp, String pendidikan, String email) {

    }

    public User(int id, String nama, String email, String nohp, String pendidikan) {
        this.id = id;
        this.nama = nama;
        this.nohp = nohp;
        this.pendidikan = pendidikan;
        this.email = email;
    }

    public int getId() {
        return id;
    }


    public  String getNama() {
        return nama;
    }

    public  String getNohp() {
        return nohp;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public String getEmail() {
        return email;
    }
}