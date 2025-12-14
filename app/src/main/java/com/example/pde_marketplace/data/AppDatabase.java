package com.example.pde_marketplace.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.pde_marketplace.model.CartItem;
import com.example.pde_marketplace.model.Order;

@Database(entities = {CartItem.class, Order.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract CartItemDao cartItemDao();
    public abstract OrderDao orderDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "pde_marketplace_db")
                            .allowMainThreadQueries() // simple para prácticas
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
