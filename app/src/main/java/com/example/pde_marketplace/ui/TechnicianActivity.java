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

    private RecyclerView recyclerView;
    private Button btnAddProduct, btnProfile, btnIncidents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician);

        // 📦 RecyclerView catálogo
        recyclerView = findViewById(R.id.recyclerProductsTech);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> productList = ProductRepository.getProducts();
        ProductAdapter adapter = new ProductAdapter(this, productList, true);
        recyclerView.setAdapter(adapter);

        // ➕ Crear producto
        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(v -> {
            startActivity(new Intent(
                    TechnicianActivity.this,
                    CreateProductActivity.class
            ));
        });

        // 👤 Perfil técnico
        btnProfile = findViewById(R.id.btnTechProfile);
        btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(
                    TechnicianActivity.this,
                    TechnicianProfileActivity.class
            ));
        });

        // 🚨 Incidencias
        btnIncidents = findViewById(R.id.btnTechIncidents);
        btnIncidents.setOnClickListener(v -> {
            startActivity(new Intent(
                    TechnicianActivity.this,
                    TechnicianIncidentsActivity.class
            ));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
