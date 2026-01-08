package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.model.PrixAssurance;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.PrixAssuranceService.PrixAssuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prix-assurances")
public class PrixAssuranceController {

    private final PrixAssuranceService prixAssuranceService;

    public PrixAssuranceController(PrixAssuranceService prixAssuranceService) {
        this.prixAssuranceService = prixAssuranceService;
    }

    // 🔹 GET : tous les prix
    @GetMapping
    public List<PrixAssurance> getAll() {
        return prixAssuranceService.getAllPrixAssurances();
    }

    // 🔹 GET : par ID
    @GetMapping("/{id}")
    public Optional<PrixAssurance> getById(@PathVariable Long id) {
        return prixAssuranceService.getPrixAssuranceById(id);
    }

    // 🔹 POST : créer un prix
    @PostMapping
    public PrixAssurance create(@RequestBody PrixAssurance prixAssurance) {
        return prixAssuranceService.savePrixAssurance(prixAssurance);
    }

    // 🔹 PUT : mettre à jour un prix
    @PutMapping("/{id}")
    public PrixAssurance update(@PathVariable Long id, @RequestBody PrixAssurance prixAssurance) {
        prixAssurance.setId(id);
        return prixAssuranceService.savePrixAssurance(prixAssurance);
    }

    // 🔹 DELETE : supprimer un prix
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        prixAssuranceService.deletePrixAssurance(id);
    }

    // 🔹 GET : par véhicule
    @GetMapping("/vehicule/{vehiculeId}")
    public List<PrixAssurance> getByVehicule(@PathVariable Vehicule vehicule) {
        return prixAssuranceService.getByVehicule(vehicule);
    }

    // 🔹 GET : par assurance
    @GetMapping("/assurance/{assuranceId}")
    public List<PrixAssurance> getByAssurance(@PathVariable Assurance assurance) {
        return prixAssuranceService.getByAssurance(assurance);
    }

    // 🔹 GET : par véhicule + assurance
    @GetMapping("/vehicule/{vehiculeId}/assurance/{assuranceId}")
    public PrixAssurance getByVehiculeAndAssurance(@PathVariable Vehicule vehicule, @PathVariable Assurance assurance) {
        return prixAssuranceService.getByVehiculeAndAssurance(vehicule, assurance);
    }

    // 🔹 GET : prix min
    @GetMapping("/prix/min/{prix}")
    public List<PrixAssurance> getByPrixMin(@PathVariable double prix) {
        return prixAssuranceService.getByPrixMin(prix);
    }

    // 🔹 GET : prix max
    @GetMapping("/prix/max/{prix}")
    public List<PrixAssurance> getByPrixMax(@PathVariable double prix) {
        return prixAssuranceService.getByPrixMax(prix);
    }

    // 🔹 GET : prix entre min et max
    @GetMapping("/prix")
    public List<PrixAssurance> getByPrixBetween(@RequestParam double min, @RequestParam double max) {
        return prixAssuranceService.getByPrixBetween(min, max);
    }
}
