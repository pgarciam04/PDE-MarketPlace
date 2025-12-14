package com.example.pde_marketplace.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pde_marketplace.model.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insert(Order order);

    @Query("SELECT * FROM orders ORDER BY dateMillis DESC")
    List<Order> getAllOrders();
}
