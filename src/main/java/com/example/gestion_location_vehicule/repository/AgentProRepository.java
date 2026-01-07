package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.AgentPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentProRepository extends JpaRepository<AgentPro, Long> {

    // Trouver un agent professionnel par raison sociale exacte
    List<AgentPro> findByRaisonsociale(String raisonSociale);

    // Filtrer par SIRET exact
    AgentPro findBySiret(String siret);

}
