package com.example.pde_marketplace.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pde_marketplace.R;
import com.example.pde_marketplace.model.CartItem;
import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrdersAdapter adapter;
    private static final List<List<CartItem>> orderHistory = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        // 🔹 Configurar ActionBar (título y botón atrás)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Historial de pedidos");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recyclerOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());


        adapter = new OrdersAdapter(this, orderHistory);
        recyclerView.setAdapter(adapter);
    }

    // ✅ Maneja la flecha atrás del ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ✅ Guardar un pedido desde el carrito
    public static void saveOrder(List<CartItem> cartItems) {
        if (cartItems == null || cartItems.isEmpty()) return;
        orderHistory.add(new ArrayList<>(cartItems)); // guarda copia del carrito
    }
}
