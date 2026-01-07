package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Critere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CritereRepository extends JpaRepository<Critere, Long> {

    // Tu peux ajouter des méthodes utiles, par exemple :
    // Critere findByNom(String nom);
}
