package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Voiture;
import com.example.gestion_location_vehicule.request.VoitureRequest;
import com.example.gestion_location_vehicule.service.VoitureService.VoitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/voitures")
public class VoitureController {

    private final VoitureService voitureService;

    @GetMapping
    public ResponseEntity<?> getAllVoitures() {
        try {
            List<Voiture> voitures = voitureService.getAllVoiture();
            return ResponseEntity.ok(voitures);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la récupération des voitures");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<?> ajouterVoiture(@RequestBody VoitureRequest voitureRequest) {
        try {
            System.out.println("Received voiture request: " + voitureRequest);

            // 验证必填字段
            if (voitureRequest.getMarque() == null || voitureRequest.getMarque().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Le champ 'marque' est obligatoire");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            if (voitureRequest.getModele() == null || voitureRequest.getModele().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Le champ 'modele' est obligatoire");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            Voiture voiture = voitureService.ajouterVoiture(voitureRequest);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Voiture ajoutée avec succès");
            response.put("data", voiture);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();

            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Erreur lors de l'ajout de la voiture");
            errorResponse.put("message", e.getMessage());
            errorResponse.put("exception", e.getClass().getName());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifierVoiture(
            @PathVariable Long id,
            @RequestBody VoitureRequest voitureRequest) {
        try {
            Voiture voiture = voitureService.modifierVoiture(voitureRequest, id);
            return ResponseEntity.ok(voiture);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la modification de la voiture");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerVoiture(@PathVariable Long id) {
        try {
            voitureService.supprimerVoiture(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Voiture supprimée avec succès");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Erreur lors de la suppression de la voiture");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }
}