package com.example.homylabs.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.Adapter.MyBookingsDataAdapter;
import com.example.homylabs.R;
import com.example.homylabs.userModal.BookingModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyBookingsActivity extends AppCompatActivity {

    private RecyclerView recViewBooking;
    private DatabaseReference reference;
    private Toolbar toolbar;
    private List<BookingModel> bookingDetailsList;
    private MyBookingsDataAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bookings);

        recViewBooking = findViewById(R.id.recViewBooking);
        progressBar = findViewById(R.id.progressBar);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("My Bookings");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        bookingDetailsList = new ArrayList<>();

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("UserPatient").child(userId).child("my_bookings");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String labAddress = dataSnapshot.child("data").child("labAddress").getValue().toString();
                    String labName = dataSnapshot.child("data").child("labName").getValue().toString();
                    String technicianName = dataSnapshot.child("data").child("technicianName").getValue().toString();
                    List<BookingModel> list = new ArrayList<>();
                    BookingModel model = new BookingModel(labName, labAddress, technicianName);
                    DataSnapshot test = dataSnapshot.child("data").child("testName");
                    for (DataSnapshot testNameSnap : test.getChildren()) {
                        String testName = testNameSnap.child("testName").getValue().toString();
                        list.add(new BookingModel(testName));
                    }
                    model.setTestNameList(list);
                    bookingDetailsList.add(model);
                }
                progressBar.setVisibility(View.GONE);
                adapter = new MyBookingsDataAdapter(bookingDetailsList, MyBookingsActivity.this);
                recViewBooking.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recViewBooking.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MyBookingsActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}