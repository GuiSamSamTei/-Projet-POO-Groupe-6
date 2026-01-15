package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Parrainage;
import com.example.gestion_location_vehicule.service.ParrainageService.IParrainageService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parrainages")
@RequiredArgsConstructor
public class ParrainageController {

    private final IParrainageService parrainageService;

    /**
     * Créer un parrainage
     */
    @PostMapping
    public ResponseEntity<?> creerParrainage(
            @RequestParam(required = false) Long filleulId,
            @RequestParam(required = false) String emailFilleul,
            HttpSession session) {
        Long parrainId = (Long) session.getAttribute("user");
        if (parrainId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            Parrainage parrainage;
            if (filleulId != null) {
                parrainage = parrainageService.creerParrainage(parrainId, filleulId);
            } else if (emailFilleul != null && !emailFilleul.isEmpty()) {
                parrainage = parrainageService.creerParrainageParEmail(parrainId, emailFilleul);
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "Veuillez fournir un ID ou un email de filleul"));
            }

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Parrainage créé avec succès");
            response.put("parrainage", parrainage);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Récupère la liste des filleuls parrainés
     */
    @GetMapping("/mes-parrainages")
    public ResponseEntity<?> getMesParrainages(HttpSession session) {
        Long parrainId = (Long) session.getAttribute("user");
        if (parrainId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            List<Parrainage> parrainages = parrainageService.getParrainagesByParrain(parrainId);
            return ResponseEntity.ok(parrainages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Récupère les informations sur mon parrain (si je suis parrainé)
     */
    @GetMapping("/mon-parrain")
    public ResponseEntity<?> getMonParrain(HttpSession session) {
        Long filleulId = (Long) session.getAttribute("user");
        if (filleulId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            Parrainage parrainage = parrainageService.getParrainageByFilleul(filleulId);
            if (parrainage == null) {
                return ResponseEntity.ok(Map.of("message", "Vous n'avez pas de parrain"));
            }
            return ResponseEntity.ok(parrainage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Récupère les statistiques de parrainage
     */
    @GetMapping("/stats")
    public ResponseEntity<?> getStatistiques(HttpSession session) {
        Long parrainId = (Long) session.getAttribute("user");
        if (parrainId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            Map<String, Object> stats = parrainageService.getStatistiquesParrainage(parrainId);
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Vérifie si on peut parrainer un utilisateur
     */
    @GetMapping("/peut-parrainer/{filleulId}")
    public ResponseEntity<?> peutParrainer(
            @PathVariable Long filleulId,
            HttpSession session) {
        Long parrainId = (Long) session.getAttribute("user");
        if (parrainId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            boolean peutParrainer = parrainageService.peutParrainer(parrainId, filleulId);
            Map<String, Object> response = new HashMap<>();
            response.put("peutParrainer", peutParrainer);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Annuler un parrainage
     */
    @DeleteMapping("/{parrainageId}")
    public ResponseEntity<?> annulerParrainage(
            @PathVariable Long parrainageId,
            HttpSession session) {
        Long utilisateurId = (Long) session.getAttribute("user");
        if (utilisateurId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            parrainageService.annulerParrainage(parrainageId);
            return ResponseEntity.ok(Map.of("message", "Parrainage annulé avec succès"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
