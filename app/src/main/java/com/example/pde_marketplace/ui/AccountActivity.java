package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.auth.LoginActivity;
import com.example.pde_marketplace.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AccountActivity extends AppCompatActivity {

    private TextView tvEmail, tvName, tvPhone, tvAddress;
    private Button btnLogout;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        tvEmail = findViewById(R.id.tvEmail);
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        btnLogout = findViewById(R.id.btnLogout);

        FirebaseUser firebaseUser = auth.getCurrentUser();

        if (firebaseUser == null) {
            finish();
            return;
        }

        // Mostrar email
        tvEmail.setText("Usuario: " + firebaseUser.getEmail());

        // Cargar datos del perfil desde Firestore
        db.collection("users")
                .document(firebaseUser.getUid())
                .get()
                .addOnSuccessListener(document -> {
                    if (document.exists()) {
                        User user = document.toObject(User.class);
                        if (user != null) {
                            tvName.setText("Nombre: " + user.getName());
                            tvPhone.setText("Teléfono: " + user.getPhone());
                            tvAddress.setText("Dirección: " + user.getAddress());
                        }
                    }
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this,
                                "Error cargando perfil",
                                Toast.LENGTH_SHORT).show()
                );

        // Cerrar sesión
        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
