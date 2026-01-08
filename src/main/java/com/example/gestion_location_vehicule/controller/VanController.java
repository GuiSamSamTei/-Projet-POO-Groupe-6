package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Van;
import com.example.gestion_location_vehicule.service.VanService.VanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vans")
public class VanController {

    private final VanService vanService;

    public VanController(VanService vanService) {
        this.vanService = vanService;
    }

    // 🔹 GET : tous les vans
    @GetMapping
    public List<Van> getAll() {
        return vanService.getAllVans();
    }

    // 🔹 GET : par ID
    @GetMapping("/{id}")
    public Optional<Van> getById(@PathVariable Long id) {
        return vanService.getVanById(id);
    }

    // 🔹 POST : créer un van
    @PostMapping
    public Van create(@RequestBody Van van) {
        return vanService.saveVan(van);
    }

    // 🔹 PUT : mettre à jour un van
    @PutMapping("/{id}")
    public Van update(@PathVariable Long id, @RequestBody Van van) {
        van.setId(id);
        return vanService.saveVan(van);
    }

    // 🔹 DELETE : supprimer un van
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vanService.deleteVan(id);
    }

    // 🔹 GET : vans disponibles
    @GetMapping("/dispo")
    public List<Van> getVansDispo() {
        return vanService.getVansDispo();
    }

    // 🔹 GET : vans par ville
    @GetMapping("/ville/{ville}")
    public List<Van> getVansByVille(@PathVariable String ville) {
        return vanService.getVansByVille(ville);
    }

    // 🔹 GET : vans par nombre de places min
    @GetMapping("/places/min/{minPlaces}")
    public List<Van> getVansByNombrePlacesMin(@PathVariable int minPlaces) {
        return vanService.getVansByNombrePlacesMin(minPlaces);
    }

    // 🔹 GET : vans par nombre de places max
    @GetMapping("/places/max/{maxPlaces}")
    public List<Van> getVansByNombrePlacesMax(@PathVariable int maxPlaces) {
        return vanService.getVansByNombrePlacesMax(maxPlaces);
    }

    // 🔹 GET : vans par nombre de places entre min et max
    @GetMapping("/places")
    public List<Van> getVansByNombrePlacesBetween(
            @RequestParam int min,
            @RequestParam int max
    ) {
        return vanService.getVansByNombrePlacesBetween(min, max);
    }

    // 🔹 GET : vans disponibles dans une ville avec nombre de places minimum
    @GetMapping("/ville-dispo-minplaces")
    public List<Van> getVansByVilleDispoEtMinPlaces(
            @RequestParam String ville,
            @RequestParam int minPlaces
    ) {
        return vanService.getVansByVilleDispoEtMinPlaces(ville, minPlaces);
    }
}
