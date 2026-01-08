package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Facture;
import com.example.gestion_location_vehicule.service.FactureService.FactureService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/factures")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    // 🔹 GET : toutes les factures
    @GetMapping
    public List<Facture> getAll() {
        return factureService.getAllFactures();
    }

    // 🔹 GET : facture par ID
    @GetMapping("/{id}")
    public Optional<Facture> getById(@PathVariable Long id) {
        return factureService.getFactureById(id);
    }

    // 🔹 POST : créer une facture
    @PostMapping
    public Facture create(@RequestBody Facture facture) {
        return factureService.saveFacture(facture);
    }

    // 🔹 PUT : mettre à jour une facture
    @PutMapping("/{id}")
    public Facture update(@PathVariable Long id, @RequestBody Facture facture) {
        facture.setId(id);
        return factureService.saveFacture(facture);
    }

    // 🔹 DELETE : supprimer une facture
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        factureService.deleteFacture(id);
    }

    // 🔹 GET : factures payées
    @GetMapping("/payees")
    public List<Facture> getPayees() {
        return factureService.getFacturesPayees();
    }

    // 🔹 GET : factures non payées
    @GetMapping("/non-payees")
    public List<Facture> getNonPayees() {
        return factureService.getFacturesNonPayees();
    }

    // 🔹 GET : factures par date
    @GetMapping("/dates")
    public List<Facture> getByDateBetween(
            @RequestParam LocalDate debut,
            @RequestParam LocalDate fin
    ) {
        return factureService.getFacturesByDateBetween(debut, fin);
    }

    // 🔹 GET : factures par montant min
    @GetMapping("/montant/min/{montant}")
    public List<Facture> getByMontantMin(@PathVariable double montant) {
        return factureService.getFacturesByMontantMin(montant);
    }

    // 🔹 GET : factures par montant max
    @GetMapping("/montant/max/{montant}")
    public List<Facture> getByMontantMax(@PathVariable double montant) {
        return factureService.getFacturesByMontantMax(montant);
    }

    // 🔹 GET : factures par montant entre min et max
    @GetMapping("/montant")
    public List<Facture> getByMontantBetween(
            @RequestParam double min,
            @RequestParam double max
    ) {
        return factureService.getFacturesByMontantBetween(min, max);
    }

    // 🔹 GET : factures triées par date décroissante
    @GetMapping("/order/date")
    public List<Facture> getOrderByDate() {
        return factureService.getFacturesOrderByDateDesc();
    }

    // 🔹 GET : factures triées par montant décroissant
    @GetMapping("/order/montant")
    public List<Facture> getOrderByMontant() {
        return factureService.getFacturesOrderByMontantDesc();
    }
}
