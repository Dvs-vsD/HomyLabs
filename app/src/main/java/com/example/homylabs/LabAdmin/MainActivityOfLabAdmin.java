package com.example.homylabs.LabAdmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.Adapter.LabAdminMainPageRecyclerVIewAdapter;
import com.example.homylabs.R;
import com.example.homylabs.SharedPreference.SharedPref;
import com.example.homylabs.general.SignIn;
import com.example.homylabs.userModal.BookedTestPatientModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivityOfLabAdmin extends AppCompatActivity {

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationView nav;
    private TextView tvOwnerName;
    private TextView tvUserName;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private View headerView;
    private TextView tvDefaultStatement;
    private RecyclerView recViewLabAdmin;
    private LabAdminMainPageRecyclerVIewAdapter adapter;
    private DatabaseReference reference;
    private List<BookedTestPatientModel> patientList;
    private String patientName, patientEmail, patientId;
    private ProgressBar progressBar;
    private String userId = "";

//    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_of_lab_admin);

        drawer = findViewById(R.id.drawer);
        nav = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        tvOwnerName = findViewById(R.id.tvOwnerName);
        tvDefaultStatement = findViewById(R.id.tvDefaultStatement);
        recViewLabAdmin = findViewById(R.id.recViewLabAdmin);
        progressBar = findViewById(R.id.progressBar);

        headerView = nav.getHeaderView(0);
        tvUserName = headerView.findViewById(R.id.tv_userName);

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        reference = database.getReference();

        SharedPref sharedPref = new SharedPref(this);
        if (sharedPref.getLabOwnerUserName() != null) {
            String labOwnerName = sharedPref.getLabOwnerUserName();
            tvOwnerName.setText(labOwnerName);
            tvUserName.setText(labOwnerName);

        }

//        SharedPreferences shPref = getSharedPreferences("HomyLabs", 0);
//        SharedPreferences.Editor editor = shPref.edit();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.logout:
                        auth.signOut();
//                        editor.clear();
//                        editor.apply();
                        Intent in = new Intent(MainActivityOfLabAdmin.this, SignIn.class);
                        startActivity(in);
                        finish();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        patientList = new ArrayList<>();
        DatabaseReference databaseReference = reference.child("UserLabOwner").child(userId).child("BookedTests");
        progressBar.setVisibility(View.VISIBLE);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        patientId = dataSnapshot.getKey();
                        Log.e("=======tag=====", patientId);
                        patientName = Objects.requireNonNull(dataSnapshot.child("patientName").getValue()).toString();
                        patientEmail = Objects.requireNonNull(dataSnapshot.child("patientEmail").getValue()).toString();
                        BookedTestPatientModel model = new BookedTestPatientModel(patientName, patientEmail, patientId);
                        List<BookedTestPatientModel> list = new ArrayList<>();
                        for (DataSnapshot snapshot1 : dataSnapshot.child("testDetails").getChildren()) {
                            for (DataSnapshot snapshot2 : snapshot1.getChildren()) {
                                String testName = Objects.requireNonNull(snapshot2.child("testName").getValue()).toString();
                                list.add(new BookedTestPatientModel(testName));
                            }
                        }
                        model.setTestNameList(list);
                        patientList.add(model);
                        adapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.GONE);
                    tvDefaultStatement.setText(R.string.you_haven_t_get_any_test_bookings_yet);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivityOfLabAdmin.this, "task failed", Toast.LENGTH_SHORT).show();
            }
        });

        adapter = new LabAdminMainPageRecyclerVIewAdapter(MainActivityOfLabAdmin.this, patientList);
        recViewLabAdmin.setLayoutManager(new LinearLayoutManager(MainActivityOfLabAdmin.this));
        recViewLabAdmin.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void recreate() {
        super.recreate();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                this.recreate();
            }
        }
    }
}