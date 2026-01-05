package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.ProductRepository;
import com.example.pde_marketplace.model.Product;

import java.util.List;

public class TechnicianActivity extends AppCompatActivity {

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician);

        RecyclerView recyclerView = findViewById(R.id.recyclerProductsTech);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> productList = ProductRepository.getProducts();
        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddProduct).setOnClickListener(v -> {
            startActivity(new Intent(this, CreateProductActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged(); // 🔥 refresca catálogo
    }
}
