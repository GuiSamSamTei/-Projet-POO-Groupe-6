package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Moto;
import com.example.gestion_location_vehicule.request.MotoRequest;
import com.example.gestion_location_vehicule.service.MotoService.MotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/motos")
public class MotoController {

    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    // 🔹 GET : toutes les motos
    @GetMapping
    public List<Moto> getAll() {
        return motoService.getAllMotos();
    }

    // 🔹 GET : moto par ID
    @GetMapping("/{id}")
    public Optional<Moto> getById(@PathVariable Long id) {
        return motoService.getMotoById(id);
    }

    // 🔹 POST : créer une moto
    @PostMapping
    public ResponseEntity<?> create(@RequestBody MotoRequest request) {
        try {
            // Validation simple
            if (request.getMarque() == null || request.getMarque().trim().isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Le champ 'marque' est obligatoire"));
            }

            // Appel Service
            Moto moto = motoService.ajouterMoto(request);

            // Réponse JSON standardisée
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Moto ajoutée avec succès");
            response.put("data", moto);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Erreur lors de l'ajout de la moto");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    // 🔹 DELETE : supprimer une moto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            motoService.deleteMoto(id);
            return ResponseEntity.ok(Map.of("message", "Moto supprimée avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Erreur lors de la suppression"));
        }
    }

    // 🔹 PUT : mettre à jour une moto
    @PutMapping("/{id}")
    public Moto update(@PathVariable Long id, @RequestBody Moto moto) {
        moto.setId(id);
        return motoService.saveMoto(moto);
    }


    // 🔹 GET : motos disponibles
    @GetMapping("/disponibles")
    public List<Moto> getDispo() {
        return motoService.getMotosDispo();
    }

    // 🔹 GET : motos par ville
    @GetMapping("/ville/{ville}")
    public List<Moto> getByVille(@PathVariable String ville) {
        return motoService.getMotosByVille(ville);
    }

    // 🔹 GET : motos par cylindrée minimale
    @GetMapping("/cylindree/min/{min}")
    public List<Moto> getByCylindreeMin(@PathVariable int min) {
        return motoService.getMotosByCylindreeMin(min);
    }

    // 🔹 GET : motos par cylindrée maximale
    @GetMapping("/cylindree/max/{max}")
    public List<Moto> getByCylindreeMax(@PathVariable int max) {
        return motoService.getMotosByCylindreeMax(max);
    }

    // 🔹 GET : motos par cylindrée entre min et max
    @GetMapping("/cylindree")
    public List<Moto> getByCylindreeBetween(@RequestParam int min, @RequestParam int max) {
        return motoService.getMotosByCylindreeBetween(min, max);
    }

    // 🔹 GET : motos par nombre de chevaux minimum
    @GetMapping("/chevaux/min/{min}")
    public List<Moto> getByNbChevauxMin(@PathVariable int min) {
        return motoService.getMotosByNbChevauxMin(min);
    }

    // 🔹 GET : motos par nombre de chevaux maximum
    @GetMapping("/chevaux/max/{max}")
    public List<Moto> getByNbChevauxMax(@PathVariable int max) {
        return motoService.getMotosByNbChevauxMax(max);
    }

    // 🔹 GET : motos par nombre de chevaux entre min et max
    @GetMapping("/chevaux")
    public List<Moto> getByNbChevauxBetween(@RequestParam int min, @RequestParam int max) {
        return motoService.getMotosByNbChevauxBetween(min, max);
    }

    // 🔹 GET : motos dispo + ville + cylindrée
    @GetMapping("/ville/{ville}/cylindree/{min}")
    public List<Moto> getByVilleAndCylindreeMin(@PathVariable String ville, @PathVariable int min) {
        return motoService.getMotosByVilleAndCylindreeMin(ville, min);
    }

    // 🔹 GET : motos dispo + ville + nb chevaux
    @GetMapping("/ville/{ville}/chevaux/{min}")
    public List<Moto> getByVilleAndNbChevauxMin(@PathVariable String ville, @PathVariable int min) {
        return motoService.getMotosByVilleAndNbChevauxMin(ville, min);
    }
}
