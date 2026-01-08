package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Evaluation;
import com.example.gestion_location_vehicule.service.EvaluationService.EvaluationService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // 🔹 GET : toutes les évaluations
    @GetMapping
    public List<Evaluation> getAll() {
        return evaluationService.getAllEvaluations();
    }

    // 🔹 GET : évaluation par ID
    @GetMapping("/{id}")
    public Optional<Evaluation> getById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    // 🔹 POST : créer une évaluation
    @PostMapping
    public Evaluation create(@RequestBody Evaluation evaluation) {
        return evaluationService.saveEvaluation(evaluation);
    }

    // 🔹 PUT : mettre à jour une évaluation
    @PutMapping("/{id}")
    public Evaluation update(@PathVariable Long id, @RequestBody Evaluation evaluation) {
        evaluation.setId(id);
        return evaluationService.saveEvaluation(evaluation);
    }

    // 🔹 DELETE : supprimer une évaluation
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
    }

    // 🔹 GET : recherche par note min
    @GetMapping("/note/min/{noteMin}")
    public List<Evaluation> getByNoteMin(@PathVariable double noteMin) {
        return evaluationService.getByNoteMin(noteMin);
    }

    // 🔹 GET : recherche par note max
    @GetMapping("/note/max/{noteMax}")
    public List<Evaluation> getByNoteMax(@PathVariable double noteMax) {
        return evaluationService.getByNoteMax(noteMax);
    }

    // 🔹 GET : recherche par note entre min et max
    @GetMapping("/note")
    public List<Evaluation> getByNoteBetween(@RequestParam double min, @RequestParam double max) {
        return evaluationService.getByNoteBetween(min, max);
    }

    // 🔹 GET : recherche par date
    @GetMapping("/date")
    public List<Evaluation> getByDate(@RequestParam Date date) {
        return evaluationService.getByDate(date);
    }

    // 🔹 GET : recherche par plage de dates
    @GetMapping("/dates")
    public List<Evaluation> getByDateBetween(@RequestParam Date start, @RequestParam Date end) {
        return evaluationService.getByDateBetween(start, end);
    }

    // 🔹 GET : toutes les évaluations triées par note descendante
    @GetMapping("/tri/note")
    public List<Evaluation> getAllOrderByNoteDesc() {
        return evaluationService.getAllOrderByNoteDesc();
    }

    // 🔹 GET : toutes les évaluations triées par date descendante
    @GetMapping("/tri/date")
    public List<Evaluation> getAllOrderByDateDesc() {
        return evaluationService.getAllOrderByDateDesc();
    }
}
