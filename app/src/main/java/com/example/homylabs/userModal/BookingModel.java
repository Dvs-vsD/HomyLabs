package com.example.homylabs.userModal;

import java.io.Serializable;
import java.util.List;

public class BookingModel implements Serializable {
    private String testName, testPrep, technicianName, labName, labAddress,testPrice,userId;
    private List<BookingModel> testNameList;

    public BookingModel() {
    }

    public BookingModel(String labName, String labAddress, String technicianName) {
        this.technicianName = technicianName;
        this.labName = labName;
        this.labAddress = labAddress;
    }

    public BookingModel( String testName, String testPrice) {
        this.testName = testName;
        this.testPrice = testPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(String testPrice) {
        this.testPrice = testPrice;
    }

    public BookingModel(String testName) {
        this.testName = testName;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabAddress() {
        return labAddress;
    }

    public void setLabAddress(String labAddress) {
        this.labAddress = labAddress;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestPrep() {
        return testPrep;
    }

    public void setTestPrep(String testPrep) {
        this.testPrep = testPrep;
    }

    public List<BookingModel> getTestNameList() {
        return testNameList;
    }

    public void setTestNameList(List<BookingModel> testNameList) {
        this.testNameList = testNameList;
    }
}
