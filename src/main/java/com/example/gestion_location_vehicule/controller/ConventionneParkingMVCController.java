package com.example.gestion_location_vehicule.controller;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Parking;
import com.example.gestion_location_vehicule.service.AgentService.AgentService;
import com.example.gestion_location_vehicule.service.ConventionneParkingService.ConventionneParkingService;
import com.example.gestion_location_vehicule.service.ParkingService.ParkingService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

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

        List<Parking> parkingList = parkingService.getAllParking();

        if (ville != null && !ville.isEmpty()) {
            parkingList = parkingList.stream()
                    .filter(p -> p.getVille().equalsIgnoreCase(ville))
                    .collect(Collectors.toList());
        }

        List<String> villes = parkingService.getAllParking().stream()
                .map(Parking::getVille)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("parkings", parkingList);
        model.addAttribute("villes", villes);
        model.addAttribute("villeSelectionnee", ville); 
        model.addAttribute("parkingdeja", agent.getParkingConvIDs());

        return "agent/choixParking";
    }

    @PostMapping
    public String saveSelectedParkings(Long[] parkingIds, HttpSession session) {
        if (session.getAttribute("user") == null)
            return "redirect:/utilisateur/connexion?required=true";

        Long agentId = (Long) session.getAttribute("user");
        Agent agent = agentService.getAgentById(agentId).get();

        conventionneParkingService.updateAgentParkings(agent, parkingIds);

        return "redirect:/parking";
    }








}
