package com.example.homylabs.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homylabs.R;
import com.example.homylabs.userModal.ReportDownloadDataModel;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.util.List;

public class ReportDownloadAdapter extends RecyclerView.Adapter<ReportDownloadAdapter.myViewHolder> {

    List<ReportDownloadDataModel> reportNameList;
    Context context;

    public ReportDownloadAdapter(List<ReportDownloadDataModel> reportNameList, Context context) {
        this.reportNameList = reportNameList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_of_report_download_layout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        String fileName = reportNameList.get(position).getReportName();
        String fileUrl = reportNameList.get(position).getUrl();
        holder.tvReportFileName.setText(fileName);
        holder.btnReportDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile(context,fileName,".pdf", Environment.getExternalStorageDirectory(),fileUrl);
                Toast.makeText(context,"Your File is Downloading",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return reportNameList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvReportFileName;
        MaterialButton btnReportDownload;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReportFileName = itemView.findViewById(R.id.tvReportFileName);
            btnReportDownload = itemView.findViewById(R.id.btnReportDownload);
        }
    }

    public long downloadFile(Context context, String fileName, String fileExtension, File destinationDirectory, String url) {


        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, String.valueOf(destinationDirectory), fileName + fileExtension);

        return downloadmanager.enqueue(request);
    }
}
