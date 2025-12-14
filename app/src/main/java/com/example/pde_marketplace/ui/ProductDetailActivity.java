package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.model.CartItem;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvName, tvDesc, tvPrice;
    private ImageView ivImage;
    private Button btnAdd;
    private static ArrayList<CartItem> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // --- Configurar barra superior con botón de retroceso ---
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detalle del producto");
        }

        // --- Referencias UI ---
        tvName = findViewById(R.id.tvDetailName);
        tvDesc = findViewById(R.id.tvDetailDesc);
        tvPrice = findViewById(R.id.tvDetailPrice);
        ivImage = findViewById(R.id.ivDetailImage);
        btnAdd = findViewById(R.id.btnAddToCart);

        // --- Obtener datos del intent ---
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        double price = intent.getDoubleExtra("price", 0);
        int imageResId = intent.getIntExtra("imageResId", R.drawable.ic_launcher_foreground);

        // --- Mostrar datos ---
        tvName.setText(name);
        tvDesc.setText(desc);
        tvPrice.setText("€" + String.format("%.2f", price));
        ivImage.setImageResource(imageResId);

        // --- Evento botón añadir ---
        btnAdd.setOnClickListener(v -> {
            cart.add(new CartItem(name, price, 1, imageResId));
            Toast.makeText(this, "Añadido al carrito", Toast.LENGTH_SHORT).show();
            finish(); // 🔹 Vuelve automáticamente al catálogo
        });
    }

    // --- Manejo de botón de navegación (flecha atrás) ---
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // --- Métodos para acceder al carrito desde otras actividades ---
    public static ArrayList<CartItem> getCartItems() {
        return cart;
    }

    public static void clearCart() {
        cart.clear();
    }
}
