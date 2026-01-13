package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.service.ContratlocationService.ContratlocationService;
import com.example.gestion_location_vehicule.service.LoueurService.ILoueurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/loueur")
public class LoueurMVCController {

    private final ILoueurService loueurService;
    private final VehiculeRepository vehiculeRepository;
    private final ContratlocationService contratlocationService;

    public LoueurMVCController(ILoueurService loueurService,
                               VehiculeRepository vehiculeRepository,
                               ContratlocationService contratlocationService) {
        this.loueurService = loueurService;
        this.vehiculeRepository = vehiculeRepository;
        this.contratlocationService = contratlocationService;
    }

    /* ===================== INSCRIPTION ===================== */

    @GetMapping("/inscription")
    public String afficherFormulaireInscription(Model model) {
        model.addAttribute("loueur", new Loueur());
        return "loueur/inscription";
    }

    @PostMapping("/inscription")
    public String traiterInscription(@ModelAttribute Loueur loueur, HttpSession session) {
        loueur.setNotemoyenne(0.0);
        loueur.setNombreevaluations(0);

        loueurService.create(loueur);  // création via save()

        session.setAttribute("user", loueur.getId());
        return "redirect:/loueur/profil";
    }

    /* ===================== PROFIL ===================== */

    @GetMapping("/profil")
    public String afficherProfil(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Loueur loueur = loueurService.getById(userId);
        model.addAttribute("loueur", loueur);

        return "loueur/profil";
    }

    /* ===================== MODIFICATION PROFIL ===================== */

    @GetMapping("/profil/modifier")
    public String afficherFormulaireModification(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Loueur loueur = loueurService.getById(userId);
        model.addAttribute("loueur", loueur);

        return "loueur/modifierProfil";
    }

    @PostMapping("/profil/modifier")
    public String enregistrerModification(@ModelAttribute("loueur") Loueur formLoueur,
                                          HttpSession session) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Loueur existingLoueur = loueurService.getById(userId);
        if (existingLoueur == null) {
            session.invalidate();
            return "redirect:/utilisateur/connexion";
        }

        // Mettre à jour les champs modifiables
        existingLoueur.setNom(formLoueur.getNom());
        existingLoueur.setPrenom(formLoueur.getPrenom());
        existingLoueur.setUsername(formLoueur.getUsername());
        existingLoueur.setEmail(formLoueur.getEmail());
        existingLoueur.setTelephone(formLoueur.getTelephone());
        existingLoueur.setVille(formLoueur.getVille());
        existingLoueur.setTypepermis(formLoueur.getTypepermis());

        // Sauvegarde avec save() via create()
        loueurService.create(existingLoueur);

        return "redirect:/loueur/profil";
    }

    /* ===================== LOCATION VEHICULE ===================== */

    @GetMapping("/louer-vehicule/{id}")
    public String louerVehicule(@PathVariable Long id,
                                HttpSession session,
                                Model model) {

        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Véhicule introuvable"
                ));

        if (!vehicule.getVehiculedispo()) {
            return "redirect:/vehicule/liste?indisponible=true";
        }

        session.setAttribute("vehiculeEnCours", vehicule);
        model.addAttribute("vehicule", vehicule);

        return "loueur/location";
    }

    @PostMapping("/location/valider")
    public String validerLocation(@RequestParam Map<String, String> formData,
                                  HttpSession session) {

        Vehicule vehicule = (Vehicule) session.getAttribute("vehiculeEnCours");
        if (vehicule == null) {
            return "redirect:/vehicule/liste?sessionExpired=true";
        }

        LocalDate dateDebut = LocalDate.parse(formData.get("dateDebut"));
        LocalDate dateFin = LocalDate.parse(formData.get("dateFin"));
        String lieuDepot = formData.get("lieuDepot");

        Long loueurId = (Long) session.getAttribute("user");
        Loueur loueur = loueurService.getById(loueurId);

        Contratlocation contrat = new Contratlocation();
        contrat.setDatedebut(dateDebut);
        contrat.setDatefin(dateFin);
        contrat.setLieudepot(lieuDepot);
        contrat.setVehicule(vehicule);
        contrat.setLoueur(loueur);

        contratlocationService.ajouterContralocation(contrat);

        vehicule.setVehiculedispo(false);
        vehiculeRepository.save(vehicule);

        session.removeAttribute("vehiculeEnCours");

        return "redirect:/vehicule/liste?locationSuccess=true";
    }
}
