package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.*;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.service.AssuranceService.AssuranceService;
import com.example.gestion_location_vehicule.service.ContratlocationService.ContratlocationService;
import com.example.gestion_location_vehicule.service.LoueurService.ILoueurService;
import com.example.gestion_location_vehicule.service.ParkingService.ParkingService;
import com.example.gestion_location_vehicule.service.TarificationService.TarificationService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/loueur")
public class LoueurMVCController {

    private final ILoueurService loueurService;
    private final VehiculeRepository vehiculeRepository;
    private final ContratlocationService contratlocationService;
    private final ParkingService parkingService;
    private final VehiculeService vehiculeService;
    private final AssuranceService assuranceService;
    private final TarificationService tarificationService;
    private final com.example.gestion_location_vehicule.service.ParrainageService.IParrainageService parrainageService;

    public LoueurMVCController(ILoueurService loueurService,
                               VehiculeRepository vehiculeRepository,
                               ContratlocationService contratlocationService, ParkingService parkingService, VehiculeService vehiculeService, AssuranceService assuranceService, TarificationService tarificationService, com.example.gestion_location_vehicule.service.ParrainageService.IParrainageService parrainageService) {
        this.loueurService = loueurService;
        this.vehiculeRepository = vehiculeRepository;
        this.contratlocationService = contratlocationService;
        this.parkingService = parkingService;
        this.vehiculeService = vehiculeService;
        this.assuranceService = assuranceService;
        this.tarificationService = tarificationService;
        this.parrainageService = parrainageService;
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

        loueurService.create(loueur);

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

        existingLoueur.setNom(formLoueur.getNom());
        existingLoueur.setPrenom(formLoueur.getPrenom());
        existingLoueur.setUsername(formLoueur.getUsername());
        existingLoueur.setEmail(formLoueur.getEmail());
        existingLoueur.setTelephone(formLoueur.getTelephone());
        existingLoueur.setVille(formLoueur.getVille());
        existingLoueur.setTypepermis(formLoueur.getTypepermis());

        loueurService.create(existingLoueur);

        return "redirect:/loueur/profil";
    }

    /* ===================== LOCATION VEHICULE ===================== */
    @GetMapping("/louer-vehicule/{id}")
    public String louerVehicule(@PathVariable Long id,
                                HttpSession session,
                                Model model) {

        if(session.getAttribute("user")==null){
            session.setAttribute("pendingVehicleId", id);
            return "redirect:/utilisateur/connexion?required=true";
        }

        String role = (String) session.getAttribute("role");
        if (role != null && !role.equals("LOUEUR")) {
            if ("AGENT_PRO".equals(role)) {
                return "redirect:/agent-pro/profil";
            } else if ("AGENT_PAR".equals(role)) {
                return "redirect:/agent-par/profil";
            }
            return "redirect:/";
        }



        Long loueur_id = (Long) session.getAttribute("user");

        Loueur loueur = null;
        try {
            loueur = loueurService.getById(loueur_id);
        } catch (Exception e) {
            return "redirect:/";
        }




        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Véhicule introuvable"
                ));

        List<Parking> parkings = vehicule.getAgent().getParkingConv();

        if (!vehicule.getVehiculedispo()) {
            return "redirect:/vehicule/liste?indisponible=true";
        }

        List<Assurance> assuranceList = assuranceService.getAllAssurances();
        Long anneeCourante = (long) java.time.LocalDate.now().getYear();

        Double tarifFixe = tarificationService.getbyAnnee(anneeCourante).getPrixfixe();
        
        // --- Gestion Affichage Gain Parrain ---
        try {
            Parrainage parrainage = parrainageService.findParrainageEnAttenteByFilleul(loueur_id);
            if (parrainage != null) {
                model.addAttribute("parrainage", parrainage);
                model.addAttribute("gainParrain", parrainage.getMontantCredit());
            }
        } catch (Exception e) {
            // Ignorer si erreur ou pas de parrainage
        }
        
        session.setAttribute("vehiculeEnCours", vehicule);
        model.addAttribute("vehicule", vehicule);
        model.addAttribute("parkings" , parkings);
        model.addAttribute("assurances", assuranceList);
        model.addAttribute("fraisServiceValue",tarifFixe);
        return "loueur/location";
    }

    @PostMapping("/location/valider")
    public String validerLocation(@RequestParam Map<String, String> formData,
                                  HttpSession session) {

        Vehicule vehicule = (Vehicule) session.getAttribute("vehiculeEnCours");
        if (vehicule == null) {
            return "redirect:/vehicule/liste?sessionExpired=true";
        }

        // 1. Récupération des dates et calcul de la durée
        LocalDate dateDebut = LocalDate.parse(formData.get("dateDebut"));
        LocalDate dateFin = LocalDate.parse(formData.get("dateFin"));

        // Calcul du nombre de jours (incluant le premier jour)
        long nbJours = java.time.temporal.ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;

        // 2. Vérification de disponibilité
        if(!vehiculeService.estDisponible(vehicule.getId(), dateDebut, dateFin)) {
            return "redirect:/vehicule/liste?VehiculeDispo=False";
        }

        // 3. Récupération du loueur
        Long loueurId = (Long) session.getAttribute("user");
        Loueur loueur = loueurService.getById(loueurId);

        // 4. Gestion de l'assurance et calcul du prix
        Long assuranceId = Long.parseLong(formData.get("assuranceId"));
        // Note: Assurez-vous d'avoir injecté assuranceRepository ou assuranceService
        Assurance assuranceChoisie = assuranceService.getAssuranceById(assuranceId).get();

        double prixLocationBase = vehicule.getPrixjour();
        double montantAssurance = 0.0;

        if (assuranceChoisie != null) {
            // Formule : prixlocation * pourcentage + prix_fixe
            montantAssurance = (prixLocationBase * (assuranceChoisie.getPourcentage() / 100.0))
                    + assuranceChoisie.getPrixFixe();
        }

        Long anneeCourante = (long) java.time.LocalDate.now().getYear();

        Double tarifFixe = tarificationService.getbyAnnee(anneeCourante).getPrixfixe();
        double fraisService = tarifFixe; // À adapter selon votre attribut en base de données

        // Formule Finale : (prixlocation * nbJours) + prixassurance + frais_service
        double montantTotal = (prixLocationBase * nbJours) + montantAssurance + fraisService;

        // 5. Création du contrat
        Contratlocation contrat = new Contratlocation();
        contrat.setDatedebut(dateDebut);
        contrat.setDatefin(dateFin);
        contrat.setVehicule(vehicule);
        contrat.setLoueur(loueur);
        contrat.setAssurance(assuranceChoisie); // Liaison avec l'assurance
        contrat.setPrixtotal(montantTotal);
        contrat.setPrixLocationJour(vehicule.getPrixjour());
        contrat.setPrixAssuranceApplique(montantAssurance);
        contrat.setFraisServiceApplique(fraisService);
        contrat.setPrixtotal(montantTotal);
        contrat.setNombreJours((int) nbJours);

        // Gestion du lieu de dépôt / parking
        if(Long.parseLong(formData.get("parkingId")) != 0) {
            Long parking_id = Long.parseLong(formData.get("parkingId"));
            Parking parking = parkingService.trouverParkingparId(parking_id);
            contrat.setParking(parking);

        } else {
            contrat.setLieudepot(vehicule.getVilledispo()); // Lieu par défaut
        }

        // 6. Sauvegarde et mise à jour
        contratlocationService.ajouterContralocation(contrat);

        vehicule.setVehiculedispo(false);
        vehiculeRepository.save(vehicule);

        session.removeAttribute("vehiculeEnCours");
        session.setAttribute("dernierContrat", contrat);
        return "redirect:/loueur/confirmation";


    }

    @GetMapping("/confirmation")
    public String afficherRecap(HttpSession session, Model model) {
        Contratlocation contrat = (Contratlocation) session.getAttribute("dernierContrat");
        if (contrat == null) return "redirect:/vehicule/liste";

        // Calcul de la durée pour l'affichage si non stocké
        long nbJours = java.time.temporal.ChronoUnit.DAYS.between(contrat.getDatedebut(), contrat.getDatefin()) + 1;
        model.addAttribute("nbJours", nbJours);
        model.addAttribute("c", contrat);

        return "loueur/recapitulatif";
    }
    /* ===================== CONSULTER UN PROFIL DE LOUEUR ===================== */
    @GetMapping("/consulter/{id}")
    public String consulterProfil(@PathVariable Long id, Model model) {

        Loueur loueur = loueurService.getById(id);
        if (loueur == null) {
            return "redirect:/";
        }

        model.addAttribute("utilisateur", loueur);


        return "loueur/consulterProfil";
    }
}
