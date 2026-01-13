package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Velo;
import com.example.gestion_location_vehicule.request.VeloRequest;
import com.example.gestion_location_vehicule.service.VeloService.VeloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/velos")
public class VeloController {

    private final VeloService veloService;

    @GetMapping
    public ResponseEntity<?> getAllVelos() {
        try {
            List<Velo> velos = veloService.getAllVelo();
            return ResponseEntity.ok(velos);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des vélos");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<?> ajouterVelo(@RequestBody VeloRequest veloRequest) {
        try {
            // 1. 简单的必填项验证 (和 VoitureController 保持一致)
            if (veloRequest.getMarque() == null || veloRequest.getMarque().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Le champ 'marque' est obligatoire");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            // 2. 调用 Service
            Velo nouveauVelo = veloService.ajouterVelo(veloRequest);

            // 3. 构建统一的返回格式 (JSON)
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Vélo ajouté avec succès"); // 前端弹窗会显示这句话
            response.put("data", nouveauVelo);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Erreur lors de l'ajout du vélo");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifierVelo(
            @PathVariable Long id,
            @RequestBody VeloRequest veloRequest) {
        try {
            Velo veloModifie = veloService.modifierVelo(veloRequest, id);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Vélo modifié avec succès");
            response.put("data", veloModifie);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur interne: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerVelo(@PathVariable Long id) {
        try {
            veloService.supprimerVelo(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Vélo supprimé avec succès");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur lors de la suppression"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        // Attention : VeloService doit avoir une méthode getVeloById qui retourne Optional<Velo>
        // Si elle n'existe pas, ajoutez-la dans VeloService : return veloRepository.findById(id);
        Optional<Velo> velo = veloService.getAllVelo().stream().filter(v -> v.getId().equals(id)).findFirst(); // Ou mieux : veloRepository.findById(id)

        if (velo.isPresent()) {
            return ResponseEntity.ok(velo.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Vélo non trouvé"));
        }
    }
}