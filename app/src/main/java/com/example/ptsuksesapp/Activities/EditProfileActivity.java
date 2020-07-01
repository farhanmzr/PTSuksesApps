package com.example.ptsuksesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ptsuksesapp.API.Konfigurasi;
import com.example.ptsuksesapp.API.MySingleton;
import com.example.ptsuksesapp.API.SharedPrefManager;
import com.example.ptsuksesapp.PreferenceHelper;
import com.example.ptsuksesapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

public class EditProfileActivity extends AppCompatActivity {

    private ImageView ic_back, img_save;
    private EditText etnik, etnama, etusername, etjeniskelamin, etemail, etalamat, etdate, etnohp, etpendidikan, etpassword;

    private PreferenceHelper preferenceHelper;
    private RequestQueue rQueue;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        preferenceHelper = new PreferenceHelper(this);

        etnik = findViewById(R.id.etnik);
        etnama = findViewById(R.id.etnama);
        etusername = findViewById(R.id.etusername);
        etjeniskelamin = findViewById(R.id.etjeniskelamin);
        etemail = findViewById(R.id.etemail);
        etalamat = findViewById(R.id.etalamat);
        etnohp = findViewById(R.id.etnohp);
        etpendidikan = findViewById(R.id.etpendidikan);
        etpassword = findViewById(R.id.etpassword);
        etdate = findViewById(R.id.etdate);

        etnik.setFocusable(false);
        etnik.setClickable(false);
        etusername.setFocusable(false);
        etusername.setClickable(false);
        etjeniskelamin.setFocusable(false);
        etjeniskelamin.setClickable(false);
        etdate.setFocusable(false);
        etdate.setClickable(false);

        ic_back = findViewById(R.id.ic_back);
        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        img_save = findViewById(R.id.img_save);
        img_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = SharedPrefManager.getInstance(getApplicationContext()).getData(Konfigurasi.KEY_EMP_ID);
                saveData(id);
            }
        });

        loadData();

    }

    private void saveData(String id) {

        final String nama = this.etnama.getText().toString().trim();
        final String email = this.etemail.getText().toString().trim();
        final String alamat = this.etalamat.getText().toString().trim();
        final String pendidikan = this.etpendidikan.getText().toString().trim();
        final String nohp = this.etnohp.getText().toString().trim();
        final String password = this.etpassword.getText().toString().trim();

        Log.e("nama", nama);

        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(getApplicationContext(), "Masukan Nama !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.matches(emailPattern)) {
            Toast.makeText(getApplicationContext(), "Email tidak valid!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(alamat)) {
            Toast.makeText(getApplicationContext(), "Masukan Alamat !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pendidikan)) {
            Toast.makeText(getApplicationContext(), "Masukan Pendidikan !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nohp)) {
            Toast.makeText(getApplicationContext(), "Masukan Nomor Handphone !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Masukan Password !!!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_UPDATE_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respon", response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            if (status.equals("true")) {
                                getNewData(id);
                            } else {
                                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditProfileActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(Konfigurasi.KEY_EMP_ID,id);
                params.put("nama", nama);
                params.put(Konfigurasi.KEY_EMP_EMAIL,email);
                params.put(Konfigurasi.KEY_EMP_ALAMAT,alamat);
                params.put(Konfigurasi.KEY_EMP_NOHP,nohp);
                params.put(Konfigurasi.KEY_EMP_PENDIDIKAN,pendidikan);
                params.put(Konfigurasi.KEY_EMP_PASSWORD,password);

                return params;
            }

        };

        rQueue = Volley.newRequestQueue(EditProfileActivity.this);
        rQueue.add(stringRequest);

    }

    private void getNewData(String id) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_READ_DETAIL, response -> {
            Log.e("log", response);
            try {
                    JSONArray dataArray = new JSONArray(response);
                    for (int i = 0; i < dataArray.length(); i++) {

                        JSONObject dataobj = dataArray.getJSONObject(i);
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_ID,dataobj.getString("id"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_NAMA,dataobj.getString("nama"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_NIK,dataobj.getString("nik"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_USERNAME,dataobj.getString("username"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_JENISKELAMIN,dataobj.getString("jeniskelamin"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_EMAIL,dataobj.getString("email"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_ALAMAT,dataobj.getString("alamat"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_DATE,dataobj.getString("date"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_NOHP,dataobj.getString("nohp"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_PENDIDIKAN,dataobj.getString("pendidikan"));
                        SharedPrefManager.getInstance(this).saveData(Konfigurasi.KEY_EMP_PASSWORD,dataobj.getString("password"));
                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"Update Succesfully!",Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        },error -> {
            Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put(Konfigurasi.KEY_EMP_ID,id);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void loadData() {
        String nik = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_NIK);
        String nama = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_NAMA);
        String username = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_USERNAME);
        String jeniskelamin = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_JENISKELAMIN);
        String email = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_EMAIL);
        String date = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_DATE);
        String alamat = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_ALAMAT);
        String nohp = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_NOHP);
        String pendidikan = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_PENDIDIKAN);
        String password = SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_PASSWORD);

        etnik.setText(nik);
        etnama.setText(nama);
        etusername.setText(username);
        etjeniskelamin.setText(jeniskelamin);
        etemail.setText(email);
        etdate.setText(date);
        etalamat.setText(alamat);
        etnohp.setText(nohp);
        etpendidikan.setText(pendidikan);
        etpassword.setText(password);

    }

}
