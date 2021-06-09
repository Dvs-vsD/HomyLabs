package com.example.homylabs.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.homylabs.R;
import com.google.android.material.button.MaterialButton;

public class TestDescription extends AppCompatActivity {

    private TextView heading;
    private TextView testPrep;
    private TextView test;
    private TextView testAbout;
    private Toolbar toolbar;
    private MaterialButton btnShowNearestLabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_description);

        testPrep = findViewById(R.id.testPrep);
        test = findViewById(R.id.test);
        testAbout = findViewById(R.id.testAbout);
        btnShowNearestLabs = findViewById(R.id.btnShowNearestLabs);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        String test_category = getIntent().getStringExtra("test_category");
        String head = getIntent().getStringExtra("heading");
        String test_data = getIntent().getStringExtra("About");
        String prep = getIntent().getStringExtra("prep");

        toolbar.setTitle(head);
        testPrep.setText(prep);
        test.setText(head);
        testAbout.setText(test_data);

        btnShowNearestLabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestDescription.this,NearestLaboratoryActivity.class);
                intent.putExtra("test_category",test_category);
                intent.putExtra("testName",head);
                intent.putExtra("testData",test_data);
                startActivity(intent);
            }
        });

    }
}