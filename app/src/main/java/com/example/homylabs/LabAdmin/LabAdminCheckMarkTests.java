package com.example.homylabs.LabAdmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.homylabs.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LabAdminCheckMarkTests extends AppCompatActivity {

    private MaterialButton btn_submit;
    private CheckBox diabetesHbA1c, diabetesIns, diabetesFbs, diabetesLp, diabetesRbs, diabetesC_peptide, diabetesThyroid, diabetesPpbs, diabetesLft, diabetesCbc;
    private EditText etDiabetesHbA1cPrice, etDiabetesInsPrice, etDiabetesFbsPrice, etDiabetesLpPrice, etDiabetesRbsPrice, etDiabetesC_peptidePrice, etDiabetesThyroidPrice, etDiabetesPpbsPrice, etDiabetesLftPrice, etDiabetesCbcPrice;
    private CheckBox feverCbc, feverDns1, feverDigg, feverDigm, feverMa, feverTa, feverCigm;
    private EditText etFeverCbcPrice, etFeverDns1Price, etFeverDiggPrice, etFeverDigmPrice, etFeverMaPrice, etFeverTaPrice, etFeverCigmPrice;
    private CheckBox skinBeap, skinVp, skinVd, skinVb12, skinThyroid, skinCbc, skinTsh;
    private EditText etSkinBeapPrice, etSkinVpPrice, etSkinVdPrice, etSkinVb12Price, etSkinThyroidPrice, etSkinCbcPrice, etSkinTshPrice;
    private CheckBox kidneyCs, kidneyBun, kidneyMu, kidneySs, kidneyPs, kidneyCbc;
    private EditText etKidneyCsPrice, etKidneyBunPrice, etKidneyMuPrice, etKidneySsPrice, etKidneyPsPrice, etKidneyCbcPrice;
    private CheckBox digestionLft, digestionBun, digestionLs, digestionTtg, digestionEsr, digestionHpi, digestionSs, digestionAs, digestionChs, digestionVp;
    private EditText etDigestionLftPrice, etDigestionBunPrice, etDigestionLsPrice, etDigestionTtgPrice, etDigestionEsrPrice, etDigestionHpiPrice, etDigestionSsPrice, etDigestionAsPrice, etDigestionChsPrice, etDigestionVpPrice;
    private CheckBox boneVp, bonePh, boneAp, boneCals, boneMb, bonePb, boneZinc, boneVd25oh;
    private EditText etBoneVpPrice, etBonePhPrice, etBoneApPrice, etBoneCalsPrice, etBoneMbPrice, etBonePbPrice, etBoneZincPrice, etBoneVd25ohPrice;
    private FirebaseDatabase database;
    private TextView tvOwnerName;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_admin_check_mark_tests);

        diabetesHbA1c = findViewById(R.id.diabetesHbA1c);
        diabetesIns = findViewById(R.id.diabetesIns);
        diabetesFbs = findViewById(R.id.diabetesFbs);
        diabetesLp = findViewById(R.id.diabetesLp);
        diabetesC_peptide = findViewById(R.id.diabetesC_peptide);
        diabetesRbs = findViewById(R.id.diabetesRbs);
        diabetesThyroid = findViewById(R.id.diabetesThyroid);
        diabetesPpbs = findViewById(R.id.diabetesPpbs);
        diabetesLft = findViewById(R.id.diabetesLft);
        diabetesCbc = findViewById(R.id.diabetesCbc);

        etDiabetesHbA1cPrice = findViewById(R.id.diabetesHbA1cPrice);
        etDiabetesInsPrice = findViewById(R.id.diabetesInsPrice);
        etDiabetesFbsPrice = findViewById(R.id.diabetesFbsPrice);
        etDiabetesLpPrice = findViewById(R.id.diabetesLpPrice);
        etDiabetesC_peptidePrice = findViewById(R.id.diabetesC_peptidePrice);
        etDiabetesRbsPrice = findViewById(R.id.diabetesRbsPrice);
        etDiabetesThyroidPrice = findViewById(R.id.diabetesThyroidPrice);
        etDiabetesPpbsPrice = findViewById(R.id.diabetesPpbsPrice);
        etDiabetesLftPrice = findViewById(R.id.diabetesLftPrice);
        etDiabetesCbcPrice = findViewById(R.id.diabetesCbcPrice);

        feverCbc = findViewById(R.id.feverCbc);
        feverDns1 = findViewById(R.id.feverDns1);
        feverDigg = findViewById(R.id.feverDigg);
        feverDigm = findViewById(R.id.feverDigm);
        feverMa = findViewById(R.id.feverMa);
        feverTa = findViewById(R.id.feverTa);
        feverCigm = findViewById(R.id.feverCigm);

        etFeverCbcPrice = findViewById(R.id.feverCbcPrice);
        etFeverDns1Price = findViewById(R.id.feverDns1Price);
        etFeverDiggPrice = findViewById(R.id.feverDiggPrice);
        etFeverDigmPrice = findViewById(R.id.feverDigmPrice);
        etFeverMaPrice = findViewById(R.id.feverMaPrice);
        etFeverTaPrice = findViewById(R.id.feverTaPrice);
        etFeverCigmPrice = findViewById(R.id.feverCigmPrice);

        skinBeap = findViewById(R.id.skinBeap);
        skinVp = findViewById(R.id.skinVp);
        skinVd = findViewById(R.id.skinVd);
        skinVb12 = findViewById(R.id.skinVb12);
        skinThyroid = findViewById(R.id.skinThyroid);
        skinCbc = findViewById(R.id.skinCbc);
        skinTsh = findViewById(R.id.skinTsh);

        etSkinBeapPrice = findViewById(R.id.skinBeapPrice);
        etSkinVpPrice = findViewById(R.id.skinVpPrice);
        etSkinVdPrice = findViewById(R.id.skinVdPrice);
        etSkinVb12Price = findViewById(R.id.skinVb12Price);
        etSkinThyroidPrice = findViewById(R.id.skinThyroidPrice);
        etSkinCbcPrice = findViewById(R.id.skinCbcPrice);
        etSkinTshPrice = findViewById(R.id.skinTshPrice);

        kidneyCs = findViewById(R.id.kidneyCs);
        kidneyBun = findViewById(R.id.kidneyBun);
        kidneyMu = findViewById(R.id.kidneyMu);
        kidneySs = findViewById(R.id.kidneySs);
        kidneyPs = findViewById(R.id.kidneyPs);
        kidneyCbc = findViewById(R.id.kidneyCbc);

        etKidneyCsPrice = findViewById(R.id.kidneyCsPrice);
        etKidneyBunPrice = findViewById(R.id.kidneyBunPrice);
        etKidneyMuPrice = findViewById(R.id.kidneyMuPrice);
        etKidneySsPrice = findViewById(R.id.kidneySsPrice);
        etKidneyPsPrice = findViewById(R.id.kidneyPsPrice);
        etKidneyCbcPrice = findViewById(R.id.kidneyCbcPrice);

        digestionLft = findViewById(R.id.digestionLft);
        digestionBun = findViewById(R.id.digestionBun);
        digestionLs = findViewById(R.id.digestionLs);
        digestionTtg = findViewById(R.id.digestionTtg);
        digestionEsr = findViewById(R.id.digestionEsr);
        digestionHpi = findViewById(R.id.digestionHpi);
        digestionSs = findViewById(R.id.digestionSs);
        digestionAs = findViewById(R.id.digestionAs);
        digestionChs = findViewById(R.id.digestionChs);
        digestionVp = findViewById(R.id.digestionVp);

        etDigestionLftPrice = findViewById(R.id.digestionLftPrice);
        etDigestionBunPrice = findViewById(R.id.digestionBunPrice);
        etDigestionLsPrice = findViewById(R.id.digestionLsPrice);
        etDigestionTtgPrice = findViewById(R.id.digestionTtgPrice);
        etDigestionEsrPrice = findViewById(R.id.digestionEsrPrice);
        etDigestionHpiPrice = findViewById(R.id.digestionHpiPrice);
        etDigestionSsPrice = findViewById(R.id.digestionSsPrice);
        etDigestionAsPrice = findViewById(R.id.digestionAsPrice);
        etDigestionChsPrice = findViewById(R.id.digestionChsPrice);
        etDigestionVpPrice = findViewById(R.id.digestionVpPrice);

        boneVp = findViewById(R.id.boneVp);
        bonePh = findViewById(R.id.bonePh);
        boneAp = findViewById(R.id.boneAp);
        boneCals = findViewById(R.id.boneCals);
        boneMb = findViewById(R.id.boneMb);
        bonePb = findViewById(R.id.bonePb);
        boneZinc = findViewById(R.id.boneZinc);
        boneVd25oh = findViewById(R.id.boneVd25oh);

        etBoneVpPrice = findViewById(R.id.boneVpPrice);
        etBonePhPrice = findViewById(R.id.bonePhPrice);
        etBoneApPrice = findViewById(R.id.boneApPrice);
        etBoneCalsPrice = findViewById(R.id.boneCalsPrice);
        etBoneMbPrice = findViewById(R.id.boneMbPrice);
        etBonePbPrice = findViewById(R.id.bonePbPrice);
        etBoneZincPrice = findViewById(R.id.boneZincPrice);
        etBoneVd25ohPrice = findViewById(R.id.boneVd25ohPrice);

        btn_submit = findViewById(R.id.btn_submit);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Check the Tests You are Serving");
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        toolbar.setNavigationOnClickListener(view -> finish());

        database = FirebaseDatabase.getInstance();
        tvOwnerName = findViewById(R.id.tvOwnerName);

        String ownerName = getIntent().getStringExtra("ownerName");
        tvOwnerName.setText(ownerName);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                DatabaseReference diabetes_db_Ref = database.getReference().child("UserLabOwner").child(userId).child("Diabetes_related_tests");

                if (diabetesHbA1c.isChecked()) {
                    String value = getString(R.string.hba1c);
                    diabetes_db_Ref.child(value).setValue(etDiabetesHbA1cPrice.getText().toString());
                }
                if (diabetesIns.isChecked()) {
                    String value = getString(R.string.ins);
                    diabetes_db_Ref.child(value).setValue(etDiabetesInsPrice.getText().toString());
                }
                if (diabetesFbs.isChecked()) {
                    String value = getString(R.string.fbs);
                    diabetes_db_Ref.child(value).setValue(etDiabetesFbsPrice.getText().toString());
                }
                if (diabetesLp.isChecked()) {
                    String value = getString(R.string.lp);
                    diabetes_db_Ref.child(value).setValue(etDiabetesLpPrice.getText().toString());
                }
                if (diabetesRbs.isChecked()) {
                    String value = getString(R.string.rbs);
                    diabetes_db_Ref.child(value).setValue(etDiabetesRbsPrice.getText().toString());
                }
                if (diabetesC_peptide.isChecked()) {
                    String value = getString(R.string.c_peptide);
                    diabetes_db_Ref.child(value).setValue(etDiabetesC_peptidePrice.getText().toString());
                }
                if (diabetesThyroid.isChecked()) {
                    String value = getString(R.string.thyroid);
                    diabetes_db_Ref.child(value).setValue(etDiabetesThyroidPrice.getText().toString());
                }
                if (diabetesPpbs.isChecked()) {
                    String value = getString(R.string.ppbs);
                    diabetes_db_Ref.child(value).setValue(etDiabetesPpbsPrice.getText().toString());
                }
                if (diabetesLft.isChecked()) {
                    String value = getString(R.string.lft);
                    diabetes_db_Ref.child(value).setValue(etDiabetesLftPrice.getText().toString());
                }
                if (diabetesCbc.isChecked()) {
                    String value = getString(R.string.cbc);
                    diabetes_db_Ref.child(value).setValue(etDiabetesCbcPrice.getText().toString());
                }

                DatabaseReference fever_db_Ref = database.getReference().child("UserLabOwner").child(userId).child("fever_related_tests");

                if (feverCbc.isChecked()) {
                    String value = getString(R.string.cbc);
                    fever_db_Ref.child(value).setValue(etFeverCbcPrice.getText().toString());
                }
                if (feverDns1.isChecked()) {
                    String value = getString(R.string.dns1);
                    fever_db_Ref.child(value).setValue(etFeverDns1Price.getText().toString());
                }
                if (feverDigg.isChecked()) {
                    String value = getString(R.string.digg);
                    fever_db_Ref.child(value).setValue(etFeverDiggPrice.getText().toString());
                }
                if (feverDigm.isChecked()) {
                    String value = getString(R.string.digm);
                    fever_db_Ref.child(value).setValue(etFeverDigmPrice.getText().toString());
                }
                if (feverMa.isChecked()) {
                    String value = getString(R.string.ma);
                    fever_db_Ref.child(value).setValue(etFeverMaPrice.getText().toString());
                }
                if (feverTa.isChecked()) {
                    String value = getString(R.string.ta);
                    fever_db_Ref.child(value).setValue(etFeverTaPrice.getText().toString());
                }
                if (feverCigm.isChecked()) {
                    String value = getString(R.string.Cigm);
                    fever_db_Ref.child(value).setValue(etFeverCigmPrice.getText().toString());
                }

                DatabaseReference skin_db_Ref = database.getReference().child("UserLabOwner").child(userId).child("skin_related_tests");

                if (skinBeap.isChecked()) {
                    String value = getString(R.string.beap);
                    skin_db_Ref.child(value).setValue(etSkinBeapPrice.getText().toString());
                }
                if (skinVp.isChecked()) {
                    String value = getString(R.string.vp);
                    skin_db_Ref.child(value).setValue(etSkinVpPrice.getText().toString());
                }
                if (skinVd.isChecked()) {
                    String value = getString(R.string.vd);
                    skin_db_Ref.child(value).setValue(etSkinVdPrice.getText().toString());
                }
                if (skinVb12.isChecked()) {
                    String value = getString(R.string.vb12);
                    skin_db_Ref.child(value).setValue(etSkinVb12Price.getText().toString());
                }
                if (skinThyroid.isChecked()) {
                    String value = getString(R.string.thyroid);
                    skin_db_Ref.child(value).setValue(etSkinThyroidPrice.getText().toString());
                }
                if (skinCbc.isChecked()) {
                    String value = getString(R.string.cbc);
                    skin_db_Ref.child(value).setValue(etSkinCbcPrice.getText().toString());
                }
                if (skinTsh.isChecked()) {
                    String value = getString(R.string.tsh);
                    skin_db_Ref.child(value).setValue(etSkinTshPrice.getText().toString());
                }

                DatabaseReference kidney_db_Ref = database.getReference().child("UserLabOwner").child(userId).child("kidney_related_tests");

                if (kidneyCs.isChecked()) {
                    String value = getString(R.string.cs);
                    kidney_db_Ref.child(value).setValue(etKidneyCsPrice.getText().toString());
                }
                if (kidneyBun.isChecked()) {
                    String value = getString(R.string.bun);
                    kidney_db_Ref.child(value).setValue(etKidneyBunPrice.getText().toString());
                }
                if (kidneyMu.isChecked()) {
                    String value = getString(R.string.mu);
                    kidney_db_Ref.child(value).setValue(etKidneyMuPrice.getText().toString());
                }
                if (kidneySs.isChecked()) {
                    String value = getString(R.string.ss);
                    kidney_db_Ref.child(value).setValue(etKidneySsPrice.getText().toString());
                }
                if (kidneyPs.isChecked()) {
                    String value = getString(R.string.ps);
                    kidney_db_Ref.child(value).setValue(etKidneyPsPrice.getText().toString());
                }
                if (kidneyCbc.isChecked()) {
                    String value = getString(R.string.cbc);
                    kidney_db_Ref.child(value).setValue(etKidneyCbcPrice.getText().toString());
                }

                DatabaseReference digestion_db_Ref = database.getReference().child("UserLabOwner").child(userId).child("digestion_related_tests");

                if (digestionLft.isChecked()) {
                    String value = getString(R.string.lft);
                    digestion_db_Ref.child(value).setValue(etDigestionLftPrice.getText().toString());
                }
                if (digestionBun.isChecked()) {
                    String value = getString(R.string.bun);
                    digestion_db_Ref.child(value).setValue(etDigestionBunPrice.getText().toString());
                }
                if (digestionLs.isChecked()) {
                    String value = getString(R.string.ls);
                    digestion_db_Ref.child(value).setValue(etDigestionLsPrice.getText().toString());
                }
                if (digestionTtg.isChecked()) {
                    String value = getString(R.string.ttg);
                    digestion_db_Ref.child(value).setValue(etDigestionTtgPrice.getText().toString());
                }
                if (digestionEsr.isChecked()) {
                    String value = getString(R.string.esr);
                    digestion_db_Ref.child(value).setValue(etDigestionEsrPrice.getText().toString());
                }
                if (digestionHpi.isChecked()) {
                    String value = getString(R.string.hpi);
                    digestion_db_Ref.child(value).setValue(etDigestionHpiPrice);
                }
                if (digestionSs.isChecked()) {
                    String value = getString(R.string.ss);
                    digestion_db_Ref.child(value).setValue(etDigestionSsPrice.getText().toString());
                }
                if (digestionAs.isChecked()) {
                    String value = getString(R.string.as);
                    digestion_db_Ref.child(value).setValue(etDigestionAsPrice.getText().toString());
                }
                if (digestionChs.isChecked()) {
                    String value = getString(R.string.lft);
                    digestion_db_Ref.child(value).setValue(etDigestionChsPrice.getText().toString());
                }
                if (digestionVp.isChecked()) {
                    String value = getString(R.string.vp);
                    digestion_db_Ref.child(value).setValue(etDigestionVpPrice.getText().toString());
                }

                DatabaseReference bone_db_Ref = database.getReference().child("UserLabOwner").child(userId).child("bone_related_tests");

                if (boneVp.isChecked()) {
                    String value = getString(R.string.vp);
                    bone_db_Ref.child(value).setValue(etBoneVpPrice.getText().toString());
                }
                if (bonePh.isChecked()) {
                    String value = getString(R.string.ph);
                    bone_db_Ref.child(value).setValue(etBonePhPrice.getText().toString());
                }
                if (boneAp.isChecked()) {
                    String value = getString(R.string.ap);
                    bone_db_Ref.child(value).setValue(etBoneApPrice.getText().toString());
                }
                if (boneCals.isChecked()) {
                    String value = getString(R.string.cals);
                    bone_db_Ref.child(value).setValue(etBoneCalsPrice.getText().toString());
                }
                if (boneMb.isChecked()) {
                    String value = getString(R.string.mb);
                    bone_db_Ref.child(value).setValue(etBoneMbPrice.getText().toString());
                }
                if (bonePb.isChecked()) {
                    String value = getString(R.string.pb);
                    bone_db_Ref.child(value).setValue(etBonePbPrice.getText().toString());
                }
                if (boneZinc.isChecked()) {
                    String value = getString(R.string.zinc);
                    bone_db_Ref.child(value).setValue(etBoneZincPrice.getText().toString());
                }
                if (boneVd25oh.isChecked()) {
                    String value = getString(R.string.vd25);
                    bone_db_Ref.child(value).setValue(etBoneVd25ohPrice.getText().toString());
                }

                Intent intent = new Intent(LabAdminCheckMarkTests.this, MainActivityOfLabAdmin.class);
                startActivity(intent);
                finish();
            }
        });

    }
}