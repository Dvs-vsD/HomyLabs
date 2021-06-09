package com.example.homylabs.Patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.homylabs.R;

public class SkinRelatedTests extends AppCompatActivity {

    private CardView beap;
    private CardView vp;
    private CardView vd;
    private CardView vb12;
    private CardView thyroid;
    private CardView cbc;
    private CardView tsh;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_related_tests);

        beap = findViewById(R.id.beap);
        vp = findViewById(R.id.vp);
        vd = findViewById(R.id.vd);
        vb12 = findViewById(R.id.vb12);
        thyroid = findViewById(R.id.thyroid);
        cbc = findViewById(R.id.cbc);
        tsh = findViewById(R.id.tsh);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Skin Related Tests");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        Intent i = new Intent(SkinRelatedTests.this, TestDescription.class);
        i.putExtra("test_category","skin_related_tests");

        beap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.beap);
                String test_data = getString(R.string.beap_data);
                String test_prep = getString(R.string.beap_prep);
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

        vd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.vd);
                String test_data = getString(R.string.vd_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        vb12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.vb12);
                String test_data = getString(R.string.vb12_data);
                String test_prep = getString(R.string.gen_prep);
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

        tsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.tsh);
                String test_data = getString(R.string.tsh_data);
                String test_prep = getString(R.string.gene_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });
    }
}