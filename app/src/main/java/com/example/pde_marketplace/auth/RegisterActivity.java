package com.example.pde_marketplace.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.UserRepository;
import com.example.pde_marketplace.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etName, etPhone, etAddress;
    private Button btnRegister;
    private FirebaseAuthManager authManager;

    // Dominios permitidos
    private static final String[] ALLOWED_DOMAINS = {
            "@gmail.com",
            "@hotmail.com",
            "@outlook.es",
            "@yahoo.es",
            "@tecnico.com"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        authManager = new FirebaseAuthManager();

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etAddress = findViewById(R.id.etAddress);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {

            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            // =========================
            // VALIDACIONES
            // =========================

            if (email.isEmpty() || password.isEmpty()
                    || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación dominio email
            boolean validDomain = false;
            for (String domain : ALLOWED_DOMAINS) {
                if (email.endsWith(domain)) {
                    validDomain = true;
                    break;
                }
            }
            if (!validDomain) {
                Toast.makeText(
                        this,
                        "Correo no permitido",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            // Validación nombre (solo letras y espacios)
            if (!name.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")) {
                Toast.makeText(
                        this,
                        "El nombre no puede contener números",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Validación teléfono (exactamente 9 números)
            if (!phone.matches("^\\d{9}$")) {
                Toast.makeText(
                        this,
                        "El teléfono debe tener 9 números",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            // =========================
            // REGISTRO FIREBASE
            // =========================
            authManager.registerUser(
                    email,
                    password,
                    name,
                    phone,
                    address,
                    RegisterActivity.this
            );

            // =========================
            // GUARDAR USUARIO EN REPOSITORIO
            // =========================
            UserRepository.saveUser(
                    RegisterActivity.this,
                    new User(email, name, phone, address)
            );


            Toast.makeText(
                    this,
                    "Cuenta registrada correctamente",
                    Toast.LENGTH_SHORT
            ).show();

            // Volver al login tras 0.5s
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }, 500);
        });
    }
}
