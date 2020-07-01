package com.example.ptsuksesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ptsuksesapp.API.Konfigurasi;
import com.example.ptsuksesapp.PreferenceHelper;
import com.example.ptsuksesapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private TextView nik, nama, username, email, alamat, pendidikan, nohp, password, konfirmasi_password;

    private RadioGroup mGender;
    private RadioButton mGenderOption, rbPria, rbPerempuan;
    private TextView tvDate, tvLogin;
    private Button btn_register;

    String jeniskelamin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private PreferenceHelper preferenceHelper;
    private RequestQueue rQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        preferenceHelper = new PreferenceHelper(this);


        nik = (EditText) findViewById(R.id.nik);
        nama = (EditText) findViewById(R.id.nama);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        alamat = (EditText) findViewById(R.id.alamat);
        pendidikan = (EditText) findViewById(R.id.pendidikan);
        nohp = (EditText) findViewById(R.id.nohp);
        password = (EditText) findViewById(R.id.password);
        konfirmasi_password = (EditText) findViewById(R.id.konfirmasi_password);
        rbPria = (RadioButton) findViewById(R.id.rbPria);
        rbPerempuan = (RadioButton) findViewById(R.id.rbPerempuan);
        konfirmasi_password = (EditText) findViewById(R.id.konfirmasi_password);
        tvDate = findViewById(R.id.tvDate);
        tvLogin = findViewById(R.id.tvLogin);
        btn_register = findViewById(R.id.btn_register);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        tvDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        mGender = (RadioGroup) findViewById(R.id.radio);

        mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mGenderOption = mGender.findViewById(checkedId);

                switch (checkedId) {
                    case R.id.rbPria:
                        jeniskelamin = mGenderOption.getText().toString();
                        break;
                    case R.id.rbPerempuan:
                        jeniskelamin = mGenderOption.getText().toString();
                        break;

                    default:
                }
            }
        });

        intent();

    }

    private void intent() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();

            }
        });
    }

    private void Register() {

        final String nik = this.nik.getText().toString().trim();
        final String nama = this.nama.getText().toString().trim();
        final String username = this.username.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String alamat = this.alamat.getText().toString().trim();
        final String pendidikan = this.pendidikan.getText().toString().trim();
        final String nohp = this.nohp.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String konfirmasi_password= this.konfirmasi_password.getText().toString().trim();
        final String date = this.tvDate.getText().toString().trim();
        final String jeniskelamin = this.mGenderOption.getText().toString().trim();

        if (TextUtils.isEmpty(nik)) {
            Toast.makeText(getApplicationContext(), "Masukan NIK !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(getApplicationContext(), "Masukan Nama !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getApplicationContext(), "Masukan Username !!!", Toast.LENGTH_SHORT).show();
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
        if (!password.equals(konfirmasi_password)) {
            Toast.makeText(getApplicationContext(), "Password tidak sama!!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(getApplicationContext(), "Masukan Tanggal Lahir !!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(jeniskelamin)) {
            Toast.makeText(getApplicationContext(), "Masukan Jenis Kelamin !!!", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Konfigurasi.URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        rQueue.getCache().clear();
                        Toast.makeText(RegisterActivity.this,response,Toast.LENGTH_LONG).show();

                        try {

                            parseData(response);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                    params.put(Konfigurasi.KEY_EMP_NIK,nik);
                params.put(Konfigurasi.KEY_EMP_NAMA,nama);
                params.put(Konfigurasi.KEY_EMP_USERNAME,username);
                params.put(Konfigurasi.KEY_EMP_EMAIL,email);
                params.put(Konfigurasi.KEY_EMP_ALAMAT,alamat);
                params.put(Konfigurasi.KEY_EMP_DATE,date);
                params.put(Konfigurasi.KEY_EMP_NOHP,nohp);
                params.put(Konfigurasi.KEY_EMP_JENISKELAMIN,jeniskelamin);
                params.put(Konfigurasi.KEY_EMP_PENDIDIKAN,pendidikan);
                params.put(Konfigurasi.KEY_EMP_PASSWORD,password);

                return params;
            }

        };

        rQueue = Volley.newRequestQueue(RegisterActivity.this);
        rQueue.add(stringRequest);
    }

    private void parseData(String response) throws JSONException {

        JSONObject jsonObject = new JSONObject(response);
        if (jsonObject.optString("status").equals("true")){

//            saveInfo(response);

            Toast.makeText(RegisterActivity.this, "Selamat, anda berhasil mendaftarkan akun baru!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            this.finish();
        }else {

            Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
        }
    }

//    private void saveInfo(String response){
//
//        preferenceHelper.putIsLogin(true);
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            if (jsonObject.getString("status").equals("true")) {
//                JSONArray dataArray = jsonObject.getJSONArray("data");
//                for (int i = 0; i < dataArray.length(); i++) {
//
//                    JSONObject dataobj = dataArray.getJSONObject(i);
//                    preferenceHelper.putNama(dataobj.getString("nama"));
//                    preferenceHelper.putUsername(dataobj.getString("username"));
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

//    private void Register() {
//
//        final String nik = this.nik.getText().toString().trim();
//        final String nama = this.nama.getText().toString().trim();
//        final String username = this.username.getText().toString().trim();
//        final String email = this.email.getText().toString().trim();
//        final String alamat = this.alamat.getText().toString().trim();
//        final String pendidikan = this.pendidikan.getText().toString().trim();
//        final String nohp = this.nohp.getText().toString().trim();
//        final String password = this.password.getText().toString().trim();
//        final String konfirmasi_password= this.konfirmasi_password.getText().toString().trim();
//        final String date = this.tvDate.getText().toString().trim();
//        final String jeniskelamin = this.mGenderOption.getText().toString().trim();
//
//
//        if (TextUtils.isEmpty(nik)) {
//            Toast.makeText(getApplicationContext(), "Masukan NIK !!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(nama)) {
//            Toast.makeText(getApplicationContext(), "Masukan Nama !!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(username)) {
//            Toast.makeText(getApplicationContext(), "Masukan Username !!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (!email.matches(emailPattern)) {
//            Toast.makeText(getApplicationContext(), "Email tidak valid!!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(alamat)) {
//            Toast.makeText(getApplicationContext(), "Masukan Alamat !!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(pendidikan)) {
//            Toast.makeText(getApplicationContext(), "Masukan Pendidikan !!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(nohp)) {
//            Toast.makeText(getApplicationContext(), "Masukan Nomor Handphone !!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (!password.equals(konfirmasi_password)) {
//            Toast.makeText(getApplicationContext(), "Password tidak sama!!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(date)) {
//            Toast.makeText(getApplicationContext(), "Masukan Tanggal Lahir !!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(jeniskelamin)) {
//            Toast.makeText(getApplicationContext(), "Masukan Jenis Kelamin !!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        class AddEmployee extends AsyncTask<Void,Void,String> {
//
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(RegisterActivity.this,"Menambahkan...","Tunggu...",false,false);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                Toast.makeText(RegisterActivity.this,"Selamat.. Akun anda berhasil ditambahkan!", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            protected String doInBackground(Void... v) {
//                HashMap<String,String> params = new HashMap<>();
//                params.put(Konfigurasi.KEY_EMP_NIK,nik);
//                params.put(Konfigurasi.KEY_EMP_NAMA,nama);
//                params.put(Konfigurasi.KEY_EMP_USERNAME,username);
//                params.put(Konfigurasi.KEY_EMP_EMAIL,email);
//                params.put(Konfigurasi.KEY_EMP_ALAMAT,alamat);
//                params.put(Konfigurasi.KEY_EMP_DATE,date);
//                params.put(Konfigurasi.KEY_EMP_NOHP,nohp);
//                params.put(Konfigurasi.KEY_EMP_JENISKELAMIN,jeniskelamin);
//                params.put(Konfigurasi.KEY_EMP_PENDIDIKAN,pendidikan);
//                params.put(Konfigurasi.KEY_EMP_PASSWORD,password);
//
//                RequestHandler rh = new RequestHandler();
//                String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
//                return res;
//            }
//        }
//
//        AddEmployee ae = new AddEmployee();
//        ae.execute();
//    }
}
