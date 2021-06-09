package com.example.homylabs.userModal;

import java.io.Serializable;
import java.util.List;

public class BookedTestPatientModel implements Serializable {
    String patientName,patientEmail,patientId,testName;
    List<BookedTestPatientModel> testNameList;

    public BookedTestPatientModel(String patientName, String patientEmail, String patientId) {
        this.patientName = patientName;
        this.patientEmail = patientEmail;
        this.patientId = patientId;
    }

    public BookedTestPatientModel(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<BookedTestPatientModel> getTestNameList() {
        return testNameList;
    }

    public void setTestNameList(List<BookedTestPatientModel> testNameList) {
        this.testNameList = testNameList;
    }
}
