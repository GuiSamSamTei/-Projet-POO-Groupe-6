package com.example.gestion_location_vehicule.service.AgentService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.AgentRepository;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentService implements IAgentService {

    private final AgentRepository agentRepository;
    private final VehiculeRepository vehiculeRepository;

    public AgentService(AgentRepository agentRepository,
                        VehiculeRepository vehiculeRepository) {
        this.agentRepository = agentRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @Override
    public Optional<Agent> getAgentById(Long id) {
        return agentRepository.findById(id);
    }

    @Override
    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public void deleteAgent(Long id) {
        agentRepository.deleteById(id);
    }

    @Override
    public Agent getByIban(String iban) {
        return agentRepository.findByIban(iban);
    }

    @Override
    public List<Agent> getAgentsWithMinRevenus(double montant) {
        return agentRepository.findByRevenustotauxGreaterThan(montant);
    }

    @Override
    public List<Agent> getAgentsWithMinVehicules(int minVehicules) {
        return agentRepository.findByNombrevehiculesGreaterThan(minVehicules);
    }

    @Override
    public List<Agent> searchByAdresse(String adresse) {
        return agentRepository.findByAdreeseContainingIgnoreCase(adresse);
    }

    @Override
    public List<Vehicule> afficherVehiculeDispo() {
        return vehiculeRepository.findByVehiculedispoTrue();
    }
}
