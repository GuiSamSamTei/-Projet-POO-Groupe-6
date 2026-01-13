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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/parking")
public class ConventionneParkingMVCController {

    private final ConventionneParkingService conventionneParkingService;
    private final ParkingService parkingService;
    private final AgentService agentService;



    @GetMapping
    public String getAllParking(Model model, HttpSession session)
    {
            if (session.getAttribute("user")==null)
                 return "redirect:/utilisateur/connexion?required=true";

            Long agent_id= (Long) session.getAttribute("user");
            Agent agent = agentService.getAgentById(agent_id).get();

            List<Parking> parkingList = parkingService.getAllParking();

            model.addAttribute("parkings" , parkingList);

            model.addAttribute("parkingdeja",agent.getParkingConvIDs());

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
