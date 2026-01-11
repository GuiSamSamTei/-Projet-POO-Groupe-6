package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vehicules")
public class VehiculeMVCController {

    private final VehiculeService vehiculeService;

    public VehiculeMVCController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping("/liste")
    public String showVehicules(Model model) {
        // Récupère tous les véhicules disponibles
        List<Vehicule> vehicules = vehiculeService.getAllVehicules();
        model.addAttribute("vehicules", vehicules);
        return "vehicule/vehicules"; // Correspond à src/main/resources/templates/vehicule/vehicules.html
    }
}
