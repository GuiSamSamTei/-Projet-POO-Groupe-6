package com.example.gestion_location_vehicule.service.ConventionneParkingService;

import com.example.gestion_location_vehicule.model.ConventionneParking;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IConventionneParkingService {

    ConventionneParking getById(Long id);
    ConventionneParking getByAgentIdEtParkingID(Long agent_id, Long parking_id);

    List<ConventionneParking> getByParkingID(Long parking_id);
    List<ConventionneParking> getByAgentID(Long Agent_id);
    void deleteAllConv();
    void deleteByAgentId( Long agentID);
}
