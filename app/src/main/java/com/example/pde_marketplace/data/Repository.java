package com.example.pde_marketplace.data;

import android.content.Context;

import com.example.pde_marketplace.model.CartItem;
import com.example.pde_marketplace.model.Order;

import java.util.List;

public class Repository {

    private AppDatabase db;

    public Repository(Context context) {
        db = AppDatabase.getInstance(context);
    }

    // Métodos para carrito
    public void addToCart(CartItem item) { db.cartItemDao().insert(item); }
    public List<CartItem> getCartItems() { return db.cartItemDao().getAllItems(); }
    public void clearCart() { db.cartItemDao().clearCart(); }

    // Métodos para pedidos
    public void addOrder(Order order) { db.orderDao().insert(order); }
    public List<Order> getOrders() { return db.orderDao().getAllOrders(); }
}
