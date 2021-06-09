package com.example.homylabs.general;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.homylabs.LabAdmin.MainActivityOfLabAdmin;
import com.example.homylabs.Patient.MainActivity;
import com.example.homylabs.R;
import com.example.homylabs.SharedPreference.SharedPref;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPref = new SharedPref(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {

                    if (sharedPref.getUserType().equals("LabOwner")) {
                        Intent intent = new Intent(SplashActivity.this, MainActivityOfLabAdmin.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Intent intent = new Intent(SplashActivity.this, SignIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);

    }
}