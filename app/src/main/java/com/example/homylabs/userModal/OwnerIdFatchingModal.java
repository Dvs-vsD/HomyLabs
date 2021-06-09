package com.example.homylabs.userModal;

public class OwnerIdFatchingModal {
    String email, UserId;

    public OwnerIdFatchingModal(String email, String userId) {
        this.email = email;
        this.UserId = userId;
    }

    public OwnerIdFatchingModal() {
    }

    @Override
    public String toString() {
        return "OwnerIdFatchingModal{" +
                "email='" + email + '\'' +
                ", UserId='" + UserId + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
