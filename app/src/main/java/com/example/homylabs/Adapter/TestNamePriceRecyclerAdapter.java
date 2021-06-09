package com.example.homylabs.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.R;
import com.example.homylabs.userModal.BookingModel;
import com.example.homylabs.userModal.UserDataModel;

import java.util.List;

public class TestNamePriceRecyclerAdapter extends RecyclerView.Adapter<TestNamePriceRecyclerAdapter.ViewHolder> {

    private final List<UserDataModel> list;
    private final List<BookingModel> selectedTestList;

    public TestNamePriceRecyclerAdapter(List<UserDataModel> list, List<BookingModel> selectedTestList) {
        this.list = list;
        this.selectedTestList = selectedTestList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checkbox_test_name_price, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String testName = list.get(position).getTestName();
        String testPrice = list.get(position).getTestPrice();
        holder.tvTestName.setText(testName);
        holder.tv_TestPrice.setText("₹ "+testPrice);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedTestList.add(new BookingModel(testName,testPrice));
            } else {
                selectedTestList.remove(new BookingModel(testName,testPrice));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTestName,tv_TestPrice;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTestName = itemView.findViewById(R.id.tvTestName);
            tv_TestPrice = itemView.findViewById(R.id.tv_TestPrice);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}
