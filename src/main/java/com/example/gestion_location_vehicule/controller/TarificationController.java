package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Tarification;
import com.example.gestion_location_vehicule.service.TarificationService.TarificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarifications")
public class TarificationController {

    private final TarificationService tarificationService;

    public TarificationController(TarificationService tarificationService) {
        this.tarificationService = tarificationService;
    }

    // 🔹 GET : toutes les tarifications
    @GetMapping
    public List<Tarification> getAll() {
        return tarificationService.getAllTarifications();
    }

    // 🔹 GET : par ID
    @GetMapping("/{id}")
    public Optional<Tarification> getById(@PathVariable Long id) {
        return tarificationService.getTarificationById(id);
    }

    // 🔹 POST : créer une tarification
    @PostMapping
    public Tarification create(@RequestBody Tarification tarification) {
        return tarificationService.saveTarification(tarification);
    }

    // 🔹 PUT : mettre à jour une tarification
    @PutMapping("/{id}")
    public Tarification update(@PathVariable Long id, @RequestBody Tarification tarification) {
        tarification.setId(id);
        return tarificationService.saveTarification(tarification);
    }

    // 🔹 DELETE : supprimer une tarification
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tarificationService.deleteTarification(id);
    }

    // 🔹 GET : par année
    @GetMapping("/annee/{annee}")
    public Optional<Tarification> getByAnnee(@PathVariable long annee) {
        return tarificationService.getByAnnee(annee);
    }

    // 🔹 GET : prix fixe minimum
    @GetMapping("/prix/min/{prix}")
    public List<Tarification> getByPrixMin(@PathVariable double prix) {
        return tarificationService.getByPrixfixeMin(prix);
    }

    // 🔹 GET : prix fixe maximum
    @GetMapping("/prix/max/{prix}")
    public List<Tarification> getByPrixMax(@PathVariable double prix) {
        return tarificationService.getByPrixfixeMax(prix);
    }

    // 🔹 GET : pourcentage minimum
    @GetMapping("/pourcentage/min/{pourcentage}")
    public List<Tarification> getByPourcentageMin(@PathVariable double pourcentage) {
        return tarificationService.getByPourcentageMin(pourcentage);
    }

    // 🔹 GET : pourcentage maximum
    @GetMapping("/pourcentage/max/{pourcentage}")
    public List<Tarification> getByPourcentageMax(@PathVariable double pourcentage) {
        return tarificationService.getByPourcentageMax(pourcentage);
    }

    // 🔹 GET : combinaison prix fixe et pourcentage max
    @GetMapping("/prix-pourcentage")
    public List<Tarification> getByPrixEtPourcentage(
            @RequestParam double prixMax,
            @RequestParam double pourcentageMax
    ) {
        return tarificationService.getByPrixfixeAndPourcentageMax(prixMax, pourcentageMax);
    }
}
