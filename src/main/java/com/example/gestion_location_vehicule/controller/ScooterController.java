package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Scooter;
import com.example.gestion_location_vehicule.service.ScooterService.ScooterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scooters")
public class ScooterController {

    private final ScooterService scooterService;

    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
    }

    // 🔹 GET : tous les scooters
    @GetMapping
    public List<Scooter> getAll() {
        return scooterService.getAllScooters();
    }

    // 🔹 GET : par ID
    @GetMapping("/{id}")
    public Optional<Scooter> getById(@PathVariable Long id) {
        return scooterService.getScooterById(id);
    }

    // 🔹 POST : créer un scooter
    @PostMapping
    public Scooter create(@RequestBody Scooter scooter) {
        return scooterService.saveScooter(scooter);
    }

    // 🔹 PUT : mettre à jour un scooter
    @PutMapping("/{id}")
    public Scooter update(@PathVariable Long id, @RequestBody Scooter scooter) {
        scooter.setId(id);
        return scooterService.saveScooter(scooter);
    }

    // 🔹 DELETE : supprimer un scooter
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scooterService.deleteScooter(id);
    }

    // 🔹 GET : scooters disponibles
    @GetMapping("/disponibles")
    public List<Scooter> getAvailable() {
        return scooterService.getAvailableScooters();
    }

    // 🔹 GET : par ville
    @GetMapping("/ville/{ville}")
    public List<Scooter> getByVille(@PathVariable String ville) {
        return scooterService.getByVille(ville);
    }

    // 🔹 GET : par cylindrée min
    @GetMapping("/cylindree/min/{min}")
    public List<Scooter> getByCylindreeMin(@PathVariable int min) {
        return scooterService.getByCylindreeMin(min);
    }

    // 🔹 GET : par cylindrée max
    @GetMapping("/cylindree/max/{max}")
    public List<Scooter> getByCylindreeMax(@PathVariable int max) {
        return scooterService.getByCylindreeMax(max);
    }

    // 🔹 GET : par cylindrée entre min et max
    @GetMapping("/cylindree")
    public List<Scooter> getByCylindreeBetween(@RequestParam int min, @RequestParam int max) {
        return scooterService.getByCylindreeBetween(min, max);
    }

    // 🔹 GET : électriques
    @GetMapping("/electriques")
    public List<Scooter> getElectrique() {
        return scooterService.getElectrique();
    }

    // 🔹 GET : non électriques
    @GetMapping("/non-electriques")
    public List<Scooter> getNonElectrique() {
        return scooterService.getNonElectrique();
    }

    // 🔹 GET : par ville + cylindrée min
    @GetMapping("/ville/{ville}/cylindree/{min}")
    public List<Scooter> getByVilleAndCylindree(@PathVariable String ville, @PathVariable int min) {
        return scooterService.getByVilleAndCylindreeMin(ville, min);
    }

    // 🔹 GET : par ville + électriques
    @GetMapping("/ville/{ville}/electriques")
    public List<Scooter> getByVilleAndElectrique(@PathVariable String ville) {
        return scooterService.getByVilleAndElectrique(ville);
    }
}
