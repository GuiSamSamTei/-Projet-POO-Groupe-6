package com.example.gestion_location_vehicule.service.AgentProService;

import com.example.gestion_location_vehicule.model.AgentPro;

import java.util.List;
import java.util.Optional;

public interface IAgentProService {

    // CRUD
    List<AgentPro> getAllAgentPro();

    Optional<AgentPro> getAgentProById(Long id);

    AgentPro saveAgentPro(AgentPro agentPro);

    void deleteAgentPro(Long id);

    // Recherches spécifiques
    List<AgentPro> getByRaisonSociale(String raisonSociale);

    AgentPro getBySiret(String siret);
}
