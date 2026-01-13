package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.DisponibiliteVehicule;
import com.example.gestion_location_vehicule.model.Parking;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.DisponibiliteVehiculeService.IDisponibiliteVehiculeService;
import com.example.gestion_location_vehicule.service.ParkingService.ParkingService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vehicules")
@RequiredArgsConstructor
public class DisponibiliteVehiculeController {

    private final IDisponibiliteVehiculeService disponibiliteService;
    private final VehiculeService vehiculeService;
    private final ParkingService parkingService;

    // =========================
    // AFFICHAGE (GET)
    // =========================
    @GetMapping("/{id}/disponibilites")
    public String getDisponibilites(@PathVariable Long id, Model model) {

        Vehicule vehicule = vehiculeService.getVehiculeByid(id);
        List<Parking> parkingList = parkingService.getAllParking();
        model.addAttribute("vehicule", vehicule);
        model.addAttribute(
                "disponibilites",
                disponibiliteService.getDisponibilitesPourVehicule(vehicule)
        );
        model.addAttribute("nouvelleDisponibilite", new DisponibiliteVehicule());
        model.addAttribute("parkings",parkingList);
        return "disponibilite/gestion";
    }

    // =========================
    // AJOUT (POST)
    // =========================
    @PostMapping("/{id}/disponibilites")
    public String ajouterDisponibilite(
            @PathVariable Long id,
            @RequestParam LocalDate dateDebut,
            @RequestParam LocalDate dateFin,
            @RequestParam(required = false) String description
    ) {
        Vehicule vehicule = vehiculeService.getVehiculeByid(id);

        DisponibiliteVehicule dispo = new DisponibiliteVehicule();
        dispo.setDateDebut(dateDebut);
        dispo.setDateFin(dateFin);
        dispo.setDescription(description);
        dispo.setDisponible(true);
        dispo.setVehicule(vehicule);

        disponibiliteService.save(dispo);

        return "redirect:/vehicules/" + id + "/disponibilites";
    }

}
