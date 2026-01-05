package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.ProductRepository;
import com.example.pde_marketplace.model.Product;

import java.util.List;

public class TechnicianActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician);

        RecyclerView recyclerView = findViewById(R.id.recyclerProductsTech);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> productList = ProductRepository.getProducts();
        recyclerView.setAdapter(new ProductAdapter(this, productList));

        Button btnProfile = findViewById(R.id.btnTechProfile);
        Button btnIncidents = findViewById(R.id.btnTechIncidents);

        // 🔹 PERFIL TÉCNICO (YA FUNCIONA)
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(
                    TechnicianActivity.this,
                    TechnicianProfileActivity.class
            );
            startActivity(intent);
        });

        // 🔹 INCIDENCIAS
        btnIncidents.setOnClickListener(v -> {
            Intent intent = new Intent(
                    TechnicianActivity.this,
                    TechnicianIncidentsActivity.class
            );
            startActivity(intent);
        });
    }
}
