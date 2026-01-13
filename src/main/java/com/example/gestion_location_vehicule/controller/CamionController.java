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

    // GET : camion par ID
    @GetMapping("/{id}")
    public Optional<Camion> getById(@PathVariable Long id) {
        return camionService.getCamionById(id);
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

    // POST : créer un camion
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CamionRequest request) {
        try {
            // 1. 验证
            if (request.getMarque() == null || request.getMarque().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Le champ 'marque' est obligatoire"));
            }

            // 2. 调用 Service
            Camion camion = camionService.ajouterCamion(request);

            // 3. 统一返回
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
