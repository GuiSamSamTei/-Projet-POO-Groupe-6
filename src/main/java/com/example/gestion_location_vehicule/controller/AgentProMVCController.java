package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.AgentProService.IAgentProService;
import com.example.gestion_location_vehicule.service.VehiculeService.IVehiculeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/agent-pro")
public class AgentProMVCController {

    private final IAgentProService agentProService;
    private final IVehiculeService vehiculeService;

    public AgentProMVCController(IAgentProService agentProService, IVehiculeService vehiculeService) {
        this.agentProService = agentProService;
        this.vehiculeService = vehiculeService;
    }

    // Formulaire d'inscription
    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("user", new AgentPro());
        return "agentPro/inscription";
    }

    // Traitement inscription
    @PostMapping("/inscription")
    public String submitForm(@ModelAttribute AgentPro agentPro, HttpSession session) {

        agentPro.setNotemoyenne(0.0);
        agentPro.setNombreevaluations(0);
        agentPro.setNombrevehicules(0);
        agentPro.setRevenustotaux(0.0);

        agentProService.saveAgentPro(agentPro);

        session.setAttribute("user", agentPro.getId());

        return "redirect:/agent-pro/profil";
    }

    @GetMapping("/profil")
    public String profil(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Optional<AgentPro> agentProOpt = agentProService.getAgentProById(userId);

        if (agentProOpt.isPresent()) {
            model.addAttribute("user", agentProOpt.get());
            return "agentPro/profil";
        }

        List<Vehicule> vehicules = vehiculeService.getVehiculesParAgent(userId);
        model.addAttribute("vehicules", vehicules);

        // Cas incohérent : utilisateur en session mais inexistant en base
        session.invalidate();
        return "redirect:/utilisateur/connexion";
    }
}
