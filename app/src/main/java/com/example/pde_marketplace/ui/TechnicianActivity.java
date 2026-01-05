package com.example.pde_marketplace.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.ProductRepository;
import com.example.pde_marketplace.model.Product;

import java.util.List;

public class TechnicianActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician);

        recyclerView = findViewById(R.id.recyclerProductsTech);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 🔹 MISMA FUENTE DE DATOS QUE EL USUARIO
        productList = ProductRepository.getProducts();

        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);
    }
}
