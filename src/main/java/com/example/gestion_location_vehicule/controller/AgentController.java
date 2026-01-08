package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.service.AgentService.AgentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agents")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    // 🔹 GET /agents
    @GetMapping
    public List<Agent> getAllAgents() {
        return agentService.getAllAgents();
    }

    // 🔹 GET /agents/{id}
    @GetMapping("/{id}")
    public Optional<Agent> getAgentById(@PathVariable Long id) {
        return agentService.getAgentById(id);
    }

    // 🔹 POST /agents
    @PostMapping
    public Agent createAgent(@RequestBody Agent agent) {
        return agentService.saveAgent(agent);
    }

    // 🔹 PUT /agents/{id}
    @PutMapping("/{id}")
    public Agent updateAgent(@PathVariable Long id, @RequestBody Agent agent) {
        agent.setId(id);
        return agentService.saveAgent(agent);
    }

    // 🔹 DELETE /agents/{id}
    @DeleteMapping("/{id}")
    public void deleteAgent(@PathVariable Long id) {
        agentService.deleteAgent(id);
    }

    // 🔹 GET /agents/iban/{iban}
    @GetMapping("/iban/{iban}")
    public Agent getByIban(@PathVariable String iban) {
        return agentService.getByIban(iban);
    }

    // 🔹 GET /agents/revenus/{montant}
    @GetMapping("/revenus/{montant}")
    public List<Agent> getByRevenus(@PathVariable double montant) {
        return agentService.getAgentsWithMinRevenus(montant);
    }

    // 🔹 GET /agents/vehicules/{min}
    @GetMapping("/vehicules/{min}")
    public List<Agent> getByMinVehicules(@PathVariable int min) {
        return agentService.getAgentsWithMinVehicules(min);
    }

    // 🔹 GET /agents/search?adresse=paris
    @GetMapping("/search")
    public List<Agent> searchByAdresse(@RequestParam String adresse) {
        return agentService.searchByAdresse(adresse);
    }
}
