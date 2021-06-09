package com.example.homylabs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.Patient.MainActivity;
import com.example.homylabs.R;
import com.example.homylabs.userModal.BookingModel;

import java.util.List;

public class SelectedTestsRecyclerAdapter extends RecyclerView.Adapter<SelectedTestsRecyclerAdapter.MyViewHolder> {

    List<BookingModel> list;
    private Context context;
    private getTotalPrice listener;

    public SelectedTestsRecyclerAdapter(List<BookingModel> list, Context context, getTotalPrice listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SelectedTestsRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_of_your_choosen_test_xml_design, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedTestsRecyclerAdapter.MyViewHolder holder, int position) {

        holder.testName.setText(list.get(position).getTestName());
        holder.testPrice.setText(list.get(position).getTestPrice());

        if (position == list.size() - 1) {
            listener.totalAmount();
        }

//        String ownerUserId = list.get(position).getUserId();

        holder.btnDelete.setOnClickListener(v -> {
            list.remove(list.get(position));

            if (list.isEmpty()) {
                listener.totalAmount();
                Toast.makeText(context, "Please Select Tests", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView testName, testPrice, tvTotal;
        ImageView btnDelete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            testName = itemView.findViewById(R.id.tvTestName);
            testPrice = itemView.findViewById(R.id.tv_TestPrice);
            btnDelete = itemView.findViewById(R.id.iv_delete);
            tvTotal = itemView.findViewById(R.id.tvTotalAmount);
        }
    }

    public interface getTotalPrice {
        void totalAmount();
    }
}
