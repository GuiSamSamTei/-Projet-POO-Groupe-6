package com.example.gestion_location_vehicule.service.AgentService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.AgentRepository;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;

import java.util.List;

public class AgentService implements IAgentService{

    VehiculeRepository vehiculeRepository;

    @Override
    public List<Vehicule> afficherVehiculeDispo() {



        return vehiculeRepository.findByVehiculedispoTrue();
    }
}
