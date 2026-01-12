package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.ControleTechnique;
import com.example.gestion_location_vehicule.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ControleTechniqueRepository extends JpaRepository<ControleTechnique, Long> {

    // Récupérer le contrôle technique d’un véhicule
    Optional<ControleTechnique> findByVehicule(Vehicule vehicule);
}
