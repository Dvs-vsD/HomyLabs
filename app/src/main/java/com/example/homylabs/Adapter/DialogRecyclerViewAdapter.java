package com.example.homylabs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.R;
import com.example.homylabs.userModal.BookingModel;
import com.example.homylabs.userModal.UserDataModel;

import java.util.List;

public class DialogRecyclerViewAdapter extends RecyclerView.Adapter<DialogRecyclerViewAdapter.ViewHolder> {

    private final List<UserDataModel> list;
    private final Context context;
    private final List<BookingModel> selectedTestList;

    public DialogRecyclerViewAdapter(List<UserDataModel> list, Context context, List<BookingModel> selectedTestList) {
        this.list = list;
        this.context = context;
        this.selectedTestList = selectedTestList;
    }

    @NonNull
    @Override
    public DialogRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category_wise_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DialogRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.tvTestCategory.setText(list.get(position).getCategory());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(new TestNamePriceRecyclerAdapter(list.get(position).getTestData(), selectedTestList));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTestCategory;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTestCategory = itemView.findViewById(R.id.tvTestCategory);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }
}
