package com.example.homylabs.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.R;
import com.example.homylabs.userModal.BookedTestPatientModel;
import com.example.homylabs.userModal.BookingModel;

import java.util.List;

public class LabAdminChildRecyclerViewAdapter extends RecyclerView.Adapter<LabAdminChildRecyclerViewAdapter.childViewHolder> {
    List<BookedTestPatientModel> testNameList;

    public LabAdminChildRecyclerViewAdapter(List<BookedTestPatientModel> testNameList) {
        this.testNameList = testNameList;
    }

    @NonNull
    @Override
    public childViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewdesign, parent, false);
        return new childViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull childViewHolder holder, int position) {
        holder.tvTestNameList.setText(testNameList.get(position).getTestName());
    }

    @Override
    public int getItemCount() {
        return testNameList.size();
    }

    public static class childViewHolder extends RecyclerView.ViewHolder {
        TextView tvTestNameList;

        public childViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTestNameList = itemView.findViewById(R.id.tvTestNameListDesign);
        }
    }
}
