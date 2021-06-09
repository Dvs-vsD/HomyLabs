package com.example.homylabs.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.homylabs.R;
import com.example.homylabs.SharedPreference.SharedPref;
import com.example.homylabs.general.SignIn;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private CardView cardDiabetes, cardFever, cardSkin, cardKidney, cardDigestion, cardBone;

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView nav;
    private FirebaseAuth auth;
    private TextView tv_userName;
    private View headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardDiabetes = findViewById(R.id.card_diabetes);
        cardFever = findViewById(R.id.card_fever);
        cardSkin = findViewById(R.id.card_skin);
        cardKidney = findViewById(R.id.card_kidney);
        cardDigestion = findViewById(R.id.card_digestion);
        cardBone = findViewById(R.id.card_bone);

        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);

        headerView = nav.getHeaderView(0);
        tv_userName = headerView.findViewById(R.id.tv_userName);
        auth = FirebaseAuth.getInstance();

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

//        SharedPreferences sharedPref = getSharedPreferences("HomyLabs", 0);
//        SharedPreferences.Editor editor = sharedPref.edit();

        SharedPref shPref = new SharedPref(this);

        if (shPref.getPatientUsername() != null) {
            tv_userName.setText(shPref.getPatientUsername());
        }

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.menuBooking:
                        Intent intentBooking = new Intent(MainActivity.this, MyBookingsActivity.class);
                        startActivity(intentBooking);
                        break;
                    case R.id.menuReport:
                        Intent intentReport = new Intent(MainActivity.this, ReportDownloadingActivity.class);
                        startActivity(intentReport);
                        break;
                    case R.id.menuLogout:
                        auth.signOut();
                        Intent intentLogout = new Intent(MainActivity.this, SignIn.class);
                        startActivity(intentLogout);
                        finish();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        cardDiabetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DiabetesRelatedTests.class);
                startActivity(i);
            }
        });

        cardFever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FeverRelatedTests.class);
                startActivity(i);
            }
        });

        cardSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SkinRelatedTests.class);
                startActivity(i);
            }
        });

        cardKidney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, KidneyUrineTests.class);
                startActivity(i);
            }
        });

        cardDigestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DigestionRelatedTests.class);
                startActivity(i);
            }
        });

        cardBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BoneRelatedTests.class);
                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}