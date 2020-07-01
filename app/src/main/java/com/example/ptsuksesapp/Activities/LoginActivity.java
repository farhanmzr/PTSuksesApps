package com.example.ptsuksesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ptsuksesapp.API.Konfigurasi;
import com.example.ptsuksesapp.API.SharedPrefManager;
import com.example.ptsuksesapp.PreferenceHelper;
import com.example.ptsuksesapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;

    private TextView tvRegister;
    private Button btn_login;

    private PreferenceHelper preferenceHelper;
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferenceHelper = new PreferenceHelper(this);

        if(preferenceHelper.getIsLogin()){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        tvRegister = findViewById(R.id.tvRegister);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser(){

        final String xusername = username.getText().toString().trim();
        final String xpassword = password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        rQueue.getCache().clear();
                        Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        parseData(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",xusername);
                params.put("password",xpassword);

                return params;
            }

        };

        rQueue = Volley.newRequestQueue(LoginActivity.this);
        rQueue.add(stringRequest);
    }

    private void parseData(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {

                saveInfo(response);

                Toast.makeText(LoginActivity.this, "Selamat datang di PT Sukses 2020", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                this.finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void saveInfo(String response){
        Log.e("response",response);
        preferenceHelper.putIsLogin(true);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                JSONArray dataArray = jsonObject.getJSONArray("data");
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
