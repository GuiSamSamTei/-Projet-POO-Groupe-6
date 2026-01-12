package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Van;
import com.example.gestion_location_vehicule.request.VanRequest;
import com.example.gestion_location_vehicule.service.VanService.VanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> create(@RequestBody VanRequest vanRequest) {
        try {
            // 1. 必填项验证
            if (vanRequest.getMarque() == null || vanRequest.getMarque().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Le champ 'marque' est obligatoire");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            Van nouveauVan = vanService.ajouterVan(vanRequest);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Van ajouté avec succès");
            response.put("data", nouveauVan);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Erreur lors de l'ajout du van");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // 🔹 DELETE : supprimer un van
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            vanService.deleteVan(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Van supprimé avec succès");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur lors de la suppression"));
        }
    }

    // 🔹 PUT : mettre à jour un van
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VanRequest vanRequest) {
        try {
            Van van = vanService.modifierVan(id, vanRequest);
            return ResponseEntity.ok(Map.of("success", true, "message", "Van mis à jour", "data", van));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
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
