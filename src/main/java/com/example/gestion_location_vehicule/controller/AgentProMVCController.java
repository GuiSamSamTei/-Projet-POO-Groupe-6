package com.example.gestion_location_vehicule.controller;

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

    // ------------------ INSCRIPTION ------------------
    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("user", new AgentPro());
        return "agentPro/inscription";
    }

    @PostMapping("/inscription")
    public String submitForm(@ModelAttribute AgentPro agentPro, HttpSession session) {

        // Valeurs par défaut
        agentPro.setNotemoyenne(0.0);
        agentPro.setNombreevaluations(0);
        agentPro.setNombrevehicules(0);
        agentPro.setRevenustotaux(0.0);

        agentProService.saveAgentPro(agentPro);

        // Stockage de l'utilisateur connecté
        session.setAttribute("user", agentPro.getId());

        return "redirect:/agent-pro/profil";
    }

    // ------------------ PROFIL CONNECTÉ ------------------
    @GetMapping("/profil")
    public String profil(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Optional<AgentPro> agentProOpt = agentProService.getAgentProById(userId);

        if (agentProOpt.isPresent()) {
            AgentPro agentPro = agentProOpt.get();

            // Ajouter l'agent et ses véhicules dans le modèle
            model.addAttribute("user", agentPro);
            List<Vehicule> vehicules = vehiculeService.getVehiculesParAgent(userId);
            model.addAttribute("vehicules", vehicules);

            return "agentPro/profil";
        }

        session.invalidate();
        return "redirect:/utilisateur/connexion";
    }

    // ------------------ MODIFICATION PROFIL ------------------
    @GetMapping("/profil/modifier")
    public String afficherFormulaireModification(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/agent-pro/inscription";
        }

        Optional<AgentPro> agentProOpt = agentProService.getAgentProById(userId);

        if (agentProOpt.isPresent()) {
            model.addAttribute("user", agentProOpt.get());
            return "agentPro/modifierProfil";
        }

        session.invalidate();
        return "redirect:/agent-pro/inscription";
    }

    @PostMapping("/profil/modifier")
    public String enregistrerModification(@ModelAttribute("user") AgentPro formUser, HttpSession session) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/agent-pro/inscription";
        }

        Optional<AgentPro> agentProOpt = agentProService.getAgentProById(userId);
        if (agentProOpt.isPresent()) {
            AgentPro existingUser = agentProOpt.get();

            // Mise à jour uniquement des champs modifiables
            existingUser.setRaisonsociale(formUser.getRaisonsociale());
            existingUser.setSiret(formUser.getSiret());
            existingUser.setUsername(formUser.getUsername());
            existingUser.setEmail(formUser.getEmail());
            existingUser.setTelephone(formUser.getTelephone());
            existingUser.setAdresse(formUser.getAdresse());
            existingUser.setTelephonepro(formUser.getTelephonepro());
            existingUser.setIban(formUser.getIban());
            existingUser.setBic(formUser.getBic());

            agentProService.saveAgentPro(existingUser);

            return "redirect:/agent-pro/profil";
        }

        session.invalidate();
        return "redirect:/agent-pro/inscription";
    }

    // ------------------ CONSULTER UN PROFIL D'AGENT PRO ------------------
    @GetMapping("/consulter/{id}")
    public String consulterProfil(@PathVariable Long id, Model model) {

        Optional<AgentPro> agentProOpt = agentProService.getAgentProById(id);

        if (agentProOpt.isPresent()) {
            AgentPro agentPro = agentProOpt.get();
            model.addAttribute("utilisateur", agentPro);

            // Ajouter les véhicules pour consultation
            List<Vehicule> vehicules = vehiculeService.getVehiculesParAgent(id);
            model.addAttribute("vehicules", vehicules);

            return "agentPro/consulterProfil";
        }

        // Profil inexistant
        return "redirect:/";
    }
}
