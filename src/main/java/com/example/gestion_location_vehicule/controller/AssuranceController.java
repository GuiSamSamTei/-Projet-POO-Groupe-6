package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.service.AssuranceService.AssuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assurances")
public class AssuranceController {

    private final AssuranceService assuranceService;

    public AssuranceController(AssuranceService assuranceService) {
        this.assuranceService = assuranceService;
    }

    // GET : toutes les assurances
    @GetMapping
    public List<Assurance> getAll() {
        return assuranceService.getAllAssurances();
    }

    // GET : assurance par ID
    @GetMapping("/{id}")
    public Optional<Assurance> getById(@PathVariable Long id) {
        return assuranceService.getAssuranceById(id);
    }

    // GET : assurances actives
    @GetMapping("/actives")
    public List<Assurance> getActive() {
        return assuranceService.getAssurancesActives();
    }

    // GET : assurance par défaut
    @GetMapping("/defaut")
    public Assurance getDefault() {
        return assuranceService.getAssuranceParDefaut();
    }

    // GET : recherche par nom
    @GetMapping("/nom/{nom}")
    public Assurance getByNom(@PathVariable String nom) {
        return assuranceService.getByNom(nom);
    }

    // GET : prix max
    @GetMapping("/prix/max/{prix}")
    public List<Assurance> getByPrixMax(@PathVariable long prix) {
        return assuranceService.getByPrixMax(prix);
    }

    // GET : prix min
    @GetMapping("/prix/min/{prix}")
    public List<Assurance> getByPrixMin(@PathVariable long prix) {
        return assuranceService.getByPrixMin(prix);
    }

    // GET : prix entre min et max
    @GetMapping("/prix")
    public List<Assurance> getByPrixBetween(
            @RequestParam long min,
            @RequestParam long max
    ) {
        return assuranceService.getByPrixBetween(min, max);
    }

    // POST : créer une assurance
    @PostMapping
    public Assurance create(@RequestBody Assurance assurance) {
        return assuranceService.saveAssurance(assurance);
    }

    // DELETE : supprimer une assurance
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        assuranceService.deleteAssurance(id);
    }
}
