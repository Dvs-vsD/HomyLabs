package com.example.homylabs.userModal;

public class ReportDownloadDataModel {
    String reportName, url;

    public ReportDownloadDataModel(String reportName, String url) {
        this.reportName = reportName;
        this.url = url;
    }

    public ReportDownloadDataModel(String reportName) {
        this.reportName = reportName;
    }

    public ReportDownloadDataModel() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }
}
