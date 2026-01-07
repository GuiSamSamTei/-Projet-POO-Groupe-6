package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.AgentPar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentParRepository extends JpaRepository<AgentPar, Long> {

    // Trouver un agent particulier par nom exact
    List<AgentPar> findByNom(String nom);

    // Trouver un agent particulier par prénom exact
    List<AgentPar> findByPrenom(String prenom);

    // Trouver tous les agents correspondant au nom et prénom exacts
    List<AgentPar> findAllByNomAndPrenom(String nom, String prenom);

}
