package com.example.pde_marketplace.model;

public class Incident {

    private String userEmail;
    private String message;
    private long timestamp;

    public Incident(String userEmail, String message, long timestamp) {
        this.userEmail = userEmail;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
