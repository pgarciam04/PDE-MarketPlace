package com.example.pde_marketplace.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        productList = new ArrayList<>();
        productList.add(new Product(1, "Auriculares Bluetooth", "Sonido HD inalámbrico con cancelación de ruido", 39.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(2, "Smartwatch Deportivo", "Monitoriza ritmo cardíaco, pasos y sueño", 59.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(3, "Teclado Mecánico RGB", "Switches azules retroiluminados, ideal para gaming", 89.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(4, "Ratón Inalámbrico", "Sensor óptico de alta precisión, batería de larga duración", 19.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(5, "Altavoz Portátil", "Sonido estéreo 360º con conexión Bluetooth 5.0", 49.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(6, "Cámara Web Full HD", "Ideal para videollamadas y streaming, micrófono incorporado", 29.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(7, "Power Bank 20000mAh", "Cargador portátil con doble salida USB y carga rápida", 24.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(8, "Lámpara LED Escritorio", "Luz regulable táctil con carga USB integrada", 34.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(9, "Mochila Antirrobo", "Compartimento para portátil de hasta 15.6'', puerto USB", 44.99, R.drawable.ic_launcher_foreground));
        productList.add(new Product(10, "Disco Duro Externo 1TB", "Almacenamiento portátil de alta velocidad USB 3.0", 69.99, R.drawable.ic_launcher_foreground));


        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);

        // 🔹 Inicializa el botón flotante del carrito
        FloatingActionButton btnCart = findViewById(R.id.btnCart);
        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        });

        // 🔹 Inicializa el botón flotante de cuenta
        FloatingActionButton btnAccount = findViewById(R.id.btnAccount);
        btnAccount.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
            startActivity(intent);
        });
        FloatingActionButton btnOrders = findViewById(R.id.btnOrders);
        btnOrders.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, OrdersActivity.class);
            startActivity(intent);
        });


        // 🔹 Configurar la barra de búsqueda
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
    public void onBackPressed() {
        moveTaskToBack(true);
        Toast.makeText(this, "Cerrando catálogo", Toast.LENGTH_SHORT).show();
    }
}
