package com.example.ptsuksesapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptsuksesapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    Animation ttb, btt;
    ImageView img_splash;
    private int waktu_loading = 2000;
    TextView app_subtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // load animation
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        //load element
        img_splash = findViewById(R.id.img_splash);
        app_subtitle = findViewById(R.id.app_subtitle);

        //run animation
        img_splash.startAnimation(ttb);
        app_subtitle.startAnimation(btt);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(home);
                finish();
                overridePendingTransition(0, 0);
                getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            }
        }, waktu_loading);
    }
}
