package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Entreprise;
import com.example.gestion_location_vehicule.service.EntrepriseService.EntrepriseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    // 🔹 GET : toutes les entreprises
    @GetMapping
    public List<Entreprise> getAll() {
        return entrepriseService.getAllEntreprises();
    }

    // 🔹 GET : entreprise par NSIRET
    @GetMapping("/{nsiret}")
    public Optional<Entreprise> getById(@PathVariable String nsiret) {
        return entrepriseService.getEntrepriseById(nsiret);
    }

    // 🔹 GET : entreprises actives
    @GetMapping("/actives")
    public List<Entreprise> getActive() {
        return entrepriseService.getActiveEntreprises();
    }

    // 🔹 GET : entreprises inactives
    @GetMapping("/inactives")
    public List<Entreprise> getInactive() {
        return entrepriseService.getInactiveEntreprises();
    }

    // 🔹 GET : recherche par ville
    @GetMapping("/ville/{ville}")
    public List<Entreprise> getByVille(@PathVariable String ville) {
        return entrepriseService.getEntreprisesByVille(ville);
    }

    // 🔹 GET : recherche par raison sociale
    @GetMapping("/raison-soc/{raisonSoc}")
    public Entreprise getByRaisonSoc(@PathVariable String raisonSoc) {
        return entrepriseService.getByRaisonSoc(raisonSoc);
    }

    // 🔹 GET : recherche par email
    @GetMapping("/email/{email}")
    public Entreprise getByEmail(@PathVariable String email) {
        return entrepriseService.getByEmail(email);
    }

    // 🔹 GET : entreprises actives dans une ville
    @GetMapping("/ville/{ville}/actives")
    public List<Entreprise> getActiveByVille(@PathVariable String ville) {
        return entrepriseService.getActiveEntreprisesByVille(ville);
    }

    // 🔹 POST : créer une entreprise
    @PostMapping
    public Entreprise create(@RequestBody Entreprise entreprise) {
        return entrepriseService.saveEntreprise(entreprise);
    }

    // 🔹 PUT : mettre à jour une entreprise
    @PutMapping("/{nsiret}")
    public Entreprise update(@PathVariable String nsiret, @RequestBody Entreprise entreprise) {
        entreprise.setNsiret(nsiret);
        return entrepriseService.saveEntreprise(entreprise);
    }

    // 🔹 DELETE : supprimer une entreprise
    @DeleteMapping("/{nsiret}")
    public void delete(@PathVariable String nsiret) {
        entrepriseService.deleteEntreprise(nsiret);
    }
}
