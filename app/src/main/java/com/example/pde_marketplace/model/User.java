package com.example.pde_marketplace.model;

public class User {

    private String email;
    private String name;
    private String phone;
    private String address;

    // Constructor vacío obligatorio para Firestore
    public User() {}

    public User(String email, String name, String phone, String address) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}
