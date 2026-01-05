package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.model.CartItem;
import com.example.pde_marketplace.model.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

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

        cartItems = ProductDetailActivity.getCartItems();

        adapter = new CartAdapter(this, cartItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        updateTotal();

        btnConfirm.setOnClickListener(v -> confirmPurchase());
    }

    private void confirmPurchase() {

        if (cartItems == null || cartItems.isEmpty()) {
            Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // ✅ MENSAJE INMEDIATO (SIEMPRE)
        Toast.makeText(
                this,
                "Compra realizada correctamente",
                Toast.LENGTH_LONG
        ).show();

        // 🔹 Guardar pedido (no bloquea la UX)
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            double total = 0;
            StringBuilder summary = new StringBuilder();

            for (CartItem item : cartItems) {
                total += item.getPrice() * item.getQuantity();
                summary.append("• ").append(item.getName()).append("\n");
            }

            Order order = new Order(
                    summary.toString().trim(),
                    total,
                    System.currentTimeMillis()
            );

            FirebaseFirestore.getInstance()
                    .collection("users")
                    .document(user.getUid())
                    .collection("orders")
                    .add(order);
        }

        // 🧹 Vaciar carrito
        ProductDetailActivity.clearCart();

        // ⏳ ESPERA 500 ms Y VUELVE AL CATÁLOGO
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(CartActivity.this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 500);
    }

    private void updateTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPrice() * item.getQuantity();
        }
        tvTotal.setText("Total: €" + String.format("%.2f", total));
    }
}
