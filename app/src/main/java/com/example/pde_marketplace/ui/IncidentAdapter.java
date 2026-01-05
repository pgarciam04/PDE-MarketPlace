package com.example.pde_marketplace.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.model.Incident;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.IncidentViewHolder> {

    private final Context context;
    private final List<Incident> incidents;

    public IncidentAdapter(Context context, List<Incident> incidents) {
        this.context = context;
        this.incidents = incidents;
    }

    @NonNull
    @Override
    public IncidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_incident, parent, false);
        return new IncidentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncidentViewHolder holder, int position) {
        Incident incident = incidents.get(position);

        holder.tvUser.setText("Usuario: " + incident.getUserEmail());
        holder.tvMessage.setText(incident.getMessage());

        String date = new SimpleDateFormat(
                "dd/MM/yyyy HH:mm",
                Locale.getDefault()
        ).format(new Date(incident.getTimestamp()));

        holder.tvDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return incidents.size();
    }

    static class IncidentViewHolder extends RecyclerView.ViewHolder {

        TextView tvUser, tvMessage, tvDate;

        public IncidentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvIncidentUser);
            tvMessage = itemView.findViewById(R.id.tvIncidentMessage);
            tvDate = itemView.findViewById(R.id.tvIncidentDate);
        }
    }
}
