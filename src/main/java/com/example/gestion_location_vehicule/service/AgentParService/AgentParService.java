package com.example.gestion_location_vehicule.service.AgentParService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.repository.AgentParRepository;

@Service
public class AgentParService implements IAgentParService {

    private final AgentParRepository agentParRepository;

    public AgentParService(AgentParRepository agentParRepository) {
        this.agentParRepository = agentParRepository;
    }


    @Override
    public List<AgentPar> getAllAgentPar() {
        return agentParRepository.findAll();
    }

    @Override
    public Optional<AgentPar> getAgentParById(Long id) {
        return agentParRepository.findById(id);
    }

    @Override
    public AgentPar saveAgentPar(AgentPar agentPar) {
        return agentParRepository.save(agentPar);
    }

    @Override
    public void deleteAgentPar(Long id) {
        agentParRepository.deleteById(id);
    }


    @Override
    public List<AgentPar> getByNom(String nom) {
        return agentParRepository.findByNom(nom);
    }

    @Override
    public List<AgentPar> getByPrenom(String prenom) {
        return agentParRepository.findByPrenom(prenom);
    }

    @Override
    public List<AgentPar> getByNomAndPrenom(String nom, String prenom) {
        return agentParRepository.findAllByNomAndPrenom(nom, prenom);
    }

    public void ajouterListAgentPar(List<AgentPar> agentParList)
    {
       agentParRepository.saveAll(agentParList);
    }


}
