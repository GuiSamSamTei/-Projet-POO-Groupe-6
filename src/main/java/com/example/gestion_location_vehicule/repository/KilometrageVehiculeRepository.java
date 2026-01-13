package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.KilometrageVehicule;
import com.example.gestion_location_vehicule.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KilometrageVehiculeRepository extends JpaRepository<KilometrageVehicule, Long> {
    List<KilometrageVehicule> findByVehicule(Vehicule vehicule);
}
