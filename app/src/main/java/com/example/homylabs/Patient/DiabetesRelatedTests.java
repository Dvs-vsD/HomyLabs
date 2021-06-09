package com.example.homylabs.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.example.homylabs.R;

public class DiabetesRelatedTests extends AppCompatActivity {

    private CardView hbA1c;
    private CardView ins;
    private CardView fbs;
    private CardView lp;
    private CardView rbs;
    private CardView c_peptide;
    private CardView thyroid;
    private CardView ppbs;
    private CardView lft;
    private CardView cbc;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diabetes_related_tests);

        hbA1c = findViewById(R.id.HbA1c);
        ins = findViewById(R.id.ins);
        fbs = findViewById(R.id.fbs);
        lp = findViewById(R.id.lp);
        rbs = findViewById(R.id.rbs);
        c_peptide = findViewById(R.id.c_peptide);
        thyroid = findViewById(R.id.thyroid);
        ppbs = findViewById(R.id.ppbs);
        lft = findViewById(R.id.lft);
        cbc = findViewById(R.id.cbc);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Diabetes Related Tests");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        Intent i = new Intent(DiabetesRelatedTests.this, TestDescription.class);
        i.putExtra("test_category","Diabetes_related_tests");

        hbA1c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.hba1c);
                String test_data = getString(R.string.HbA1c_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        ins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ins);
                String test_data = getString(R.string.ins_data);
                String test_prep = getString(R.string.gen_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        fbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.fbs);
                String test_data = getString(R.string.fbs_data);
                String test_prep = getString(R.string.gen_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        lp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.lp);
                String test_data = getString(R.string.lp_data);
                String test_prep = getString(R.string.lp_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        rbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.rbs);
                String test_data = getString(R.string.rbs_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        c_peptide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.c_peptide);
                String test_data = getString(R.string.c_peptide_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        thyroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.thyroid);
                String test_data = getString(R.string.thyroid_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        ppbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ppbs);
                String test_data = getString(R.string.ppbs_data);
                String test_prep = getString(R.string.ppbs_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        lft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.lft);
                String test_data = getString(R.string.lft_data);
                String test_prep = getString(R.string.lft_prep);
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