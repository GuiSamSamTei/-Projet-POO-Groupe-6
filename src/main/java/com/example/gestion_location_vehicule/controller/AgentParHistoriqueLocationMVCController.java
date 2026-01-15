package com.example.gestion_location_vehicule.controller;
import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.ContratlocationService.ContratlocationService;
import com.example.gestion_location_vehicule.service.VehiculeService.IVehiculeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/agent-par/historique-location")
@RequiredArgsConstructor

public class AgentParHistoriqueLocationMVCController {
    private final IVehiculeService vehiculeService;
    private final ContratlocationService contratlocationService;

    @GetMapping
    public String afficherHistorique(HttpSession session, Model model) {

        // 🔐 Vérification de la connexion
        Long agentId = (Long) session.getAttribute("user");
        if (agentId == null) {
            return "redirect:/utilisateur/connexion";
        }




        List<Vehicule> all_vehicules = vehiculeService.getVehiculesParAgent(agentId);
        List<Vehicule> vehicules = new LinkedList<>();


        Map<Long, List<Contratlocation>> contratsParVehicule = new HashMap<>();
        for (Vehicule v : all_vehicules) {
            List<Contratlocation> contrats = contratlocationService.trouverContraByVehiculeId(v.getId());
            if (!contrats.isEmpty()) {
                vehicules.add(v);
                contratsParVehicule.put(v.getId(), contrats);
            }
        }
        model.addAttribute("aujourdhui", java.time.LocalDate.now());
        model.addAttribute("vehicules", vehicules);
        model.addAttribute("contratsParVehicule", contratsParVehicule);

        return "AgentPar/historiquelocation";
    }

}
