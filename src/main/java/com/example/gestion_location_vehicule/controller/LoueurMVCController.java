package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import com.example.gestion_location_vehicule.service.ContratlocationService.ContratlocationService;
import com.example.gestion_location_vehicule.service.LoueurService.ILoueurService;
import com.example.gestion_location_vehicule.service.LoueurService.LoueurService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/loueur")
public class LoueurMVCController {

    private final LoueurService loueurService;
    private final VehiculeRepository vehiculeRepository;
    private final ContratlocationService contratlocationService;

    public LoueurMVCController(ILoueurService loueurService, VehiculeService vehiculeService, VehiculeRepository vehiculeRepository, ContratlocationService contratlocationService) {
        this.loueurService = (LoueurService) loueurService;
        this.vehiculeRepository = vehiculeRepository;
        this.contratlocationService = contratlocationService;
    }

    // Afficher le formulaire d'inscription
    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("loueur", new Loueur());
        return "loueur/inscription";
    }

    // Traiter le formulaire d'inscription
    @PostMapping("/inscription")
    public String submitForm(@ModelAttribute Loueur loueur, HttpSession session) {

        // Initialisation des valeurs par défaut
        loueur.setNotemoyenne(0.0);
        loueur.setNombreevaluations(0);

        // Création en base
        loueurService.create(loueur);

        // Stocker le loueur en session
        session.setAttribute("user", loueur.getId());

        // Redirection vers le dashboard
        return "redirect:/loueur/profil";
    }

    @GetMapping("/profil")
    public String profil(HttpSession session, Model model) {
        // Récupère le loueur depuis la session

        if(session.getAttribute("user")==null)
            return "redirect:/utilisateur/connexion";

        Loueur loueur = loueurService.getById((Long) session.getAttribute("user"));
        if (loueur != null) {
            model.addAttribute("loueur", loueur);

        };
        return "/loueur/profil";
    }

     @GetMapping("/louer-vehicule/{id}")
    public String louerVehicule(@PathVariable Long id, HttpSession session, Model model)

     {
         Vehicule vehicule = vehiculeRepository.findById(id)
                 .orElseThrow(() -> new ResponseStatusException(
                         HttpStatus.NOT_FOUND, "Véhicule introuvable"
                 ));

         if (!vehicule.getVehiculedispo()) {
             return "redirect:/vehicule/liste?indisponible=true";
         }

         session.setAttribute("vehiculeEnCours", vehicule);

         // Passer le véhicule à la vue
         model.addAttribute("vehicule", vehicule);


         return "/loueur/location";
     }


    @PostMapping("/location/valider")
    public String validerLocation(
            @RequestParam Map<String, String> formData,
            HttpSession session
    ) {
        Vehicule vehicule = (Vehicule) session.getAttribute("vehiculeEnCours");

        if (vehicule == null) {
            return "redirect:/vehicule/liste?sessionExpired=true";
        }

        // 🔹 Récupération des champs
        LocalDate dateDebut = LocalDate.parse(formData.get("dateDebut"));
        LocalDate dateFin = LocalDate.parse(formData.get("dateFin"));
       // String assuranceLibelle = formData.get("assurance");
        String lieuDepot = formData.get("lieuDepot");

        // 🔹 Récupération entités liées
//        Assurance assurance = assuranceRepository
//                .findByLibelle(assuranceLibelle)
//                .orElseThrow(() -> new RuntimeException("Assurance introuvable"));

        Long loueur_id = (Long) session.getAttribute("user");
        Loueur loueur = loueurService.getById(loueur_id);

        // 🔹 Création du contrat
        Contratlocation contrat = new Contratlocation();
        contrat.setDatedebut(dateDebut);
        contrat.setDatefin(dateFin);
        contrat.setLieudepot(lieuDepot);
        contrat.setVehicule(vehicule);
        contrat.setLoueur(loueur);

        contratlocationService.ajouterContralocation(contrat);

        // 🔹 Mise à jour véhicule
        vehicule.setVehiculedispo(false);
        vehiculeRepository.save(vehicule);

        session.removeAttribute("vehiculeEnCours");

        return "redirect:/vehicule/liste?locationSuccess=true";
    }

}
