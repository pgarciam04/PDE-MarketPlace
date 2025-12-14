package com.example.pde_marketplace.auth;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthManager {

    private FirebaseAuth mAuth;

    public FirebaseAuthManager() {
        mAuth = FirebaseAuth.getInstance();
    }

    // Registro
    public void registerUser(String email, String password, Activity activity) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(activity, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        activity.finish(); // volver al login
                    } else {
                        Toast.makeText(activity, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Login
    public void loginUser(String email, String password, Activity activity, Intent nextActivity) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(activity, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
                        activity.startActivity(nextActivity);
                        activity.finish();
                    } else {
                        Toast.makeText(activity, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    // Logout
    public void logout(Activity activity, Intent loginActivity) {
        mAuth.signOut();
        activity.startActivity(loginActivity);
        activity.finish();
    }

    // Obtener usuario actual
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }
}
