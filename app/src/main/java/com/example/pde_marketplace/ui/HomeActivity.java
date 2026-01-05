package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.ProductRepository;
import com.example.pde_marketplace.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // RecyclerView
        recyclerView = findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 🔹 Catálogo compartido
        productList = ProductRepository.getProducts();
        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        // 🛒 Carrito
        FloatingActionButton btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v ->
                startActivity(new Intent(this, CartActivity.class)));

        // 👤 Cuenta
        FloatingActionButton btnAccount = findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(v ->
                startActivity(new Intent(this, AccountActivity.class)));

        // 🧾 Pedidos
        FloatingActionButton btnOrders = findViewById(R.id.btnOrders);
        btnOrders.setOnClickListener(v ->
                startActivity(new Intent(this, OrdersActivity.class)));

        // 🔍 Búsqueda
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 🔄 Refresca catálogo por si el técnico añadió productos
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
