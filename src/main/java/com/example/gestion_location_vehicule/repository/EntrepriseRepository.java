package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {

    // Trouver toutes les entreprises actives
    List<Entreprise> findByActiveTrue();

    // Trouver toutes les entreprises inactives
    List<Entreprise> findByActiveFalse();

    // Trouver toutes les entreprises dans une ville donnée
    List<Entreprise> findByVille(String ville);

    // Recherche par raison sociale
    Entreprise findByRaisonSoc(String raisonSoc);

    // Recherche par email
    Entreprise findByEmail(String email);

    // Combiner ville et statut actif
    List<Entreprise> findByVilleAndActiveTrue(String ville);
}
