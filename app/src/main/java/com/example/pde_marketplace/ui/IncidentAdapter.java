package com.example.pde_marketplace.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.IncidentRepository;
import com.example.pde_marketplace.model.Incident;

import java.util.List;

public class IncidentAdapter extends RecyclerView.Adapter<IncidentAdapter.ViewHolder> {

    private final Context context;
    private final List<Incident> incidents;

    public IncidentAdapter(Context context, List<Incident> incidents) {
        this.context = context;
        this.incidents = incidents;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_incident, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Incident incident = incidents.get(position);

        holder.tvUser.setText("Usuario: " + incident.getUserEmail());
        holder.tvMessage.setText(incident.getMessage());

        if (incident.isResolved()) {
            holder.tvStatus.setText("RESUELTA");
            holder.tvStatus.setTextColor(context.getColor(android.R.color.holo_green_dark));
            holder.btnResolve.setEnabled(false);
        } else {
            holder.tvStatus.setText("PENDIENTE");
            holder.tvStatus.setTextColor(context.getColor(android.R.color.holo_red_dark));
            holder.btnResolve.setEnabled(true);
        }

        holder.btnResolve.setOnClickListener(v -> {
            IncidentRepository.markResolved(position);
            notifyItemChanged(position);
        });

        holder.btnDelete.setOnClickListener(v -> {
            IncidentRepository.removeIncident(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, incidents.size());
        });
    }

    @Override
    public int getItemCount() {
        return incidents.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUser, tvMessage, tvStatus;
        Button btnResolve, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvIncidentUser);
            tvMessage = itemView.findViewById(R.id.tvIncidentMessage);
            tvStatus = itemView.findViewById(R.id.tvIncidentStatus);
            btnResolve = itemView.findViewById(R.id.btnResolve);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
