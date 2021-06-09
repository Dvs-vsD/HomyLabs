package com.example.homylabs.Patient;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.Adapter.DialogRecyclerViewAdapter;
import com.example.homylabs.Adapter.SelectedTestsRecyclerAdapter;
import com.example.homylabs.R;
import com.example.homylabs.userModal.BookingModel;
import com.example.homylabs.userModal.UserDataModel;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TestBookingActivity extends AppCompatActivity implements SelectedTestsRecyclerAdapter.getTotalPrice {

    private TextView tvLabName, tvLabAddress, tvLabOwnerName, tvLabOwnerEmail, tv_add_more_tests;
    private RecyclerView recyclerViewBooking;
    private SelectedTestsRecyclerAdapter adapter;
    private MaterialButton btn_BookNow;
    private DatabaseReference ref;
    private String technicianEmail, ownerUserId, userId;
    private Toolbar toolbar;
    private List<BookingModel> list;
    private List<String> list_test_category;
    private List<UserDataModel> finalList;
    private DialogRecyclerViewAdapter selectTestAdapter;
    private int i = 0;
    private int total = 0;
    private TextView tvTotalAmount;

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_booking);

        tvLabName = findViewById(R.id.tv_lab_name);
        tvLabAddress = findViewById(R.id.tv_lab_address);
        tvLabOwnerName = findViewById(R.id.tvLabOwnerName);
        tvLabOwnerEmail = findViewById(R.id.tvLabOwnerEmail);
        tv_add_more_tests = findViewById(R.id.tv_add_more_tests);
        recyclerViewBooking = findViewById(R.id.recyclerViewBooking);
        btn_BookNow = findViewById(R.id.btn_BookNow);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);

        list_test_category = new ArrayList<>();
        list_test_category.add("Diabetes_related_tests");
        list_test_category.add("fever_related_tests");
        list_test_category.add("skin_related_tests");
        list_test_category.add("kidney_related_tests");
        list_test_category.add("digestion_related_tests");
        list_test_category.add("bone_related_tests");


        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("You Are Just One Step Away");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        ownerUserId = getIntent().getStringExtra("ownerId");
        String labName = getIntent().getStringExtra("labName");
        String labAddress = getIntent().getStringExtra("labAddress");
        String technicianName = getIntent().getStringExtra("userName");
        String testName = getIntent().getStringExtra("testName");
        String testPrice = getIntent().getStringExtra("testPrice");
        technicianEmail = getIntent().getStringExtra("email");

        tvLabName.setText(labName);
        tvLabAddress.setText(labAddress);
        tvLabOwnerName.setText(technicianName);
        tvLabOwnerEmail.setText(technicianEmail);


        recyclerViewBooking.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        ref = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference reference = ref.child("Test_Booked_By_Patient").child(userId);

        list = new ArrayList<>();
        finalList = new ArrayList<>();

//        DatabaseReference refCheckEmail = ref.child("UserLabOwner");
//        refCheckEmail.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    OwnerIdFatchingModal ownerIdFatchingModal = snapshot.getValue(OwnerIdFatchingModal.class);
//
//                    String email = ownerIdFatchingModal.getEmail();
//                    if (email != null) {
//                        if (email.equals(technicianEmail)) {
//                            ownerUserId = snapshot.getKey();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//            }
//        });

        list.add(new BookingModel(testName, testPrice));

        adapter = new SelectedTestsRecyclerAdapter(list, this, this);
        recyclerViewBooking.setAdapter(adapter);

        for (String num : list_test_category) {
            ref.child("UserLabOwner").child(ownerUserId).child(num).addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                List<UserDataModel> listTestData = new ArrayList<>();
                                for (DataSnapshot data : snapshot.getChildren()) {
                                    String testName = data.getKey();
                                    String testPrice = data.getValue().toString();
                                    Log.e("test", "onDataChange:" + testName + " " + testPrice);
                                    listTestData.add(new UserDataModel(testName, testPrice));
                                }
                                UserDataModel model = new UserDataModel(num);
                                model.setTestData(listTestData);
                                finalList.add(model);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("error", "onCancelled:" + error.getMessage());
                        }
                    });
        }

//        DatabaseReference bookedTestRef = ref.child("UserPatient").child(userId).child("my_bookings").push().child("data");

        btn_BookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        bookedTestRef.child("labName").setValue(labName);
//                        bookedTestRef.child("labAddress").setValue(labAddress);
//                        bookedTestRef.child("technicianName").setValue(technicianName);
//                        bookedTestRef.child("testName").setValue(snapshot.getValue());
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

                DatabaseReference referenceBookedTestData = ref.child("UserLabOwner").child(ownerUserId).child("BookedTests").child(userId);

                ref.child("UserPatient").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        referenceBookedTestData.child("patientName").setValue(snapshot.child("userName").getValue());
                        referenceBookedTestData.child("patientEmail").setValue(snapshot.child("email").getValue().toString());
                        referenceBookedTestData.child("testDetails").child("testName").setValue(list.get(0).getTestName());
                        Toast.makeText(TestBookingActivity.this, "Booked Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TestBookingActivity.this, MyBookingsActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


//                reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                        referenceBookedTestData.child("testDetails").push().setValue(snapshot.getValue(), new DatabaseReference.CompletionListener() {
//                            @Override
//                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
////                                boolean testSelected = snapshot.exists();
//                                if (error == null) {
//                                    Toast.makeText(TestBookingActivity.this, "Booked Successfully", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(TestBookingActivity.this, MyBookingsActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else {
//                                    Toast.makeText(TestBookingActivity.this, "Please select the test", Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(TestBookingActivity.this, "Booking failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });

        tv_add_more_tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogSelectMore();
            }
        });
    }

    private void dialogSelectMore() {
        Dialog dialog = new Dialog(this, R.style.Theme_Dialog);
        Window window = dialog.getWindow();
        window.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dialog.setContentView(R.layout.dialog_select_more);

        MaterialButton btnConfirm = dialog.findViewById(R.id.btnConfirm);
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        selectTestAdapter = new DialogRecyclerViewAdapter(finalList, this, list);
        recyclerView.setAdapter(selectTestAdapter);

        btnConfirm.setOnClickListener(v -> {
                    totalAmount();
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
        );

        dialog.show();
    }

    @Override
    public void totalAmount() {
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += Integer.parseInt(list.get(i).getTestPrice());
        }
        tvTotalAmount.setText("Total: ₹ " + total);
    }
}