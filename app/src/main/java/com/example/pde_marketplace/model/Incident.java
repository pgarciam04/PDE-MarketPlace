package com.example.pde_marketplace.model;

public class Incident {

    private String userEmail;
    private String message;
    private boolean resolved;

    public Incident(String userEmail, String message, long l) {
        this.userEmail = userEmail;
        this.message = message;
        this.resolved = false;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getMessage() {
        return message;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }
}
