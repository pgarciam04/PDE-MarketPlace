package com.example.pde_marketplace.data;

import com.example.pde_marketplace.model.Incident;

import java.util.ArrayList;
import java.util.List;

public class IncidentRepository {

    private static final List<Incident> incidents = new ArrayList<>();

    public static void addIncident(Incident incident) {
        incidents.add(incident);
    }

    public static List<Incident> getIncidents() {
        return incidents;
    }

    public static int getCount() {
        return incidents.size();
    }
}
