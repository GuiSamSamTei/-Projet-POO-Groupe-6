package com.example.gestion_location_vehicule.service.AgentService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IAgentService {

    List<Agent> getAllAgents();

    Optional<Agent> getAgentById(Long id);

    Agent saveAgent(Agent agent);

    void deleteAgent(Long id);

    Agent getByIban(String iban);

    List<Agent> getAgentsWithMinRevenus(double montant);

    List<Agent> getAgentsWithMinVehicules(int minVehicules);

    List<Agent> searchByAdresse(String adresse);

    List<Vehicule> afficherVehiculeDispo();
}
