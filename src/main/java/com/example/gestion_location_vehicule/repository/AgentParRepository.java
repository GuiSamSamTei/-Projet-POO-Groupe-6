package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.AgentPar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentParRepository extends JpaRepository<AgentPar, Long> {
}
