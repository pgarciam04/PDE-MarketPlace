package com.example.pde_marketplace.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String productSummary;
    private double total;
    private long dateMillis;

    // 🔴 Constructor vacío obligatorio para Firestore
    public Order() {
    }

    // Constructor normal
    public Order(String productSummary, double total, long dateMillis) {
        this.productSummary = productSummary;
        this.total = total;
        this.dateMillis = dateMillis;
    }

    // ---------- GETTERS ----------
    public int getId() {
        return id;
    }

    public String getProductSummary() {
        return productSummary;
    }

    public double getTotal() {
        return total;
    }

    public long getDateMillis() {
        return dateMillis;
    }

    // ---------- SETTERS (OBLIGATORIOS PARA FIRESTORE) ----------
    public void setId(int id) {
        this.id = id;
    }

    public void setProductSummary(String productSummary) {
        this.productSummary = productSummary;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDateMillis(long dateMillis) {
        this.dateMillis = dateMillis;
    }
}
