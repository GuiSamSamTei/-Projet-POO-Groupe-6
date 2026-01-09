package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.service.AgentProService.AgentProService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/agents-professionnels")
public class AgentProController {

    private final AgentProService agentProService;

    public AgentProController(AgentProService agentProService) {
        this.agentProService = agentProService;
    }

    // GET : tous les agents professionnels
    @GetMapping
    public List<AgentPro> getAll() {
        return agentProService.getAllAgentPro(); // corrigé
    }

    // GET : recherche par raison sociale
    @GetMapping("/raison-sociale/{raisonSociale}")
    public List<AgentPro> getByRaisonSociale(@PathVariable String raisonSociale) {
        return agentProService.getByRaisonSociale(raisonSociale); // corrigé
    }

    // GET : recherche par SIRET
    @GetMapping("/siret/{siret}")
    public AgentPro getBySiret(@PathVariable String siret) {
        return agentProService.getBySiret(siret); // corrigé
    }

    // POST : créer un agent professionnel
    @PostMapping
    public AgentPro create(@RequestBody AgentPro agentPro) {
        return agentProService.saveAgentPro(agentPro);
    }

    // DELETE : supprimer un agent professionnel
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        agentProService.deleteAgentPro(id);
    }


    @PostMapping("/list")
    public ResponseEntity<?> ajouterListAgentPro(@RequestBody List<AgentPro> agentProList)
    {
        try{
            agentProService.ajouterListAgentPro(agentProList);
            return ResponseEntity.ok("La liste a ete bien ajoutés");
        } catch (Exception e)
        {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
