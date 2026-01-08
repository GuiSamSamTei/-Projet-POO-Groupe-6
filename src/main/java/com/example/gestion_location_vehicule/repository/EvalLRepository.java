package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.EvalL;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvalLRepository extends JpaRepository<EvalL, Long> {

    // Récupérer toutes les évaluations d'un loueur
    List<EvalL> findByLoueur(Loueur loueur);

    // Récupérer toutes les évaluations reçues par un agent
    List<EvalL> findByAgent(Agent agent);

    // Récupérer toutes les évaluations d'un loueur pour un agent spécifique
    List<EvalL> findByLoueurAndAgent(Loueur loueur, Agent agent);

    // Optionnel : récupérer toutes les évaluations avec une note finale >= un certain seuil
    List<EvalL> findByNoteGreaterThanEqual(double noteMin);
}
