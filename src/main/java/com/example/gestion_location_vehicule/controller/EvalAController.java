package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.EvalA;
import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.service.EvalAService.EvalAService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations-agent")
public class EvalAController {

    private final EvalAService evalAService;

    public EvalAController(EvalAService evalAService) {
        this.evalAService = evalAService;
    }

    // 🔹 GET : toutes les évaluations
    @GetMapping
    public List<EvalA> getAll() {
        return evalAService.getAllEvalA();
    }

    // 🔹 GET : évaluation par ID
    @GetMapping("/{id}")
    public Optional<EvalA> getById(@PathVariable Long id) {
        return evalAService.getEvalAById(id);
    }

    // 🔹 POST : créer une évaluation
    @PostMapping
    public EvalA create(@RequestBody EvalA evalA) {
        return evalAService.saveEvalA(evalA);
    }

    // 🔹 PUT : mettre à jour une évaluation
    @PutMapping("/{id}")
    public EvalA update(@PathVariable Long id, @RequestBody EvalA evalA) {
        evalA.setId(id);
        return evalAService.saveEvalA(evalA);
    }

    // 🔹 DELETE : supprimer une évaluation
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        evalAService.deleteEvalA(id);
    }

    // 🔹 GET : toutes les évaluations pour un agent
    @GetMapping("/agent/{agentId}")
    public List<EvalA> getByAgent(@PathVariable Long agentId) {
        Agent agent = new Agent();
        return evalAService.getByAgent(agent);
    }

    // 🔹 GET : toutes les évaluations pour un loueur
    @GetMapping("/loueur/{loueurId}")
    public List<EvalA> getByLoueur(@PathVariable Long loueurId) {
        Loueur loueur = new Loueur();
        return evalAService.getByLoueur(loueur);
    }
}
