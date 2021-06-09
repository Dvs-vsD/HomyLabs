package com.example.homylabs.Patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.homylabs.R;

public class DigestionRelatedTests extends AppCompatActivity {

    private CardView lft;
    private CardView bun;
    private CardView ls;
    private CardView ttg;
    private CardView esr;
    private CardView hpi;
    private CardView ss;
    private CardView as;
    private CardView chs;
    private CardView vp;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digestion_related_tests);

        lft = findViewById(R.id.lft);
        bun = findViewById(R.id.bun);
        ls = findViewById(R.id.ls);
        ttg = findViewById(R.id.ttg);
        esr = findViewById(R.id.esr);
        hpi = findViewById(R.id.hpi);
        ss = findViewById(R.id.ss);
        as = findViewById(R.id.as);
        chs = findViewById(R.id.chs);
        vp = findViewById(R.id.vp);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Digestion Related Tests");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        Intent i = new Intent(DigestionRelatedTests.this, TestDescription.class);
        i.putExtra("test_category","digestion_related_tests");

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

        ls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ls);
                String test_data = getString(R.string.ls_data);
                String test_prep = getString(R.string.ls_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        ttg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ttg);
                String test_data = getString(R.string.ttg_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        esr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.esr);
                String test_data = getString(R.string.esr_data);
                String test_prep = getString(R.string.gene_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        hpi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.hpi);
                String test_data = getString(R.string.hpi_data);
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

        as.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.as);
                String test_data = getString(R.string.as_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        chs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.chs);
                String test_data = getString(R.string.chs_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        vp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.vp);
                String test_data = getString(R.string.vp_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });
    }
}