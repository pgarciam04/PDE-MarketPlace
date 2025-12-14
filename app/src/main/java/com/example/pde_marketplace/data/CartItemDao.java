package com.example.pde_marketplace.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pde_marketplace.model.CartItem;

import java.util.List;

@Dao
public interface CartItemDao {

    @Insert
    void insert(CartItem item);

    @Delete
    void delete(CartItem item);

    @Query("SELECT * FROM cart_items")
    List<CartItem> getAllItems();

    @Query("DELETE FROM cart_items")
    void clearCart();
}
