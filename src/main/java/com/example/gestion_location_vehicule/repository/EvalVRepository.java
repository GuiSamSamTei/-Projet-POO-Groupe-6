package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.EvalV;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvalVRepository extends JpaRepository<EvalV, Long> {

    // Récupérer toutes les évaluations faites par un loueur
    List<EvalV> findByLoueur(Loueur loueur);

    // Récupérer toutes les évaluations d'un véhicule
    List<EvalV> findByVehicule(Vehicule vehicule);

    // Récupérer toutes les évaluations d'un véhicule faites par un loueur spécifique
    List<EvalV> findByVehiculeAndLoueur(Vehicule vehicule, Loueur loueur);

    // récupérer toutes les évaluations d'un véhicule avec note finale >= seuil
    List<EvalV> findByVehiculeAndNoteGreaterThanEqual(Vehicule vehicule, double noteMin);
}
