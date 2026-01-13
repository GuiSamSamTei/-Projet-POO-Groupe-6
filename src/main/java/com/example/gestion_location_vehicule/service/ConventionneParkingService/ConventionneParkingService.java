package com.example.gestion_location_vehicule.service.ConventionneParkingService;


import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.ConventionneParking;
import com.example.gestion_location_vehicule.model.Parking;
import com.example.gestion_location_vehicule.repository.ConventionneParkingRepository;
import com.example.gestion_location_vehicule.service.ParkingService.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@jakarta.transaction.Transactional

public class ConventionneParkingService implements IConventionneParkingService{

    private final ConventionneParkingRepository conventionneParkingRepository;
    private final ParkingService parkingService;

    @Override
    public ConventionneParking getById(Long id) {
        return conventionneParkingRepository.findById(id).get();
    }

    @Override
    public ConventionneParking getByAgentIdEtParkingID(Long agent_id, Long parking_id) {
        return conventionneParkingRepository.findByParkingIdAndAgentId(parking_id,agent_id);
    }

    @Override
    public List<ConventionneParking> getByParkingID(Long parking_id) {
        return conventionneParkingRepository.findByParkingId(parking_id);
    }

    @Override
    public List<ConventionneParking> getByAgentID(Long Agent_id) {
        return conventionneParkingRepository.findByAgentId(Agent_id);
    }

    @Override
    public void deleteAllConv() {
        conventionneParkingRepository.deleteAll();
    }

    @Override
    public void deleteByAgentId(Long agentID) {
        conventionneParkingRepository.deleteByAgent_Id(agentID);
    }


    public void updateAgentParkings(Agent agent, Long[] parkingIds) {

        if (parkingIds != null) {
            this.deleteByAgentId(agent.getId());
            for (Long id : parkingIds) {
                Parking parking = parkingService.trouverParkingparId(id);
                ConventionneParking conventionneParking = new ConventionneParking();

                conventionneParking.setAgent(agent);
                conventionneParking.setParking(parking);
                conventionneParkingRepository.save(conventionneParking);
            }
        }
    }

}
