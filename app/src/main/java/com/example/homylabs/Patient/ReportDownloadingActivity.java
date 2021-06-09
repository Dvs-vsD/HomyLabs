package com.example.homylabs.Patient;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.Adapter.ReportDownloadAdapter;
import com.example.homylabs.R;
import com.example.homylabs.userModal.ReportDownloadDataModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReportDownloadingActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recViewReportDownload;
    private FirebaseStorage storage;
    //    private FirebaseDatabase database;
    private DatabaseReference reference;
    private List<ReportDownloadDataModel> reportNameList = new ArrayList<>();
    private ReportDownloadAdapter adapter;
    private TextView tvDefaultStatement;
    private ProgressBar progressBar;
    private ImageView no_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_downloading);

        toolbar = findViewById(R.id.toolbar);
        recViewReportDownload = findViewById(R.id.recViewReportDownload);
        tvDefaultStatement = findViewById(R.id.tvDefaultStatement);
        progressBar = findViewById(R.id.progressBar);
        no_result = findViewById(R.id.no_result);

        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setTitle("Download Your Report");
        toolbar.setNavigationOnClickListener(view -> finish());

        reference = FirebaseDatabase.getInstance().getReference().child("reportUploadedByLabOwner");

        String userPatientId = FirebaseAuth.getInstance().getUid();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    assert userPatientId != null;
                    if (dataSnapshot.child(userPatientId).exists()) {
                        for (DataSnapshot innerSnapshot : dataSnapshot.getChildren()) {
                            if (Objects.equals(innerSnapshot.getKey(), userPatientId)) {
                                for (DataSnapshot underInnerSnapshot : innerSnapshot.getChildren()) {
                                    String reportName = Objects.requireNonNull(underInnerSnapshot.child("name").getValue()).toString();
                                    String reportUrl = Objects.requireNonNull(underInnerSnapshot.child("url").getValue()).toString();
                                    reportNameList.add(new ReportDownloadDataModel(reportName, reportUrl));
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    } else {
                        Log.e("====Else====", "executed");
                        progressBar.setVisibility(View.GONE);
                        no_result.setVisibility(View.VISIBLE);
                        tvDefaultStatement.setVisibility(View.VISIBLE);
                    }
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressBar.setVisibility(View.GONE);
                Log.e("=======error====", error.getMessage());
            }
        });
        adapter = new ReportDownloadAdapter(reportNameList, ReportDownloadingActivity.this);
        recViewReportDownload.setLayoutManager(new LinearLayoutManager(this));
        recViewReportDownload.setAdapter(adapter);
    }
}