package com.example.homylabs.Patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.homylabs.R;

public class FeverRelatedTests extends AppCompatActivity {

    private CardView cbc;
    private CardView dns1;
    private CardView digg;
    private CardView digm;
    private CardView ma;
    private CardView ta;
    private CardView Cigm;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fever_related_tests);

        cbc = findViewById(R.id.cbc);
        dns1 = findViewById(R.id.dns1);
        digg = findViewById(R.id.digg);
        digm = findViewById(R.id.digm);
        ma = findViewById(R.id.ma);
        ta = findViewById(R.id.ta);
        Cigm = findViewById(R.id.Cigm);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Fever Related Tests");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        Intent i = new Intent(FeverRelatedTests.this, TestDescription.class);
        i.putExtra("test_category","fever_related_tests");

        cbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.cbc);
                String test_data = getString(R.string.cbc_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("prep", test_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                startActivity(i);
            }
        });

        dns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.dns1);
                String test_data = getString(R.string.dns1_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("prep", test_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                startActivity(i);
            }
        });

        digg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.digg);
                String test_data = getString(R.string.digg_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("prep", test_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                startActivity(i);
            }
        });

        digm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.digm);
                String test_data = getString(R.string.digm_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("prep", test_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                startActivity(i);
            }
        });

        ma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ma);
                String test_data = getString(R.string.ma_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("prep", test_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                startActivity(i);
            }
        });

        ta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ta);
                String test_data = getString(R.string.ta_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("prep", test_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                startActivity(i);
            }
        });

        Cigm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.Cigm);
                String test_data = getString(R.string.Cigm_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("prep", test_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                startActivity(i);
            }
        });
    }
}