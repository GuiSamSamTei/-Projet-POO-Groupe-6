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

    // Trouver les agents avec un revenu total supérieur à un certain montant
    List<Agent> findByRevenustotauxGreaterThan(double montant);

    // Trouver les agents par adresse
    List<Agent> findByAdreeseContainingIgnoreCase(String adresse);
}
