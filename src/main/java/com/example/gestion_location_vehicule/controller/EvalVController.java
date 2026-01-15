package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.EvalV;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.EvalVService.EvalVService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/evalv")
public class EvalVController {

    private final EvalVService evalVService;
    private final VehiculeService vehiculeService;

    public EvalVController(EvalVService evalVService, VehiculeService vehiculeService) {
        this.evalVService = evalVService;
        this.vehiculeService = vehiculeService;
    }

    // 🔹 GET : toutes les évaluations
    @GetMapping
    public List<EvalV> getAll() {
        return evalVService.getAllEvalV();
    }

    // 🔹 GET : évaluation par ID
    @GetMapping("/{id}")
    public Optional<EvalV> getById(@PathVariable Long id) {
        return evalVService.getEvalVById(id);
    }

    // 🔹 POST : créer une évaluation
    @PostMapping
    public EvalV create(@RequestBody EvalV evalV) {
        return evalVService.saveEvalV(evalV);
    }

    // 🔹 PUT : mettre à jour une évaluation
    @PutMapping("/{id}")
    public EvalV update(@PathVariable Long id, @RequestBody EvalV evalV) {
        evalV.setId(id);
        return evalVService.saveEvalV(evalV);
    }

    // 🔹 DELETE : supprimer une évaluation
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        evalVService.deleteEvalV(id);
    }

    // 🔹 GET : toutes les évaluations pour un loueur
    @GetMapping("/loueur/{loueurId}")
    public List<EvalV> getByLoueur(@PathVariable Long loueurId) {
        Loueur loueur = new Loueur();
        return evalVService.getByLoueur(loueur);
    }

    // 🔹 GET : toutes les évaluations pour un véhicule
    @GetMapping("/vehicule/{vehiculeId}")
    public List<EvalV> getByVehicule(@PathVariable Long vehiculeId) {
        Vehicule vehicule = new Vehicule();
        vehicule.setId(vehiculeId);
        return evalVService.getByVehicule(vehicule);
    }

    // 🔹 GET : toutes les évaluations pour un véhicule et un loueur
    @GetMapping("/vehicule/{vehiculeId}/loueur/{loueurId}")
    public List<EvalV> getByVehiculeAndLoueur(
            @PathVariable Long vehiculeId,
            @PathVariable Long loueurId
    ) {
        Vehicule vehicule = new Vehicule();
        vehicule.setId(vehiculeId);
        Loueur loueur = new Loueur();
        return evalVService.getByVehiculeAndLoueur(vehicule, loueur);
    }

    // 🔹 GET : toutes les évaluations d'un véhicule avec note >= noteMin
    @GetMapping("/vehicule/{vehiculeId}/note-min/{noteMin}")
    public List<EvalV> getByVehiculeAndNoteMin(
            @PathVariable Long vehiculeId,
            @PathVariable double noteMin
    ) {
        Vehicule vehicule = new Vehicule();
        vehicule.setId(vehiculeId);
        return evalVService.getByVehiculeAndNoteMin(vehicule, noteMin);
    }


    @GetMapping("/vehicule/{id}")
    public List<EvalV> getAvisVehicule(@PathVariable Long id) {
        // Cette méthode doit retourner la liste des avis liés au véhicule

        Vehicule vehicule = vehiculeService.getVehiculeByid(id);
        return evalVService.getByVehicule(vehicule);
    }
}
