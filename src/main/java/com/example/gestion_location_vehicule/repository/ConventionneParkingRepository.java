package com.example.gestion_location_vehicule.repository;


import com.example.gestion_location_vehicule.model.ConventionneParking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConventionneParkingRepository extends JpaRepository<ConventionneParking, Long> {

    List<ConventionneParking> findByAgentId(Long agent_id);
    List<ConventionneParking> findByParkingId(Long parking_id);
    ConventionneParking findByParkingIdAndAgentId(Long parking_id,Long AgentId);
    void deleteByAgent_Id(Long agentId);
}
