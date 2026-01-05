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

    private TextView tvName, tvDesc, tvPrice, tvQuantity;
    private ImageView ivImage;
    private Button btnAdd, btnPlus, btnMinus;

    private int quantity = 1;

    private static ArrayList<CartItem> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detalle del producto");
        }

        tvName = findViewById(R.id.tvDetailName);
        tvDesc = findViewById(R.id.tvDetailDesc);
        tvPrice = findViewById(R.id.tvDetailPrice);
        tvQuantity = findViewById(R.id.tvQuantity);
        ivImage = findViewById(R.id.ivDetailImage);

        btnAdd = findViewById(R.id.btnAddToCart);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        double price = intent.getDoubleExtra("price", 0);
        int imageResId = intent.getIntExtra("imageResId", R.drawable.ic_launcher_foreground);

        tvName.setText(name);
        tvDesc.setText(desc);
        tvPrice.setText("€" + String.format("%.2f", price));
        ivImage.setImageResource(imageResId);

        tvQuantity.setText(String.valueOf(quantity));

        // ➕ Aumentar cantidad
        btnPlus.setOnClickListener(v -> {
            quantity++;
            tvQuantity.setText(String.valueOf(quantity));
        });

        // ➖ Disminuir cantidad (mínimo 1)
        btnMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.valueOf(quantity));
            }
        });

        btnAdd.setOnClickListener(v -> {

            boolean found = false;

            for (CartItem item : cart) {
                if (item.getName().equals(name)) {
                    item.setQuantity(item.getQuantity() + quantity);
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new CartItem(name, price, quantity, imageResId));
            }

            Toast.makeText(this,
                    "Añadido al carrito (" + quantity + ")",
                    Toast.LENGTH_SHORT).show();

            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static ArrayList<CartItem> getCartItems() {
        return cart;
    }

    public static void clearCart() {
        cart.clear();
    }
}
