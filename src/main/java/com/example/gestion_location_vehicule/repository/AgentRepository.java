package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
