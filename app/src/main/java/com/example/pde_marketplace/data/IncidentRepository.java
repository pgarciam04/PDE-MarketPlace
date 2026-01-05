package com.example.pde_marketplace.data;

import com.example.pde_marketplace.model.Incident;

import java.util.ArrayList;
import java.util.List;

public class IncidentRepository {

    private static final List<Incident> incidents = new ArrayList<>();

    public static List<Incident> getIncidents() {
        return incidents;
    }

    public static void addIncident(Incident incident) {
        incidents.add(incident);
    }

    public static void removeIncident(int position) {
        if (position >= 0 && position < incidents.size()) {
            incidents.remove(position);
        }
    }

    public static void markResolved(int position) {
        if (position >= 0 && position < incidents.size()) {
            incidents.get(position).setResolved(true);
        }
    }
}
