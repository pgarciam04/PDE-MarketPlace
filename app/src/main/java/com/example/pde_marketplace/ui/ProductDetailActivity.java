package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.ProductRepository;
import com.example.pde_marketplace.model.CartItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView tvName, tvDesc, tvPrice, tvQuantity;
    private ImageView ivImage;
    private Button btnAdd, btnDelete, btnPlus, btnMinus;

    private int quantity = 1;
    private int productPosition;

    private static final ArrayList<CartItem> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvDesc = findViewById(R.id.tvDetailDesc);
        TextView tvPrice = findViewById(R.id.tvDetailPrice);
        TextView tvQuantity = findViewById(R.id.tvQuantity);
        ImageView ivImage = findViewById(R.id.ivDetailImage);

        Button btnAdd = findViewById(R.id.btnAddToCart);
        Button btnDelete = findViewById(R.id.btnDeleteProduct);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnMinus = findViewById(R.id.btnMinus);

        // 🔒 Oculto por defecto
        btnDelete.setVisibility(View.GONE);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");
        double price = intent.getDoubleExtra("price", 0);
        int imageResId = intent.getIntExtra("imageResId", R.drawable.ic_launcher_foreground);
        int productId = intent.getIntExtra("productId", -1);

        tvName.setText(name);
        tvDesc.setText(desc);
        tvPrice.setText("€" + String.format("%.2f", price));
        ivImage.setImageResource(imageResId);

        final int[] quantity = {1};
        tvQuantity.setText("1");

        btnPlus.setOnClickListener(v -> {
            quantity[0]++;
            tvQuantity.setText(String.valueOf(quantity[0]));
        });

        btnMinus.setOnClickListener(v -> {
            if (quantity[0] > 1) {
                quantity[0]--;
                tvQuantity.setText(String.valueOf(quantity[0]));
            }
        });

        // 🛒 Usuario
        btnAdd.setOnClickListener(v -> {
            ProductDetailActivity.getCartItems()
                    .add(new CartItem(name, price, quantity[0], imageResId));
            Toast.makeText(this, "Añadido al carrito", Toast.LENGTH_SHORT).show();
            finish();
        });

        // 🛠 COMPROBAR TÉCNICO (ESTA ES LA CLAVE)
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            if (email != null && email.trim().toLowerCase().endsWith("@tecnico.com")) {

                // ✅ MOSTRAR BOTÓN
                btnDelete.setVisibility(View.VISIBLE);

                btnDelete.setOnClickListener(v -> {
                    ProductRepository.removeProductById(productId);
                    Toast.makeText(this, "Producto eliminado", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }
        }
    }


    public static ArrayList<CartItem> getCartItems() {
        return cart;
    }

    public static void clearCart() {
        cart.clear();
    }

}
