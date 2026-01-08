package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.service.LoueurService.ILoueurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loueurs")
public class LoueurController {

    private final ILoueurService loueurService;

    public LoueurController(ILoueurService loueurService) {
        this.loueurService = loueurService;
    }

    // GET /api/loueurs
    @GetMapping
    public List<Loueur> getAllLoueurs() {
        return loueurService.getAll();
    }

    // GET /api/loueurs/{id}
    @GetMapping("/{id}")
    public Loueur getLoueurById(@PathVariable Long id) {
        return loueurService.getById(id);
    }

    // POST /api/loueurs
    @PostMapping
    public Loueur createLoueur(@RequestBody Loueur loueur) {
        return loueurService.create(loueur);
    }

    // GET /api/loueurs/search?keyword=ali
    @GetMapping("/search")
    public List<Loueur> searchLoueurs(@RequestParam String keyword) {
        return loueurService.searchByNomOrPrenom(keyword);
    }

    // GET /api/loueurs/exact?nom=Doe&prenom=John
    @GetMapping("/exact")
    public Loueur getByNomAndPrenom(
            @RequestParam String nom,
            @RequestParam String prenom) {
        return loueurService.getByNomAndPrenom(nom, prenom);
    }
}
