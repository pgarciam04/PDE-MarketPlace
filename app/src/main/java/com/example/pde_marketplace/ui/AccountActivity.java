package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pde_marketplace.MainActivity;
import com.example.pde_marketplace.R;
import com.example.pde_marketplace.auth.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    private TextView tvEmail;
    private Button btnLogout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        auth = FirebaseAuth.getInstance();
        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);

        // Mostrar correo del usuario actual
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            tvEmail.setText("Usuario: " + user.getEmail());
        } else {
            tvEmail.setText("No hay usuario autenticado");
        }

        // Cerrar sesión
        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

            // 🔹 Redirigir al LoginActivity después de cerrar sesión
            Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

    }
}
