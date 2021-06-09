package com.example.homylabs.Patient;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.Adapter.NearestLabRecyclerAdapter;
import com.example.homylabs.R;
import com.example.homylabs.userModal.UserDataModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NearestLaboratoryActivity extends AppCompatActivity {

    private RecyclerView recView;
    private NearestLabRecyclerAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Toolbar toolbar;
    private List<UserDataModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest_laboratory);

        recView = findViewById(R.id.recView);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Nearest Laboratories");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        recView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        String test_category = getIntent().getStringExtra("test_category");
        String testName = getIntent().getStringExtra("testName");

        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();
        reference = database.getReference();

        reference.child("UserLabOwner").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.e("===lab=owner=key==my==", dataSnapshot.getKey());
                    String ownerId = dataSnapshot.getKey();
                    if (dataSnapshot.child(test_category).child(testName).exists()) {
                        String testPrice = dataSnapshot.child(test_category).child(testName).getValue().toString();
                        String labOwnerName = dataSnapshot.child("userName").getValue().toString();
                        String labName = dataSnapshot.child("labName").getValue().toString();
                        String labAddress = dataSnapshot.child("labAddress").getValue().toString();
                        String labOwnerEmail = dataSnapshot.child("email").getValue().toString();
                        Log.e("====price====", testPrice + "      " + labAddress + "     " + labName + "    " + labOwnerEmail + "   " + labOwnerName);
                        list.add(new UserDataModel(ownerId,labName,labAddress,labOwnerName,labOwnerEmail,testName,testPrice));
                    } else {

                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("====error====", error.getMessage());
            }
        });

        adapter = new NearestLabRecyclerAdapter(this, list);
        recView.setAdapter(adapter);
    }
}