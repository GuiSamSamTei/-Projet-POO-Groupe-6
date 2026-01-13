package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Scooter;
import com.example.gestion_location_vehicule.request.ScooterRequest;
import com.example.gestion_location_vehicule.service.ScooterService.ScooterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/scooters")
public class ScooterController {

    private final ScooterService scooterService;

    public ScooterController(ScooterService scooterService) {
        this.scooterService = scooterService;
    }

    // 🔹 GET : tous les scooters
    @GetMapping
    public List<Scooter> getAll() {
        return scooterService.getAllScooters();
    }


    // 🔹 POST : créer un scooter
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ScooterRequest request) {
        try {
            // Validation simple
            if (request.getMarque() == null || request.getMarque().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Le champ 'marque' est obligatoire"));
            }

            // Appel Service
            Scooter scooter = scooterService.ajouterScooter(request);

            // Réponse JSON standardisée
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Scooter ajouté avec succès");
            response.put("data", scooter);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Erreur lors de l'ajout du scooter");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // 🔹 GET par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Scooter> scooter = scooterService.getScooterById(id);
        if (scooter.isPresent()) {
            return ResponseEntity.ok(scooter.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Scooter non trouvé"));
        }
    }

    // 🔹 PUT avec ScooterRequest
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ScooterRequest request) {
        try {
            Scooter scooter = scooterService.modifierScooter(id, request); // Assurez-vous d'avoir ajouté modifierScooter dans le Service
            return ResponseEntity.ok(Map.of("success", true, "message", "Scooter modifié avec succès", "data", scooter));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    // 🔹 DELETE : supprimer un scooter
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            scooterService.deleteScooter(id);
            return ResponseEntity.ok(Map.of("message", "Scooter supprimé avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur lors de la suppression"));
        }
    }



    // 🔹 GET : scooters disponibles
    @GetMapping("/disponibles")
    public List<Scooter> getAvailable() {
        return scooterService.getAvailableScooters();
    }

    // 🔹 GET : par ville
    @GetMapping("/ville/{ville}")
    public List<Scooter> getByVille(@PathVariable String ville) {
        return scooterService.getByVille(ville);
    }

    // 🔹 GET : par cylindrée min
    @GetMapping("/cylindree/min/{min}")
    public List<Scooter> getByCylindreeMin(@PathVariable int min) {
        return scooterService.getByCylindreeMin(min);
    }

    // 🔹 GET : par cylindrée max
    @GetMapping("/cylindree/max/{max}")
    public List<Scooter> getByCylindreeMax(@PathVariable int max) {
        return scooterService.getByCylindreeMax(max);
    }

    // 🔹 GET : par cylindrée entre min et max
    @GetMapping("/cylindree")
    public List<Scooter> getByCylindreeBetween(@RequestParam int min, @RequestParam int max) {
        return scooterService.getByCylindreeBetween(min, max);
    }

    // 🔹 GET : électriques
    @GetMapping("/electriques")
    public List<Scooter> getElectrique() {
        return scooterService.getElectrique();
    }

    // 🔹 GET : non électriques
    @GetMapping("/non-electriques")
    public List<Scooter> getNonElectrique() {
        return scooterService.getNonElectrique();
    }

    // 🔹 GET : par ville + cylindrée min
    @GetMapping("/ville/{ville}/cylindree/{min}")
    public List<Scooter> getByVilleAndCylindree(@PathVariable String ville, @PathVariable int min) {
        return scooterService.getByVilleAndCylindreeMin(ville, min);
    }

    // 🔹 GET : par ville + électriques
    @GetMapping("/ville/{ville}/electriques")
    public List<Scooter> getByVilleAndElectrique(@PathVariable String ville) {
        return scooterService.getByVilleAndElectrique(ville);
    }
}
