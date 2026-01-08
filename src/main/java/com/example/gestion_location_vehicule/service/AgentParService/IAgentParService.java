package com.example.gestion_location_vehicule.service.AgentParService;

import com.example.gestion_location_vehicule.model.AgentPar;

import java.util.List;
import java.util.Optional;

public interface IAgentParService {

    // CRUD
    List<AgentPar> getAllAgentPar();

    Optional<AgentPar> getAgentParById(Long id);

    AgentPar saveAgentPar(AgentPar agentPar);

    void deleteAgentPar(Long id);

    // Recherches spécifiques
    List<AgentPar> getByNom(String nom);

    List<AgentPar> getByPrenom(String prenom);

    List<AgentPar> getByNomAndPrenom(String nom, String prenom);
}
