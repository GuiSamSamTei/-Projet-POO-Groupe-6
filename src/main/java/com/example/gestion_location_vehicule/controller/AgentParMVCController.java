package com.example.gestion_location_vehicule.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.EvalA;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.AgentParService.IAgentParService;
import com.example.gestion_location_vehicule.service.ContratlocationService.ContratlocationService;
import com.example.gestion_location_vehicule.service.EvalAService.EvalAService;
import com.example.gestion_location_vehicule.service.VehiculeService.IVehiculeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/agent-par")
public class AgentParMVCController {

    private final IAgentParService agentParService;
    private final IVehiculeService vehiculeService;
    private final ContratlocationService contratlocationService;
    private final EvalAService evalAService;

    public AgentParMVCController(IAgentParService agentParService, IVehiculeService vehiculeService, ContratlocationService contratlocationService, EvalAService evalAService) {
        this.agentParService = agentParService;
        this.vehiculeService = vehiculeService;
        this.contratlocationService = contratlocationService;
        this.evalAService = evalAService;
    }

    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("user", new AgentPar());
        return "agentPar/inscription";
    }

    @PostMapping("/inscription")
    public String submitForm(@ModelAttribute AgentPar agentPar, HttpSession session) {

        agentPar.setNotemoyenne(0.0);
        agentPar.setNombreevaluations(0);
        agentPar.setNombrevehicules(0);
        agentPar.setRevenustotaux(0.0);

        agentParService.saveAgentPar(agentPar);

        session.setAttribute("user", agentPar.getId());
        session.setAttribute("role", "AGENT_PAR");

        return "redirect:/agent-par/profil";
    }

    @GetMapping("/profil")
    public String profil(HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Optional<AgentPar> agentParOpt = agentParService.getAgentParById(userId);

        if (agentParOpt.isPresent()) {
            model.addAttribute("user", agentParOpt.get());
            List<Vehicule> mesVehicules = vehiculeService.getVehiculesParAgent(userId);
            model.addAttribute("vehicules", mesVehicules);
            List<Contratlocation> contratsEnAttente = contratlocationService.trouverparAgentIDetValideeFalse(userId);
            model.addAttribute("contratsAttente", contratsEnAttente);

            List<EvalA> evalAS = evalAService.getByAgent(agentParOpt.get());
            model.addAttribute("evaluations", evalAS);
            return "agentPar/profil";
        }
        session.invalidate();
        return "redirect:/utilisateur/connexion";
    }

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

    @PostMapping("/profil/modifier")
    public String enregistrerModification(@ModelAttribute("user") AgentPar formUser, HttpSession session) {

        Long userId = (Long) session.getAttribute("user");
        if (userId == null) {
            return "redirect:/agent-par/inscription";
        }

        Optional<AgentPar> agentParOpt = agentParService.getAgentParById(userId);
        if (agentParOpt.isPresent()) {
            AgentPar existingUser = agentParOpt.get();

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
