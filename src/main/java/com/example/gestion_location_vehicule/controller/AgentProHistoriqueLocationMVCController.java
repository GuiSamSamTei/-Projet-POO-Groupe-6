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
@RequestMapping("/agent-pro/historique-location")
@RequiredArgsConstructor
public class AgentProHistoriqueLocationMVCController {

    private final IVehiculeService vehiculeService;
    private final ContratlocationService contratlocationService;

    @GetMapping
    public String afficherHistorique(HttpSession session, Model model) {

        // 🔐 Vérification de la connexion
        Long agentId = (Long) session.getAttribute("user");
        if (agentId == null) {
            return "redirect:/utilisateur/connexion";
        }



        // 🚗 Récupération des véhicules de l'agent pro
        List<Vehicule> all_vehicules = vehiculeService.getVehiculesParAgent(agentId);
        List<Vehicule> vehicules = new LinkedList<>();

        // 📄 Récupération des contrats pour chaque véhicule
        Map<Long, List<Contratlocation>> contratsParVehicule = new HashMap<>();
        for (Vehicule v : all_vehicules) {
            List<Contratlocation> contrats = contratlocationService.trouverContraByVehiculeId(v.getId());
            if (!contrats.isEmpty()) {
                vehicules.add(v);
                contratsParVehicule.put(v.getId(), contrats);
            }
        }
        model.addAttribute("aujourdhui", java.time.LocalDate.now());
        model.addAttribute("vehicules", vehicules); // ✅ passe la liste des véhicules
        model.addAttribute("contratsParVehicule", contratsParVehicule); // ✅ passe la map à la vue

        return "AgentPro/historiquelocation";
    }
}
