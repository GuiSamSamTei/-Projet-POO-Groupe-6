package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.service.AgentParService.IAgentParService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/agent-par")
public class AgentParMVCController {

    private final IAgentParService agentParService;

    public AgentParMVCController(IAgentParService agentParService) {
        this.agentParService = agentParService;
    }

    // ------------------ INSCRIPTION ------------------
    // Afficher le formulaire d'inscription
    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("user", new AgentPar());
        return "agentPar/inscription";
    }

    // Traiter l'inscription
    @PostMapping("/inscription")
    public String submitForm(@ModelAttribute AgentPar agentPar, HttpSession session) {

        // Valeurs par défaut héritées de Utilisateur / Agent
        agentPar.setNotemoyenne(0.0);
        agentPar.setNombreevaluations(0);
        agentPar.setNombrevehicules(0);
        agentPar.setRevenustotaux(0.0);

        agentParService.saveAgentPar(agentPar);

        // Stockage de l'utilisateur connecté
        session.setAttribute("user", agentPar.getId());

        return "redirect:/agent-par/profil";
    }

    // ------------------ PROFIL ------------------
    @GetMapping("/profil")
    public String profil(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/agent-par/inscription";
        }

        Optional<AgentPar> agentParOpt = agentParService.getAgentParById(userId);

        if (agentParOpt.isPresent()) {
            model.addAttribute("user", agentParOpt.get());
            return "agentPar/profil";
        }

        // Cas incohérent : utilisateur en session mais inexistant en base
        session.invalidate();
        return "redirect:/agent-par/inscription";
    }

    // ------------------ MODIFICATION PROFIL ------------------
    // Afficher le formulaire de modification
    @GetMapping("/profil/modifier")
    public String afficherFormulaireModification(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/agent-par/inscription";
        }

        Optional<AgentPar> agentParOpt = agentParService.getAgentParById(userId);

        if (agentParOpt.isPresent()) {
            model.addAttribute("user", agentParOpt.get());
            return "agentPar/modifierProfil";
        }

        session.invalidate();
        return "redirect:/agent-par/inscription";
    }

    // Traiter le formulaire de modification
    @PostMapping("/profil/modifier")
    public String enregistrerModification(@ModelAttribute("user") AgentPar formUser, HttpSession session) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/agent-par/inscription";
        }

        Optional<AgentPar> agentParOpt = agentParService.getAgentParById(userId);
        if (agentParOpt.isPresent()) {
            AgentPar existingUser = agentParOpt.get();

            // Mettre à jour uniquement les champs modifiables
            existingUser.setNom(formUser.getNom());
            existingUser.setPrenom(formUser.getPrenom());
            existingUser.setUsername(formUser.getUsername());
            existingUser.setEmail(formUser.getEmail());
            existingUser.setTelephone(formUser.getTelephone());
            existingUser.setAdresse(formUser.getAdresse());
            existingUser.setTelephonepro(formUser.getTelephonepro());
            existingUser.setIban(formUser.getIban());
            existingUser.setBic(formUser.getBic());

            agentParService.saveAgentPar(existingUser);

            return "redirect:/agent-par/profil";
        }

        session.invalidate();
        return "redirect:/agent-par/inscription";
    }
}
