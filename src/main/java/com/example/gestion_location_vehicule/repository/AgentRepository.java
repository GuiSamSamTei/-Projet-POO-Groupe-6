package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

    // Trouver un agent par son IBAN
    Agent findByIban(String iban);

    // Trouver tous les agents actifs (tu pourrais ajouter un boolean 'active' si besoin)
    List<Agent> findByNombrevehiculesGreaterThan(int minVehicules);

    // Trouver les agents par adresse
    List<Agent> findByAdresseContainingIgnoreCase(String adresse);

    List<Agent> findByRevenustotauxGreaterThan(double montant);
}
