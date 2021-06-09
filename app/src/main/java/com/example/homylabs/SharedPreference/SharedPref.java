package com.example.homylabs.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    String userType = "Patient";
    String userId = "uid";
    String labOwnerUserName = "owner";
    String patientUsername = "patient";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SharedPref(Context context) {
        pref = context.getSharedPreferences("HomyLabs", 0);
        editor = pref.edit();
//        editor.clear();
        editor.apply();
    }

    public String getPatientUsername() {
        return pref.getString(patientUsername, "");
    }

    public void setPatientUsername(String patientUsername) {
        editor.putString(this.patientUsername, patientUsername);
        editor.apply();
    }

    public String getLabOwnerUserName() {
        return pref.getString(labOwnerUserName, "");
    }

    public void setLabOwnerUserName(String labOwnerUserName) {
        editor.putString(this.labOwnerUserName, labOwnerUserName);
        editor.apply();
    }

    public String getUserType() {
        return pref.getString(userType, "Patient");
    }

    public void setUserType(String userType) {
        editor.putString(this.userType, userType);
        editor.apply();
    }

    public String getUserId() {
        return pref.getString(userId, "");
    }

    public void setUserId(String userId) {
        editor.putString(this.userId, userId);
        editor.apply();
    }
}
