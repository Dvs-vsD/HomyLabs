package com.example.homylabs.Patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.homylabs.R;

public class BoneRelatedTests extends AppCompatActivity {

    private CardView vp;
    private CardView ph;
    private CardView ap;
    private CardView cals;
    private CardView mb;
    private CardView pb;
    private CardView zinc;
    private CardView vd25oh;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bone_related_tests);

        vp = findViewById(R.id.vp);
        ph = findViewById(R.id.ph);
        ap = findViewById(R.id.ap);
        cals = findViewById(R.id.cals);
        mb = findViewById(R.id.mb);
        pb = findViewById(R.id.pb);
        zinc = findViewById(R.id.zinc);
        vd25oh = findViewById(R.id.vd25oh);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Bone Related Tests");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        Intent i = new Intent(BoneRelatedTests.this, TestDescription.class);
        i.putExtra("test_category","bone_related_tests");

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

        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ph);
                String test_data = getString(R.string.ph_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.ap);
                String test_data = getString(R.string.ap_data);
                String test_prep = getString(R.string.ap_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        cals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.cals);
                String test_data = getString(R.string.cals_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.mb);
                String test_data = getString(R.string.mb_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.pb);
                String test_data = getString(R.string.pb_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        zinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.zinc);
                String test_data = getString(R.string.zinc_data);
                String test_prep = getString(R.string.zinc_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });

        vd25oh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String head = getString(R.string.vd25);
                String test_data = getString(R.string.vd25_data);
                String test_prep = getString(R.string.general_prep);
                i.putExtra("heading", head);
                i.putExtra("About", test_data);
                i.putExtra("prep", test_prep);
                startActivity(i);
            }
        });
    }
}