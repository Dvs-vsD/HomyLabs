package com.example.homylabs.general;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.homylabs.LabAdmin.LabAdminCheckMarkTests;
import com.example.homylabs.Patient.MainActivity;
import com.example.homylabs.R;
import com.example.homylabs.SharedPreference.SharedPref;
import com.example.homylabs.userModal.UserDataModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    private TextView tvSignIn;
    private Button btnSignUp;
    private TextInputEditText etUser;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextInputEditText etLabName;
    private TextInputEditText etLabAddress;
    private TextInputLayout tilLabName;
    private TextInputLayout tilLabAddress;
    private Chip chipPatient;
    private Chip chipLabOwner;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ProgressDialog progressDialog;
    private SharedPref sharedPref;
    private ChipGroup chip_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        tvSignIn = findViewById(R.id.tvSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        etUser = findViewById(R.id.etUserName);
        etEmail = findViewById(R.id.etMail);
        etPassword = findViewById(R.id.etPassword);
        etLabName = findViewById(R.id.etLabName);
        etLabAddress = findViewById(R.id.etLabAddress);
        tilLabName = findViewById(R.id.tilLabName);
        tilLabAddress = findViewById(R.id.tilLabAddress);

        chip_group = findViewById(R.id.chip_group);
        chipPatient = findViewById(R.id.chipPatient);
        chipLabOwner = findViewById(R.id.chipLabOwner);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        sharedPref = new SharedPref(SignUp.this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("we are creating your account");

        chipLabOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilLabName.setVisibility(View.VISIBLE);
                tilLabAddress.setVisibility(View.VISIBLE);
            }
        });

        chipPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tilLabName.setVisibility(View.GONE);
                tilLabAddress.setVisibility(View.GONE);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (chip_group.getCheckedChipId() == R.id.chipPatient) {
//                    if (etUser.getText().toString().trim().equals("") || etEmail.getText().toString().trim().equals("") ||
//                            etPassword.getText().toString().trim().equals("")) {
//                        if (etUser.getText().toString().trim().equals("")) {
//                            etUser.setError("Please Enter Username");
//                            etUser.requestFocus();
//                        } else if (etEmail.getText().toString().trim().equals("")) {
//                            etEmail.setError("Please Enter Email");
//                            etEmail.requestFocus();
//                        } else {
//                            etPassword.setError("Please Enter Username");
//                            etPassword.requestFocus();
//                        }
//                    }
//                } else {

                    progressDialog.show();
                    auth.createUserWithEmailAndPassword(Objects.requireNonNull(etEmail.getText()).toString(), Objects.requireNonNull(etPassword.getText()).toString()).
                            addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {
                                        UserDataModel userPatient = new UserDataModel(Objects.requireNonNull(etUser.getText()).toString(),
                                                etEmail.getText().toString(), etPassword.getText().toString());
                                        UserDataModel userLabOwner = new UserDataModel(etUser.getText().toString(),
                                                etEmail.getText().toString(), etPassword.getText().toString(),
                                                Objects.requireNonNull(etLabName.getText()).toString(), Objects.requireNonNull(etLabAddress.getText()).toString());

                                        String userId = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid();
                                        sharedPref.setUserId(userId);
                                        if (chipPatient.isChecked()) {
                                            sharedPref.setPatientUsername(etUser.getText().toString());
                                            reference.child("UserPatient").child(userId).setValue(userPatient);
                                            Intent i = new Intent(SignUp.this, MainActivity.class);
                                            startActivity(i);
                                            finish();
                                        } else if (chipLabOwner.isChecked()) {
                                            sharedPref.setUserType("LabOwner");
                                            sharedPref.setLabOwnerUserName(etUser.getText().toString());
                                            reference.child("UserLabOwner").child(userId).setValue(userLabOwner);
                                            Intent i = new Intent(SignUp.this, LabAdminCheckMarkTests.class);
                                            i.putExtra("ownerName", etUser.getText().toString());
                                            startActivity(i);
                                            finish();
                                        }

                                        Toast.makeText(SignUp.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignUp.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
//            }
        });


        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, SignIn.class);
                startActivity(i);
                finish();
            }
        });
    }
}
