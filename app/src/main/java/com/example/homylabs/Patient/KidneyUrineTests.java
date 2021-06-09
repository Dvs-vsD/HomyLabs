package com.example.homylabs.Patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.homylabs.R;

public class KidneyUrineTests extends AppCompatActivity {

    private CardView cs;
    private CardView bun;
    private CardView mu;
    private CardView ss;
    private CardView ps;
    private CardView cbc;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kidney_urine_tests);

        cs = findViewById(R.id.cs);
        bun = findViewById(R.id.bun);
        mu = findViewById(R.id.mu);
        ss = findViewById(R.id.ss);
        ps = findViewById(R.id.ps);
        cbc = findViewById(R.id.cbc);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Kidney Related Tests");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        Intent i = new Intent(KidneyUrineTests.this, TestDescription.class);
        i.putExtra("test_category","kidney_related_tests");

        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.cs);
                String test_data = getString(R.string.cs_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        bun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.bun);
                String test_data = getString(R.string.bun_data);
                String test_prep = getString(R.string.gene_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.mu);
                String test_data = getString(R.string.mu_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ss);
                String test_data = getString(R.string.ss_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ps);
                String test_data = getString(R.string.ps_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        cbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.cbc);
                String test_data = getString(R.string.cbc_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });
    }
}