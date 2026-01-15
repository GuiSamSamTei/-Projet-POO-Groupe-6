package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.enums.TypeTransaction;
import com.example.gestion_location_vehicule.model.PorteMonnaie;
import com.example.gestion_location_vehicule.model.TransactionPorteMonnaie;
import com.example.gestion_location_vehicule.service.PorteMonnaieService.IPorteMonnaieService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/porte-monnaie")
@RequiredArgsConstructor
public class PorteMonnaieController {

    private final IPorteMonnaieService porteMonnaieService;

    /**
     * Récupère le solde du porte-monnaie de l'utilisateur connecté
     */
    @GetMapping("/solde")
    public ResponseEntity<Map<String, Object>> getSolde(HttpSession session) {
        Long utilisateurId = (Long) session.getAttribute("user");
        if (utilisateurId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            double solde = porteMonnaieService.getSolde(utilisateurId);
            Map<String, Object> response = new HashMap<>();
            response.put("solde", solde);
            response.put("utilisateurId", utilisateurId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Récupère l'historique des transactions
     */
    @GetMapping("/historique")
    public ResponseEntity<?> getHistorique(HttpSession session) {
        Long utilisateurId = (Long) session.getAttribute("user");
        if (utilisateurId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            List<TransactionPorteMonnaie> historique = porteMonnaieService.getHistorique(utilisateurId);
            return ResponseEntity.ok(historique);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Récupère l'historique par type de transaction
     */
    @GetMapping("/historique/{type}")
    public ResponseEntity<?> getHistoriqueParType(
            @PathVariable TypeTransaction type,
            HttpSession session) {
        Long utilisateurId = (Long) session.getAttribute("user");
        if (utilisateurId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            List<TransactionPorteMonnaie> historique = porteMonnaieService.getHistoriqueParType(utilisateurId, type);
            return ResponseEntity.ok(historique);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Récupère les informations complètes du porte-monnaie
     */
    @GetMapping("/info")
    public ResponseEntity<?> getInfo(HttpSession session) {
        Long utilisateurId = (Long) session.getAttribute("user");
        if (utilisateurId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        try {
            PorteMonnaie porteMonnaie = porteMonnaieService.getByUtilisateur(utilisateurId);
            Map<String, Object> response = new HashMap<>();
            response.put("id", porteMonnaie.getId());
            response.put("solde", porteMonnaie.getSolde());
            response.put("dateCreation", porteMonnaie.getDateCreation());
            response.put("dateDerniereModification", porteMonnaie.getDateDerniereModification());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Créditer le porte-monnaie (admin uniquement)
     */
    @PostMapping("/crediter")
    public ResponseEntity<?> crediter(
            @RequestParam Long utilisateurId,
            @RequestParam double montant,
            @RequestParam(required = false) String description,
            HttpSession session) {
        Long adminId = (Long) session.getAttribute("user");
        if (adminId == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Non authentifié"));
        }

        // TODO: Vérifier que l'utilisateur est admin

        try {
            TransactionPorteMonnaie transaction = porteMonnaieService.crediter(
                    utilisateurId,
                    montant,
                    TypeTransaction.CREDIT_ADMIN,
                    description != null ? description : "Crédit administrateur"
            );
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
