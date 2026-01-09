package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehicules/ajouter")
public class AjouterVehiculeController {

    private final VehiculeService vehiculeService;

    public AjouterVehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    // Affiche le formulaire d'ajout
    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("vehicule", new Vehicule());
        return "loueur/vehicule/ajouterVehicule";
    }

    // Traite la soumission du formulaire
    @PostMapping
    public String submitForm(@ModelAttribute Vehicule vehicule) {
        vehiculeService.addVehicule(vehicule);
        return "redirect:/vehicules/liste"; // redirige vers la liste des véhicules
    }
}
