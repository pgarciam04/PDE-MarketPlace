package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.auth.LoginActivity;
import com.example.pde_marketplace.data.IncidentRepository;
import com.example.pde_marketplace.model.Incident;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvAddress = findViewById(R.id.tvAddress);

        EditText etMessage = findViewById(R.id.etUserMessage);
        Button btnSend = findViewById(R.id.btnSendMessage);
        Button btnLogout = findViewById(R.id.btnLogout);

        if (user != null) {
            tvEmail.setText("Usuario: " + user.getEmail());
        }

        SharedPreferences prefs = getSharedPreferences("user_profile", MODE_PRIVATE);
        tvName.setText("Nombre: " + prefs.getString("name", "—"));
        tvPhone.setText("Teléfono: " + prefs.getString("phone", "—"));
        tvAddress.setText("Dirección: " + prefs.getString("address", "—"));

        // =========================
        // ENVIAR INCIDENCIA
        // =========================
        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();

            if (msg.isEmpty()) {
                Toast.makeText(this, "Escribe un mensaje", Toast.LENGTH_SHORT).show();
                return;
            }

            Incident incident = new Incident(
                    user != null ? user.getEmail() : "desconocido",
                    msg,
                    System.currentTimeMillis()
            );

            IncidentRepository.addIncident(incident);

            Toast.makeText(this, "Incidencia enviada al técnico", Toast.LENGTH_SHORT).show();
            etMessage.setText("");
        });

        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}
