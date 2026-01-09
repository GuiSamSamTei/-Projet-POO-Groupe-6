package com.example.gestion_location_vehicule.controller;


import com.example.gestion_location_vehicule.model.Voiture;
import com.example.gestion_location_vehicule.request.VoitureRequest;
import com.example.gestion_location_vehicule.service.VoitureService.VoitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> ajouterVoiture(@RequestBody VoitureRequest voitureRequest) {
        try {
            Voiture voiture = voitureService.ajouterVoiture(voitureRequest);
            return ResponseEntity.ok(voiture);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
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
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerVoiture(@PathVariable Long id) {
        try {
            voitureService.supprimerVoiture(id);
            return ResponseEntity.ok("Voiture supprimée avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
