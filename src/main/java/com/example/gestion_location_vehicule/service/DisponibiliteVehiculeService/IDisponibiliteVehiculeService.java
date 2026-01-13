package com.example.gestion_location_vehicule.service.DisponibiliteVehiculeService;

import com.example.gestion_location_vehicule.model.DisponibiliteVehicule;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.util.List;

public interface IDisponibiliteVehiculeService {

    DisponibiliteVehicule save(DisponibiliteVehicule disponibilite);

    List<DisponibiliteVehicule> getDisponibilitesPourVehicule(Vehicule vehicule);
}
