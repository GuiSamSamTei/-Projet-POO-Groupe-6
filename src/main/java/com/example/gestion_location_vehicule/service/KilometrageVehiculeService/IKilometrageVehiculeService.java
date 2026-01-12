package com.example.gestion_location_vehicule.service.KilometrageVehiculeService;

import com.example.gestion_location_vehicule.model.KilometrageVehicule;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.util.List;

public interface IKilometrageVehiculeService {
    void enregistrerKilometrage(KilometrageVehicule kmVehicule);
    List<KilometrageVehicule> getKilometragesByVehicule(Vehicule vehicule);
}
