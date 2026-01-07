package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {

    List<Entreprise> findByActiveTrue();

    List<Entreprise> findByVille(String ville);
}
