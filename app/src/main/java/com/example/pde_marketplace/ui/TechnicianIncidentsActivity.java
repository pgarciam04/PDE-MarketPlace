package com.example.pde_marketplace.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pde_marketplace.R;
import com.example.pde_marketplace.data.IncidentRepository;

public class TechnicianIncidentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technician_incidents);

        RecyclerView recyclerView = findViewById(R.id.recyclerIncidents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(
                new IncidentAdapter(this, IncidentRepository.getIncidents())
        );
    }
}
