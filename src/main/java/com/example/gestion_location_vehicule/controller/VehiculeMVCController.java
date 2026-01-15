package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.*;
import com.example.gestion_location_vehicule.service.UtilisateurService.UtilisateurService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vehicule")
public class VehiculeMVCController {

    private final VehiculeService vehiculeService;
    private final UtilisateurService utilisateurService;

    public VehiculeMVCController(VehiculeService vehiculeService, UtilisateurService utilisateurService) {
        this.vehiculeService = vehiculeService;
        this.utilisateurService = utilisateurService;
    }

    //afficher la page



    @GetMapping("/liste")
    public String showVehicules(Model model) {
        // Récupère tous les véhicules disponibles
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        model.addAttribute("vehicules", vehicules);
        return "vehicule/vehicules"; // Correspond à src/main/resources/templates/vehicule/vehicules.html
    }

    @GetMapping("/filtres")
    public String filtrerVehicules(
            @RequestParam Map<String, String> params,
            Model model) {

        List<Vehicule> vehicules = vehiculeService.filtrer(params);
        model.addAttribute("vehicules", vehicules);

        return "vehicule/vehicules"; // on réutilise la même page
    }

    @GetMapping("/afficher/{id}")
    public String afficherVehicule(@PathVariable Long id, Model model)
    {
        Vehicule vehicule = vehiculeService.getVehiculeByid(id);

        model.addAttribute("vehicule", vehicule);

        return "vehicule/afficherVehicule";

    }

    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        if (userId != null) {
            Utilisateur currentUser = utilisateurService.getUserbyID(userId);
            model.addAttribute("currentUser", currentUser);

            String profileUrl = "#";
            if (currentUser instanceof Loueur) {
                profileUrl = "/loueur/profil";
            } else if (currentUser instanceof AgentPar) {
                profileUrl = "/agent-par/profil";
            } else if (currentUser instanceof AgentPro) {
                profileUrl = "/agent-pro/profil";
            }
            model.addAttribute("profileUrl", profileUrl);
        }
    }

}
