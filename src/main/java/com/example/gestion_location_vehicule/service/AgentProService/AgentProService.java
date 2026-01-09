package com.example.gestion_location_vehicule.service.AgentProService;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.repository.AgentProRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentProService implements IAgentProService {

    private final AgentProRepository agentProRepository;

    public AgentProService(AgentProRepository agentProRepository) {
        this.agentProRepository = agentProRepository;
    }

    // 🔹 CRUD

    @Override
    public List<AgentPro> getAllAgentPro() {
        return agentProRepository.findAll();
    }

    @Override
    public Optional<AgentPro> getAgentProById(Long id) {
        return agentProRepository.findById(id);
    }

    @Override
    public AgentPro saveAgentPro(AgentPro agentPro) {
        return agentProRepository.save(agentPro);
    }

    @Override
    public void deleteAgentPro(Long id) {
        agentProRepository.deleteById(id);
    }

    // 🔹 Recherches spécifiques

    @Override
    public List<AgentPro> getByRaisonSociale(String raisonSociale) {
        return agentProRepository.findByRaisonsociale(raisonSociale);
    }

    @Override
    public AgentPro getBySiret(String siret) {
        return agentProRepository.findBySiret(siret);
    }


    public void ajouterListAgentPro(List<AgentPro> agentProList)
    {
        agentProRepository.saveAll(agentProList);
    }
}
