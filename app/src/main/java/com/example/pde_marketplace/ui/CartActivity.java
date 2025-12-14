package com.example.pde_marketplace.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView tvTotal;
    private Button btnConfirm;
    private CartAdapter adapter;
    private List<CartItem> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerCart);
        tvTotal = findViewById(R.id.tvCartTotal);
        btnConfirm = findViewById(R.id.btnConfirmOrder);

        // ✅ Cargar carrito directamente desde ProductDetailActivity (lista estática compartida)
        cartItems = ProductDetailActivity.getCartItems();

        adapter = new CartAdapter(this, cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // 🔹 Asegura que el adaptador se actualice al abrir
        adapter.notifyDataSetChanged();

        // 🔹 Calcula el total
        updateTotal();

        btnConfirm.setOnClickListener(v -> {
            Toast.makeText(this, "Pedido confirmado. Gracias por su compra.", Toast.LENGTH_LONG).show();

            // ✅ Guardar el pedido en el historial
            OrdersActivity.saveOrder(new ArrayList<>(cartItems));

            ProductDetailActivity.clearCart();
            adapter.notifyDataSetChanged();
            updateTotal();

            recyclerView.postDelayed(this::finish, 1000);
        });


    }

    private void updateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        tvTotal.setText("Total: €" + String.format("%.2f", total));
    }
}
