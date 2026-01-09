package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Long> {

    // Récupérer uniquement les assurances actives
    List<Assurance> findByActiveTrue();

    // Récupérer l'assurance par défaut (AZA)
    Assurance findByAssurancepardefautTrue();

    Assurance findByNom(String nom);
}
