package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.AssuranceService.AssuranceService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import com.example.gestion_location_vehicule.service.CalculPrixService.ICalculPrixService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/prix")
@RequiredArgsConstructor
public class CalculPrixController {

    private final ICalculPrixService calculPrixService;
    private final VehiculeService vehiculeService;
    private final AssuranceService assuranceService;

    /**
     * Calcul du prix global d'une location
     *
     * @param vehiculeId  ID du véhicule choisi
     * @param assuranceId ID de l'assurance choisie (par défaut, complète ou premium)
     * @param dateDebut   date de début de la location (format yyyy-MM-dd)
     * @param dateFin     date de fin de la location (format yyyy-MM-dd)
     * @return JSON contenant le détail du prix
     */
    @GetMapping("/global")
    public Map<String, Object> getPrixGlobal(
            @RequestParam Long vehiculeId,
            @RequestParam Long assuranceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin
    ) {
        // Récupérer les objets depuis leurs services
        Vehicule vehicule = vehiculeService.getVehiculeByid(vehiculeId);
        if (vehicule == null) throw new IllegalArgumentException("Véhicule introuvable");

        Assurance assurance = assuranceService.getAssuranceById(assuranceId)
                .orElseThrow(() -> new IllegalArgumentException("Assurance introuvable"));

        // Calcul du nombre de jours de location
        long jours = ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
        if (jours <= 0) {
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
        }

        // Utiliser le service centralisé pour calculer le prix global
        double prixTotal = calculPrixService.calculerPrixGlobal(vehicule, assurance, dateDebut, dateFin);

        // Calcul des détails pour la réponse
        double prixVehicule = vehicule.getPrixjour() * jours;
        double prixAssuranceParJour = calculPrixService.getPrixAssurance(vehicule, assurance);
        double prixAssuranceTotal = prixAssuranceParJour * jours;
        double commission = prixTotal - prixVehicule - prixAssuranceTotal;

        // Retour sous forme JSON avec détails
        Map<String, Object> resultat = new HashMap<>();
        resultat.put("vehiculeId", vehiculeId);
        resultat.put("assuranceId", assuranceId);
        resultat.put("assuranceNom", assurance.getNom());
        resultat.put("nombreJours", jours);
        resultat.put("prixVehicule", prixVehicule);
        resultat.put("prixAssuranceParJour", prixAssuranceParJour);
        resultat.put("prixAssuranceTotal", prixAssuranceTotal);
        resultat.put("commission", commission);
        resultat.put("prixTotal", prixTotal);

        return resultat;
    }
}
