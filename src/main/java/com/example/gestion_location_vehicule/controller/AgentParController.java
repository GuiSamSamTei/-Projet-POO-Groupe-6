package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.service.AgentParService.AgentParService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/agents-particuliers")
public class AgentParController {

    private final AgentParService agentParService;

    public AgentParController(AgentParService agentParService) {
        this.agentParService = agentParService;
    }

    @GetMapping
    public List<AgentPar> getAll() {
        return agentParService.getAllAgentPar();
    }

    @GetMapping("/{id}")
    public Optional<AgentPar> getById(@PathVariable Long id) {
        return agentParService.getAgentParById(id);
    }

    @GetMapping("/nom/{nom}")
    public List<AgentPar> getByNom(@PathVariable String nom) {
        return agentParService.getByNom(nom);
    }

    @GetMapping("/prenom/{prenom}")
    public List<AgentPar> getByPrenom(@PathVariable String prenom) {
        return agentParService.getByPrenom(prenom);
    }

    @GetMapping("/search")
    public List<AgentPar> getByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        return agentParService.getByNomAndPrenom(nom, prenom);
    }

    @PostMapping
    public AgentPar create(@RequestBody AgentPar agentPar) {
        return agentParService.saveAgentPar(agentPar);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        agentParService.deleteAgentPar(id);
    }
}

