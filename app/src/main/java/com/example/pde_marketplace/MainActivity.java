package com.example.pde_marketplace;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pde_marketplace.auth.FirebaseAuthManager;
import com.example.pde_marketplace.auth.LoginActivity;
import com.example.pde_marketplace.ui.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuthManager authManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        authManager = new FirebaseAuthManager();

        // Verifica si ya hay un usuario autenticado
        if (authManager.getCurrentUser() != null) {
            // Ir directamente al catálogo
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        } else {
            // Si no hay sesión, ir al login
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        // Cerramos esta actividad para que no quede en el back stack
        finish();
    }
}
