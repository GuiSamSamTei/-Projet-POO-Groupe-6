package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.EvalL;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.service.EvalLService.EvalLService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations-loueur")
public class EvalLController {

    private final EvalLService evalLService;

    public EvalLController(EvalLService evalLService) {
        this.evalLService = evalLService;
    }

    // 🔹 GET : toutes les évaluations
    @GetMapping
    public List<EvalL> getAll() {
        return evalLService.getAllEvalL();
    }

    // 🔹 GET : évaluation par ID
    @GetMapping("/{id}")
    public Optional<EvalL> getById(@PathVariable Long id) {
        return evalLService.getEvalLById(id);
    }

    // 🔹 POST : créer une évaluation
    @PostMapping
    public EvalL create(@RequestBody EvalL evalL) {
        return evalLService.saveEvalL(evalL);
    }

    // 🔹 PUT : mettre à jour une évaluation
    @PutMapping("/{id}")
    public EvalL update(@PathVariable Long id, @RequestBody EvalL evalL) {
        evalL.setId(id);
        return evalLService.saveEvalL(evalL);
    }

    // 🔹 DELETE : supprimer une évaluation
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        evalLService.deleteEvalL(id);
    }

    // 🔹 GET : toutes les évaluations pour un loueur
    @GetMapping("/loueur/{loueurId}")
    public List<EvalL> getByLoueur(@PathVariable Long loueurId) {
        Loueur loueur = new Loueur();
        return evalLService.getByLoueur(loueur);
    }

    // 🔹 GET : toutes les évaluations pour un agent
    @GetMapping("/agent/{agentId}")
    public List<EvalL> getByAgent(@PathVariable Long agentId) {
        Agent agent = new Agent();
        return evalLService.getByAgent(agent);
    }

    // 🔹 GET : toutes les évaluations pour un loueur et un agent
    @GetMapping("/loueur/{loueurId}/agent/{agentId}")
    public List<EvalL> getByLoueurAndAgent(
            @PathVariable Long loueurId,
            @PathVariable Long agentId
    ) {
        Loueur loueur = new Loueur();
        Agent agent = new Agent();
        return evalLService.getByLoueurAndAgent(loueur, agent);
    }

    // 🔹 GET : toutes les évaluations avec note >= noteMin
    @GetMapping("/note-min/{noteMin}")
    public List<EvalL> getByNoteMin(@PathVariable double noteMin) {
        return evalLService.getByNoteMin(noteMin);
    }
}
