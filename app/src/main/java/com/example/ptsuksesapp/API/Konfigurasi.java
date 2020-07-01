package com.example.ptsuksesapp.API;

public class Konfigurasi {

    //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
    //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
    //dimana File PHP tersebut berada
    //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
    public static final String URL_REGIST = "http://192.168.1.7/pt_sukses/register.php";
    public static final String URL_GET_ALL = "http://192.168.1.7/pt_sukses/read_all.php";
    public static final String URL_LOGIN = "http://192.168.1.7/pt_sukses/login.php";
    public static final String URL_READ_DETAIL = "http://192.168.1.7/pt_sukses/editprofile.php";
    public static final String URL_UPDATE_PROFILE = "http://192.168.1.7/pt_sukses/updateprofile.php";

    //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NIK = "nik";
    public static final String KEY_EMP_NAMA = "nama"; //desg itu variabel untuk posisi
    public static final String KEY_EMP_USERNAME = "username";
    public static final String KEY_EMP_JENISKELAMIN = "jeniskelamin";
    public static final String KEY_EMP_EMAIL = "email";
    public static final String KEY_EMP_ALAMAT = "alamat";
    public static final String KEY_EMP_DATE = "date";
    public static final String KEY_EMP_NOHP = "nohp";
    public static final String KEY_EMP_PENDIDIKAN = "pendidikan";
    public static final String KEY_EMP_PASSWORD = "password";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "name"; //desg itu variabel untuk posisi
    public static final String TAG_USERNAME = "username";
    public static final String TAG_JENISKELAMIN = "jeniskelamin";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_DATE = "date";
    public static final String TAG_NOHP = "nohp";
    public static final String TAG_PENDIDIKAN = "pendidikan";
    public static final String TAG_PASSWORD = "password";

    //ID karyawan
    //emp itu singkatan dari Employee
    public static final String EMP_ID = "emp_id";

}

