package com.example.pde_marketplace.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.ProductRepository;

public class CreateProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        EditText etName = findViewById(R.id.etProductName);
        EditText etDesc = findViewById(R.id.etProductDesc);
        EditText etPrice = findViewById(R.id.etProductPrice);
        Button btnSave = findViewById(R.id.btnSaveProduct);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String desc = etDesc.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();

            if (name.isEmpty() || desc.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Precio inválido", Toast.LENGTH_SHORT).show();
                return;
            }

            ProductRepository.addProduct(name, desc, price);
            Toast.makeText(this, "Producto creado", Toast.LENGTH_SHORT).show();
            finish(); // vuelve al catálogo técnico
        });
    }
}
