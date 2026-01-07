package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.model.PrixAssurance;
import com.example.gestion_location_vehicule.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrixAssuranceRepository extends JpaRepository<PrixAssurance, Long> {

    // Récupérer tous les prix pour un véhicule donné
    List<PrixAssurance> findByVehicule(Vehicule vehicule);

    // Récupérer tous les prix pour une assurance donnée
    List<PrixAssurance> findByAssurance(Assurance assurance);

    // Récupérer un prix pour un véhicule et une assurance spécifiques
    PrixAssurance findByVehiculeAndAssurance(Vehicule vehicule, Assurance assurance);

    // Filtrer par prix minimum ou maximum
    List<PrixAssurance> findByPrixGreaterThanEqual(double prixMin);
    List<PrixAssurance> findByPrixLessThanEqual(double prixMax);

    // Filtrer par intervalle de prix
    List<PrixAssurance> findByPrixBetween(double min, double max);

    // Combiner véhicule + prix
    List<PrixAssurance> findByVehiculeAndPrixLessThanEqual(Vehicule vehicule, double prixMax);

    // Combiner assurance + prix
    List<PrixAssurance> findByAssuranceAndPrixLessThanEqual(Assurance assurance, double prixMax);
}
