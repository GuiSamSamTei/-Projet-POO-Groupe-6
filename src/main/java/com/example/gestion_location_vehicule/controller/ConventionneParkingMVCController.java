package com.example.gestion_location_vehicule.controller;



import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Parking;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.AgentService.AgentService;
import com.example.gestion_location_vehicule.service.ConventionneParkingService.ConventionneParkingService;
import com.example.gestion_location_vehicule.service.ParkingService.ParkingService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parking")
public class ConventionneParkingMVCController {

    private final ConventionneParkingService conventionneParkingService;
    private final ParkingService parkingService;
    private final AgentService agentService;



    @GetMapping
    public String getAllParking(
            @RequestParam(required = false) String ville,
            Model model,
            HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "redirect:/utilisateur/connexion?required=true";
        }

        Long agent_id = (Long) session.getAttribute("user");
        Agent agent = agentService.getAgentById(agent_id).get();

        // Récupérer tous les parkings
        List<Parking> parkingList = parkingService.getAllParking();

        // Filtrer par ville si un paramètre est fourni
        if (ville != null && !ville.isEmpty()) {
            parkingList = parkingList.stream()
                    .filter(p -> p.getVille().equalsIgnoreCase(ville))
                    .collect(Collectors.toList());
        }

        // Extraire la liste des villes uniques pour le select
        List<String> villes = parkingService.getAllParking().stream()
                .map(Parking::getVille)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("parkings", parkingList);
        model.addAttribute("villes", villes);
        model.addAttribute("villeSelectionnee", ville); // pour garder la ville sélectionnée
        model.addAttribute("parkingdeja", agent.getParkingConvIDs());

        return "agent/choixParking";
    }

    @PostMapping
    public String saveSelectedParkings(Long[] parkingIds, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/utilisateur/connexion?required=true";

        Long agentId = (Long) session.getAttribute("user");
        Agent agent = agentService.getAgentById(agentId).get();

        // Mettre à jour les parkings conventionnés de l'agent
        conventionneParkingService.updateAgentParkings(agent, parkingIds);

        return "redirect:/parking"; // Retour à la page de choix après enregistrement
    }








}
