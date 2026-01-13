package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Camion;
import com.example.gestion_location_vehicule.request.CamionRequest;
import com.example.gestion_location_vehicule.service.CamionService.CamionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/camions")
public class CamionController {

    private final CamionService camionService;

    public CamionController(CamionService camionService) {
        this.camionService = camionService;
    }

    // GET : tous les camions
    @GetMapping
    public List<Camion> getAll() {
        return camionService.getAllCamions();
    }

    // GET : camions disponibles
    @GetMapping("/dispo")
    public List<Camion> getDispo() {
        return camionService.getCamionsDispo();
    }

    // GET : camions par ville
    @GetMapping("/ville/{ville}")
    public List<Camion> getByVille(@PathVariable String ville) {
        return camionService.getCamionsByVille(ville);
    }

    // GET : camions par charge minimale
    @GetMapping("/charge-min/{min}")
    public List<Camion> getByCharge(@PathVariable double min) {
        return camionService.getCamionsByChargemax(min);
    }

    // GET : camions par volume minimal
    @GetMapping("/volume-min/{min}")
    public List<Camion> getByVolume(@PathVariable double min) {
        return camionService.getCamionsByVolume(min);
    }

    // GET : camions par ville + dispo + charge minimale
    @GetMapping("/recherche")
    public List<Camion> getByVilleDispoAndCharge(@RequestParam String ville, @RequestParam double minCharge) {
        return camionService.getCamionsByVilleDispoAndCharge(ville, minCharge);
    }

    // 🔹 GET par ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Camion> camion = camionService.getCamionById(id);
        if (camion.isPresent()) {
            return ResponseEntity.ok(camion.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Camion non trouvé"));
        }
    }

    // 🔹 PUT pour modification
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CamionRequest request) {
        try {
            Camion camion = camionService.modifierCamion(id, request);
            return ResponseEntity.ok(Map.of("success", true, "message", "Camion modifié avec succès", "data", camion));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }

    // POST : créer un camion
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CamionRequest request) {
        try {
            if (request.getMarque() == null || request.getMarque().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Le champ 'marque' est obligatoire"));
            }

            Camion camion = camionService.ajouterCamion(request);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Camion ajouté avec succès");
            response.put("data", camion);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Erreur lors de l'ajout du camion");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // DELETE : supprimer un camion
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        camionService.deleteCamion(id);
    }
}
