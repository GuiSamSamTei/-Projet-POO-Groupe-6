package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Loueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoueurRepository extends JpaRepository<Loueur, Long> {

    // Trouver par nom exact
    List<Loueur> findByNom(String nom);

    // Trouver par prénom exact
    List<Loueur> findByPrenom(String prenom);

    // Recherche partielle (nom ou prénom)
    List<Loueur> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(
            String nom,
            String prenom
    );

    // Nom + prénom exacts
    Loueur findByNomAndPrenom(String nom, String prenom);
    
    // Trouver par email (hérité de Utilisateur)
    java.util.Optional<Loueur> findByEmail(String email);

    // Trouver par username (hérité de Utilisateur)
    java.util.Optional<Loueur> findByUsername(String username);
}