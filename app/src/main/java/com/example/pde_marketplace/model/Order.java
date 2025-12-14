package com.example.pde_marketplace.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "orders")
public class Order {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String productSummary;
    private double total;
    private long dateMillis; // guardamos fecha como long

    public Order(String productSummary, double total, long dateMillis) {
        this.productSummary = productSummary;
        this.total = total;
        this.dateMillis = dateMillis;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProductSummary() { return productSummary; }
    public double getTotal() { return total; }
    public long getDateMillis() { return dateMillis; }
}
