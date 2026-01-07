package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.EvalA;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvalARepository extends JpaRepository<EvalA, Long> {

    // Récupérer toutes les évaluations d'un agent
    List<EvalA> findByAgent(Agent agent);

    // Récupérer toutes les évaluations d'un loueur
    List<EvalA> findByLoueur(Loueur loueur);

}
