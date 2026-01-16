package com.example.gestion_location_vehicule.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_location_vehicule.model.Entreprise;
import com.example.gestion_location_vehicule.service.EntrepriseService.EntrepriseService;

@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @GetMapping
    public List<Entreprise> getAll() {
        return entrepriseService.getAllEntreprises();
    }

    @GetMapping("/{nsiret}")
    public Optional<Entreprise> getById(@PathVariable String nsiret) {
        return entrepriseService.getEntrepriseById(nsiret);
    }

    @GetMapping("/actives")
    public List<Entreprise> getActive() {
        return entrepriseService.getActiveEntreprises();
    }

    @GetMapping("/inactives")
    public List<Entreprise> getInactive() {
        return entrepriseService.getInactiveEntreprises();
    }

    @GetMapping("/ville/{ville}")
    public List<Entreprise> getByVille(@PathVariable String ville) {
        return entrepriseService.getEntreprisesByVille(ville);
    }

    @GetMapping("/raison-soc/{raisonSoc}")
    public Entreprise getByRaisonSoc(@PathVariable String raisonSoc) {
        return entrepriseService.getByRaisonSoc(raisonSoc);
    }

    @GetMapping("/email/{email}")
    public Entreprise getByEmail(@PathVariable String email) {
        return entrepriseService.getByEmail(email);
    }

    @GetMapping("/ville/{ville}/actives")
    public List<Entreprise> getActiveByVille(@PathVariable String ville) {
        return entrepriseService.getActiveEntreprisesByVille(ville);
    }

    @PostMapping
    public Entreprise create(@RequestBody Entreprise entreprise) {
        return entrepriseService.saveEntreprise(entreprise);
    }

    @PutMapping("/{nsiret}")
    public Entreprise update(@PathVariable String nsiret, @RequestBody Entreprise entreprise) {
        entreprise.setNsiret(nsiret);
        return entrepriseService.saveEntreprise(entreprise);
    }

    @DeleteMapping("/{nsiret}")
    public void delete(@PathVariable String nsiret) {
        entrepriseService.deleteEntreprise(nsiret);
    }
}
