package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.EntretienTechnique;
import com.example.gestion_location_vehicule.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntretienTechniqueRepository extends JpaRepository<EntretienTechnique, Long> {

    // Récupère tous les entretiens pour un véhicule
    List<EntretienTechnique> findByVehicule(Vehicule vehicule);
}
