package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Contratlocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ContratlocationRepository extends JpaRepository<Contratlocation, Long> {

    List<Contratlocation> findByDatedebut(Date datedebut);

    List<Contratlocation> findByDatefin(Date datefin);

    // Méthode utilisée par le controller AgentPro
    List<Contratlocation> findByVehicule_Id(Long vehicule_id);

    Contratlocation findByAssuranceId(Long assurance_id);

    List<Contratlocation> findByLoueur_Id(Long loueur_id);

    List<Contratlocation> findByValideeIsFalseAndVehicule_Agent_Id(Long agentId);
    
    // Compter le nombre de locations d'un loueur
    long countByLoueur_Id(Long loueur_id);

}
