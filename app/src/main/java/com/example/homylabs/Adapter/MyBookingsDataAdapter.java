package com.example.homylabs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.R;
import com.example.homylabs.userModal.BookingModel;

import java.util.List;

public class MyBookingsDataAdapter extends RecyclerView.Adapter<MyBookingsDataAdapter.MyViewHolder> {
    List<BookingModel> bookingDetailsList;
    Context context;

    public MyBookingsDataAdapter(List<BookingModel> bookingDetailsList, Context context) {
        this.bookingDetailsList = bookingDetailsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyBookingsDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_of_my_booking_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookingsDataAdapter.MyViewHolder holder, int position) {
    holder.tv_lab_name.setText(bookingDetailsList.get(position).getLabName());
    holder.tv_lab_address.setText(bookingDetailsList.get(position).getLabAddress());
    holder.tv_technician_name.setText(bookingDetailsList.get(position).getTechnicianName());

    ChildRecyclerViewAdapter adapter = new ChildRecyclerViewAdapter(bookingDetailsList.get(position).getTestNameList());
    holder.recViewInner.setLayoutManager(new LinearLayoutManager(context));
    holder.recViewInner.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return bookingDetailsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_lab_name,tv_lab_address,tv_technician_name;
        RecyclerView recViewInner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_lab_name = itemView.findViewById(R.id.tv_lab_name);
            tv_lab_address = itemView.findViewById(R.id.tv_lab_address);
            tv_technician_name = itemView.findViewById(R.id.tv_technician_name);
            recViewInner = itemView.findViewById(R.id.recViewInner);
        }
    }

}
