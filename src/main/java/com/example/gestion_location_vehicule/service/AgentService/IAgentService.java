package com.example.gestion_location_vehicule.service.AgentService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.util.List;

public interface IAgentService {

    List<Vehicule> afficherVehiculeDispo();
}
