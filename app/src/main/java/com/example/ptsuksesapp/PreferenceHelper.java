package com.example.ptsuksesapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private final String INTRO = "intro";
    private final String ID = "id";
    private final String NAMA = "nama";
    private final String USERNAME = "username";
    private final String NIK = "nik";
    private final String EMAIL = "email";
    private final String JENISKELAMIN = "jeniskelamin";
    private final String ALAMAT = "alamat";
    private final String DATE = "date";
    private final String NOHP = "nohp";
    private final String PENDIDIKAN = "pendidikan";
    private final String PASSWORD = "password";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(INTRO, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(INTRO, false);
    }

    public void putId(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(ID, loginorout);
        edit.commit();
    }
    public String getID() {
        return app_prefs.getString(NAMA, "");
    }

    public void putNama(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NAMA, loginorout);
        edit.commit();
    }
    public String getNAMA() {
        return app_prefs.getString(NAMA, "");
    }

    public void putUsername(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(USERNAME, loginorout);
        edit.commit();
    }
    public String getUSERNAME() {
        return app_prefs.getString(USERNAME, "");
    }

    public void putNIK(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NIK, loginorout);
        edit.commit();
    }
    public String getNIK() {
        return app_prefs.getString(NIK, "");
    }

    public void putEmail(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(EMAIL, loginorout);
        edit.commit();
    }
    public String getEMAIL() {
        return app_prefs.getString(EMAIL, "");
    }

    public void putJeniskelamin(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(JENISKELAMIN, loginorout);
        edit.commit();
    }
    public String getJENISKELAMIN() {
        return app_prefs.getString(JENISKELAMIN, "");
    }

    public void putAlamat(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(ALAMAT, loginorout);
        edit.commit();
    }
    public String getALAMAT() {
        return app_prefs.getString(ALAMAT, "");
    }

    public void putDate(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(DATE, loginorout);
        edit.commit();
    }
    public String getDATE() {
        return app_prefs.getString(DATE, "");
    }

    public void putNohp(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(NOHP, loginorout);
        edit.commit();
    }
    public String getNOHP() {
        return app_prefs.getString(NOHP, "");
    }

    public void putPassword(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PASSWORD, loginorout);
        edit.commit();
    }
    public String getPASSWORD() {
        return app_prefs.getString(PASSWORD, "");
    }

    public void putPendidikan(String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(PENDIDIKAN, loginorout);
        edit.commit();
    }
    public String getPENDIDIKAN() {
        return app_prefs.getString(PENDIDIKAN, "");
    }

}