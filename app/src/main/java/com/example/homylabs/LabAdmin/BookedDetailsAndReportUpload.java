package com.example.homylabs.LabAdmin;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.Adapter.LabAdminChildRecyclerViewAdapter;
import com.example.homylabs.R;
import com.example.homylabs.userModal.BookedTestPatientModel;
import com.example.homylabs.userModal.PutPDFModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;
import java.util.Objects;

public class BookedDetailsAndReportUpload extends AppCompatActivity {

    private TextView tvPatientName, tvPatientEmail, tvUrl;
    private MaterialButton btn_upload_report, btn_order_complete, btnSelectFile;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private String patientId;
    private ProgressDialog progressDialog;
    private Uri pdfUri;
    private TextInputEditText etReportName;
    private String reportName;
    private RecyclerView recyclerViewBooking;
    private Toolbar toolbar;
    private String userIdLabOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_tests_and_report_upload);

        tvPatientName = findViewById(R.id.tv_patient_name);
        tvPatientEmail = findViewById(R.id.tv_patient_email);
        tvUrl = findViewById(R.id.tvUrl);
        btn_upload_report = findViewById(R.id.btn_upload_report);
        btnSelectFile = findViewById(R.id.btnSelectFile);
        etReportName = findViewById(R.id.etReportName);
        recyclerViewBooking = findViewById(R.id.recyclerViewBooking);
        btn_order_complete = findViewById(R.id.btn_order_complete);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Patient Order");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        String patient_name = getIntent().getStringExtra("patient_name");
        String patient_email = getIntent().getStringExtra("patient_email");
        patientId = getIntent().getStringExtra("patientId");

        List<BookedTestPatientModel> selected_tests_list = (List<BookedTestPatientModel>) getIntent().getSerializableExtra("SELECTED TEST");

        LabAdminChildRecyclerViewAdapter adapter = new LabAdminChildRecyclerViewAdapter(selected_tests_list);
        recyclerViewBooking.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBooking.setAdapter(adapter);

        tvPatientName.setText(patient_name);
        tvPatientEmail.setText(patient_email);

        btn_upload_report.setEnabled(false);
        btn_upload_report.setBackgroundColor(getResources().getColor(R.color.grey));

        btn_order_complete.setEnabled(false);
        btn_order_complete.setBackgroundColor(getResources().getColor(R.color.grey));

        userIdLabOwner = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnSelectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDF();
            }
        });

        btn_order_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConform();
            }
        });

    }

    private void dialogConform() {
        new AlertDialog.Builder(this)
                .setTitle("Are You Sure you are done ?")
                .setMessage("It will delete entry of Booked test data of patient from your dashboard")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child("UserLabOwner").child(userIdLabOwner).child("BookedTests").child(patientId).removeValue();
                        onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void selectPDF() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            btn_upload_report.setEnabled(true);
            btn_upload_report.setBackgroundColor(getResources().getColor(R.color.yellow));

            assert data != null;
            pdfUri = data.getData();
            tvUrl.setText(pdfUri.toString());

            btn_upload_report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btn_order_complete.setEnabled(true);
                    btn_order_complete.setBackgroundColor(getResources().getColor(R.color.yellow));
                    reportName = etReportName.getText().toString() + " " + System.currentTimeMillis();
                    if (!reportName.equals("")) {
                        progressDialog = new ProgressDialog(BookedDetailsAndReportUpload.this);
                        progressDialog.setTitle("Uploading...");
                        progressDialog.show();
                        uploadPDFFileFirebase(pdfUri);
                    } else {
                        Toast.makeText(BookedDetailsAndReportUpload.this, "Please Enter Report name", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void uploadPDFFileFirebase(Uri data) {
        storageReference = FirebaseStorage.getInstance().getReference().child(reportName + "." + "pdf");
        storageReference.child(reportName + System.currentTimeMillis() + ".pdf");
        storageReference.putFile(data).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                        while (!task.isComplete()) ;
                        Uri uri = task.getResult();
                        assert uri != null;
                        PutPDFModel putPDFModel = new PutPDFModel(reportName, uri.toString());
                        databaseReference.child("reportUploadedByLabOwner").child(userIdLabOwner).child(patientId).push().setValue(putPDFModel);
                        Toast.makeText(BookedDetailsAndReportUpload.this, "Report Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.0 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                progressDialog.setMessage("File Uploading..." + ((int) progress) + "%");
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();
//        super.onBackPressed();
    }
}