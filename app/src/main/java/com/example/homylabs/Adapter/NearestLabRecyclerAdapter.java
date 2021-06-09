package com.example.homylabs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.Patient.TestBookingActivity;
import com.example.homylabs.R;
import com.example.homylabs.userModal.UserDataModel;

import java.util.List;

public class NearestLabRecyclerAdapter extends RecyclerView.Adapter<NearestLabRecyclerAdapter.MyViewHolder> {
    Context context;
    List<UserDataModel> list;

    public NearestLabRecyclerAdapter(Context context, List<UserDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_of_laboratory_xml_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.labName.setText(list.get(position).getLabName());
        holder.labAddress.setText(list.get(position).getLabAddress());
        holder.tv_userName.setText(list.get(position).getUserName());
        holder.tvLabOwnerEmail.setText(list.get(position).getEmail());
        holder.tvTestName.setText(list.get(position).getTestName());
        holder.tv_TestPrice.setText(list.get(position).getTestPrice());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TestBookingActivity.class);
            intent.putExtra("ownerId", list.get(position).getOwnerId());
            intent.putExtra("labName", list.get(position).getLabName());
            intent.putExtra("labAddress", list.get(position).getLabAddress());
            intent.putExtra("userName", list.get(position).getUserName());
            intent.putExtra("email", list.get(position).getEmail());
            intent.putExtra("testName", list.get(position).getTestName());
            intent.putExtra("testPrice", list.get(position).getTestPrice());
            intent.putExtra("testPrep", list.get(position).getTestPrep());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
                TextView labName, labAddress, tv_userName, tvLabOwnerEmail, tvTestName, tv_TestPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            labName = itemView.findViewById(R.id.tvLabName);
            labAddress = itemView.findViewById(R.id.tvLabAddress);
            tv_userName = itemView.findViewById(R.id.tv_user_name);
            tvLabOwnerEmail = itemView.findViewById(R.id.tv_email);
            tvTestName = itemView.findViewById(R.id.tvTestName);
            tv_TestPrice = itemView.findViewById(R.id.tv_TestPrice);
        }
    }
}
