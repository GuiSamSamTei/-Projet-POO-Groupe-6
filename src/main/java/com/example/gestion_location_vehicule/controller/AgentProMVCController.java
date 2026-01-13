package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.service.AgentProService.IAgentProService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/agent-pro")
public class AgentProMVCController {

    private final IAgentProService agentProService;

    public AgentProMVCController(IAgentProService agentProService) {
        this.agentProService = agentProService;
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
            return "redirect:/agent-pro/inscription";
        }

        Optional<AgentPro> agentProOpt = agentProService.getAgentProById(userId);

        if (agentProOpt.isPresent()) {
            model.addAttribute("user", agentProOpt.get());
            return "agentPro/profil";
        }

        // Cas incohérent : utilisateur en session mais inexistant en base
        session.invalidate();
        return "redirect:/agent-pro/inscription";
    }


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

            // Mettre à jour uniquement les champs modifiables
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

}
