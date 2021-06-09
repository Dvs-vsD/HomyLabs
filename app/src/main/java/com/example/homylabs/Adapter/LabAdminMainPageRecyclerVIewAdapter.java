package com.example.homylabs.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.LabAdmin.BookedDetailsAndReportUpload;
import com.example.homylabs.R;
import com.example.homylabs.userModal.BookedTestPatientModel;
import com.example.homylabs.userModal.BookingModel;

import java.io.Serializable;
import java.util.List;

public class LabAdminMainPageRecyclerVIewAdapter extends RecyclerView.Adapter<LabAdminMainPageRecyclerVIewAdapter.MyViewHolder> {

    Context context;
    List<BookedTestPatientModel> patientList;

    public LabAdminMainPageRecyclerVIewAdapter(Context context, List<BookedTestPatientModel> patientList) {
        this.context = context;
        this.patientList = patientList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_of_booked_tests_on_labadmin_homepage_xml_design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_patient_name.setText(patientList.get(position).getPatientName());
        holder.tv_patient_email.setText(patientList.get(position).getPatientEmail());

        LabAdminChildRecyclerViewAdapter adapter = new LabAdminChildRecyclerViewAdapter(patientList.get(position).getTestNameList());
        holder.recViewInner.setLayoutManager(new LinearLayoutManager(context));
        holder.recViewInner.setAdapter(adapter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookedDetailsAndReportUpload.class);
                intent.putExtra("patient_name", patientList.get(position).getPatientName());
                intent.putExtra("patient_email", patientList.get(position).getPatientEmail());
                intent.putExtra("patientId", patientList.get(position).getPatientId());
                intent.putExtra("SELECTED TEST",(Serializable) patientList.get(position).getTestNameList());
                ((Activity)context).startActivityForResult(intent,101);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_patient_name, tv_patient_email;
        RecyclerView recViewInner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_patient_name = itemView.findViewById(R.id.tv_patient_name);
            tv_patient_email = itemView.findViewById(R.id.tv_patient_email);
            recViewInner = itemView.findViewById(R.id.recViewInner);
        }
    }
}
