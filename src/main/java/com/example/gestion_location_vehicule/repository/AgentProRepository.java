package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.AgentPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentProRepository extends JpaRepository<AgentPro, Long> {
}
