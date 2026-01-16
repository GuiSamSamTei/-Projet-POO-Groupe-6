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

    @GetMapping
    public List<Tarification> getAll() {
        return tarificationService.getAllTarifications();
    }

    @GetMapping("/{id}")
    public Optional<Tarification> getById(@PathVariable Long id) {
        return tarificationService.getTarificationById(id);
    }

    @PostMapping
    public Tarification create(@RequestBody Tarification tarification) {
        return tarificationService.saveTarification(tarification);
    }

    @PutMapping("/{id}")
    public Tarification update(@PathVariable Long id, @RequestBody Tarification tarification) {
        tarification.setId(id);
        return tarificationService.saveTarification(tarification);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tarificationService.deleteTarification(id);
    }

    @GetMapping("/annee/{annee}")
    public Optional<Tarification> getByAnnee(@PathVariable long annee) {
        return tarificationService.getByAnnee(annee);
    }

    @GetMapping("/prix/min/{prix}")
    public List<Tarification> getByPrixMin(@PathVariable double prix) {
        return tarificationService.getByPrixfixeMin(prix);
    }

    @GetMapping("/prix/max/{prix}")
    public List<Tarification> getByPrixMax(@PathVariable double prix) {
        return tarificationService.getByPrixfixeMax(prix);
    }

    @GetMapping("/pourcentage/min/{pourcentage}")
    public List<Tarification> getByPourcentageMin(@PathVariable double pourcentage) {
        return tarificationService.getByPourcentageMin(pourcentage);
    }

    @GetMapping("/pourcentage/max/{pourcentage}")
    public List<Tarification> getByPourcentageMax(@PathVariable double pourcentage) {
        return tarificationService.getByPourcentageMax(pourcentage);
    }

    @GetMapping("/prix-pourcentage")
    public List<Tarification> getByPrixEtPourcentage(
            @RequestParam double prixMax,
            @RequestParam double pourcentageMax
    ) {
        return tarificationService.getByPrixfixeAndPourcentageMax(prixMax, pourcentageMax);
    }
}
