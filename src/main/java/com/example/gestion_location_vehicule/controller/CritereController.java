package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Critere;
import com.example.gestion_location_vehicule.enums.TypeCritere;
import com.example.gestion_location_vehicule.service.CritereService.CritereService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/criteres")
public class CritereController {

    private final CritereService critereService;

    public CritereController(CritereService critereService) {
        this.critereService = critereService;
    }

    // GET : tous les critères
    @GetMapping
    public List<Critere> getAll() {
        return critereService.getAllCriteres();
    }

    // GET : critere par ID
    @GetMapping("/{id}")
    public Optional<Critere> getById(@PathVariable Long id) {
        return critereService.getCritereById(id);
    }

    // GET : critere par nom
    @GetMapping("/nom/{nom}")
    public Critere getByNom(@PathVariable String nom) {
        return critereService.getByNom(nom);
    }

    // GET : critere par type
    @GetMapping("/type/{type}")
    public List<Critere> getByType(@PathVariable TypeCritere type) {
        return critereService.getByType(type);
    }

    // POST : créer un critere
    @PostMapping
    public Critere create(@RequestBody Critere critere) {
        return critereService.saveCritere(critere);
    }

    // PUT : modifier un critere
    @PutMapping("/{id}")
    public Critere update(@PathVariable Long id, @RequestBody Critere critere) {
        critere.setId(id);
        return critereService.saveCritere(critere);
    }

    // DELETE : supprimer un critere
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        critereService.deleteCritere(id);
    }
}
