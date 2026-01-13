package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
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

    public VehiculeMVCController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
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

}
