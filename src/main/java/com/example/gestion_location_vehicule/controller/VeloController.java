package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Velo;
import com.example.gestion_location_vehicule.request.VeloRequest;
import com.example.gestion_location_vehicule.service.VeloService.VeloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<?> ajouterVelo(@RequestBody VeloRequest veloRequest) {
        try {
            Velo nouveauVelo = veloService.ajouterVelo(veloRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauVelo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifierVelo(
            @PathVariable Long id,
            @RequestBody VeloRequest veloRequest) {
        try {
            Velo veloModifie = veloService.modifierVelo(veloRequest, id);
            return ResponseEntity.ok(veloModifie);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> supprimerVelo(@PathVariable Long id) {
        try {
            veloService.supprimerVelo(id);
            return ResponseEntity.ok("Vélo supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
