package com.example.ptsuksesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ptsuksesapp.API.Konfigurasi;
import com.example.ptsuksesapp.API.SharedPrefManager;
import com.example.ptsuksesapp.PreferenceHelper;
import com.example.ptsuksesapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView username;
    private LinearLayout editProfile, datapegawai, img_logout;

    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferenceHelper = new PreferenceHelper(this);

        username = findViewById(R.id.username);
        editProfile = findViewById(R.id.editProfile);
        img_logout = findViewById(R.id.img_logout);
        datapegawai = findViewById(R.id.datapegawai);

        username.setText(SharedPrefManager.getInstance(this).getData(Konfigurasi.KEY_EMP_USERNAME));

        editProfile.setOnClickListener(this);
        datapegawai.setOnClickListener(this);
        img_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == editProfile){

            Intent intent = new Intent(this,EditProfileActivity.class);
            intent.putExtra(Konfigurasi.KEY_EMP_ID, preferenceHelper.getID());
            startActivity(intent);
        }
        if(v == datapegawai){
            startActivity(new Intent(this,DataPegawaiActivity.class));
        }
        if(v == img_logout){
            preferenceHelper.putIsLogin(false);
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            MainActivity.this.finish();
        }
        }


//    private void parseData(String response){
//        try {
//            JSONObject jsonObject = new JSONObject(response);
//            if (editProfile.hasOnClickListeners()) {
//
//                saveInfo(response);
//
//                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this,EditProfileActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                this.finish();
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }
//
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
//                    preferenceHelper.putNIK(dataobj.getString("nik"));
//                    preferenceHelper.putEmail(dataobj.getString("email"));
//                    preferenceHelper.putJeniskelamin(dataobj.getString("jeniskelamin"));
//                    preferenceHelper.putAlamat(dataobj.getString("alamat"));
//                    preferenceHelper.putDate(dataobj.getString("date"));
//                    preferenceHelper.putNohp(dataobj.getString("nohp"));
//                    preferenceHelper.putPassword(dataobj.getString("password"));
//                    preferenceHelper.putPendidikan(dataobj.getString("pendidikan"));
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
        }



