package com.example.homylabs.general;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.homylabs.LabAdmin.MainActivityOfLabAdmin;
import com.example.homylabs.Patient.MainActivity;
import com.example.homylabs.R;
import com.example.homylabs.SharedPreference.SharedPref;
import com.example.homylabs.utils.ProgressUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class SignIn extends AppCompatActivity {

    private TextView tvSignUp, goBack;
    private MaterialButton btn_signin;
    private EditText etEmail;
    private EditText etPassword;
    private FirebaseAuth auth;
    private FirebaseDatabase database;
    //    private ProgressDialog progressDialog;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        tvSignUp = findViewById(R.id.tvSignUp);
        btn_signin = findViewById(R.id.btnSignIn);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(view -> finish());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Login");
//        progressDialog.setMessage("we are logging in into your account");

        ProgressUtils progressUtils = new ProgressUtils();

        sharedPref = new SharedPref(SignIn.this);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressUtils.showProgressDialog(SignIn.this);
                auth.signInWithEmailAndPassword(etEmail.getText().toString(), etPassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userId = Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid();
                                    sharedPref.setUserId(userId);
                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("UserLabOwner");
                                    DatabaseReference check_Existence = reference.child(userId);

                                    check_Existence.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            if (dataSnapshot.exists()) {
                                                progressUtils.dismissProgressDialog();
                                                sharedPref.setUserType("LabOwner");
                                                Intent intent = new Intent(SignIn.this, MainActivityOfLabAdmin.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                progressUtils.dismissProgressDialog();
                                                sharedPref.setUserType("Patient");
                                                Intent intent = new Intent(SignIn.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            progressUtils.dismissProgressDialog();
                                            Log.e("error====", error.getMessage());
                                        }
                                    });
                                } else {
                                    progressUtils.dismissProgressDialog();
                                    Toast.makeText(SignIn.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignIn.this, SignUp.class);
                startActivity(i);
                finish();
            }
        });

    }
}