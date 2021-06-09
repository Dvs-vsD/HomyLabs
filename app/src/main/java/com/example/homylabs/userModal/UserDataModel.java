package com.example.homylabs.userModal;

import java.util.List;

public class UserDataModel {
    String userName, email, password, phoneNo, userId, labName, labAddress, testName, testPrep, testPrice, ownerId, category;
    List<UserDataModel> testData;

    public UserDataModel(String userName, String email, String password, String phoneNo, String userId, String labName, String labAddress, String testPrice, String testName, String testPrep) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.userId = userId;
        this.labName = labName;
        this.labAddress = labAddress;
        this.testPrice = testPrice;
        this.testName = testName;
        this.testPrep = testPrep;
    }

    public UserDataModel() {
    }

    public UserDataModel(String category) {
        this.category = category;
    }

//SignUp Constructor for patient

    public UserDataModel(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    //SignUp Constructor for lab Owner
    public UserDataModel(String userName, String email, String password, String labName, String labAddress) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.labName = labName;
        this.labAddress = labAddress;
    }

    //    Lab Data Constructor for final booking activity
    public UserDataModel(String ownerId, String labName, String labAddress, String userName, String email, String testName, String testPrice) {
        this.ownerId = ownerId;
        this.labName = labName;
        this.labAddress = labAddress;
        this.userName = userName;
        this.email = email;
        this.testName = testName;
        this.testPrice = testPrice;
    }

    // test data constructor
    public UserDataModel(String testName, String testPrice) {
        this.testName = testName;
        this.testPrice = testPrice;
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

    public String getTestPrice() {
        return testPrice;
    }

    public void setTestPrice(String testPrice) {
        this.testPrice = testPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<UserDataModel> getTestData() {
        return testData;
    }

    public void setTestData(List<UserDataModel> testData) {
        this.testData = testData;
    }
}
