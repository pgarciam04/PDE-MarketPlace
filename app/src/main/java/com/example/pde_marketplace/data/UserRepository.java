package com.example.pde_marketplace.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pde_marketplace.model.User;

public class UserRepository {

    private static final String PREFS = "users_prefs";

    public static void saveUser(Context context, User user) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(user.getEmail() + "_name", user.getName());
        editor.putString(user.getEmail() + "_phone", user.getPhone());
        editor.putString(user.getEmail() + "_address", user.getAddress());

        editor.apply();
    }

    public static User getUserByEmail(Context context, String email) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        String name = prefs.getString(email + "_name", null);
        String phone = prefs.getString(email + "_phone", null);
        String address = prefs.getString(email + "_address", null);

        if (name == null || phone == null || address == null) return null;

        return new User(email, name, phone, address);
    }
}
