package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    List<Voiture> findByVehiculeDispoTrue();

    List<Voiture> findByVilleDispo(String ville);

    List<Voiture> findByVilleDispoAndVehiculeDispoTrue(String ville);
}
