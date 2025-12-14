package com.example.pde_marketplace.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> productList;   // lista actual mostrada
    private List<Product> fullList;      // copia completa para filtrar

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
        this.fullList = new ArrayList<>(productList); // 🔹 guarda la lista original
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvDesc.setText(product.getDescription());
        holder.tvPrice.setText("€" + product.getPrice());
        holder.ivImage.setImageResource(product.getImageResId());

        // Evento: al hacer clic, ir al detalle
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("name", product.getName());
            intent.putExtra("desc", product.getDescription());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("imageResId", product.getImageResId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName, tvDesc, tvPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivProductImage);
            tvName = itemView.findViewById(R.id.tvProductName);
            tvDesc = itemView.findViewById(R.id.tvProductDesc);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }

    // 🔍 Método de filtrado
    public void filter(String text) {
        List<Product> filteredList = new ArrayList<>();

        if (text == null || text.isEmpty()) {
            // Si no se escribe nada, mostrar todos
            filteredList.addAll(fullList);
        } else {
            String query = text.toLowerCase().trim();
            for (Product product : fullList) {
                if (product.getName().toLowerCase().contains(query)
                        || product.getDescription().toLowerCase().contains(query)) {
                    filteredList.add(product);
                }
            }
        }

        productList.clear();
        productList.addAll(filteredList);
        notifyDataSetChanged();
    }
}
