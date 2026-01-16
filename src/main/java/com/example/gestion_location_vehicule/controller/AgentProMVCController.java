package com.example.gestion_location_vehicule.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.EvalA;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.AgentProService.IAgentProService;
import com.example.gestion_location_vehicule.service.ContratlocationService.ContratlocationService;
import com.example.gestion_location_vehicule.service.EvalAService.EvalAService;
import com.example.gestion_location_vehicule.service.VehiculeService.IVehiculeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/agent-pro")
public class AgentProMVCController {

    private final IAgentProService agentProService;
    private final IVehiculeService vehiculeService;
    private final ContratlocationService contratlocationService;
    private final EvalAService evalAService;


    public AgentProMVCController(IAgentProService agentProService, IVehiculeService vehiculeService, ContratlocationService contratlocationService, EvalAService evalAService) {
        this.agentProService = agentProService;
        this.vehiculeService = vehiculeService;
        this.contratlocationService = contratlocationService;
        this.evalAService = evalAService;
    }

    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("user", new AgentPro());
        return "agentPro/inscription";
    }

    @PostMapping("/inscription")
    public String submitForm(@ModelAttribute AgentPro agentPro, HttpSession session) {

        agentPro.setNotemoyenne(0.0);
        agentPro.setNombreevaluations(0);
        agentPro.setNombrevehicules(0);
        agentPro.setRevenustotaux(0.0);

        agentProService.saveAgentPro(agentPro);

        session.setAttribute("user", agentPro.getId());
        session.setAttribute("role", "AGENT_PRO");

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
            AgentPro agentPro = agentProOpt.get();

            model.addAttribute("user", agentPro);
            List<Vehicule> vehicules = vehiculeService.getVehiculesParAgent(userId);
            model.addAttribute("vehicules", vehicules);
            List<Contratlocation> contratsEnAttente = contratlocationService.trouverparAgentIDetValideeFalse(userId);
            model.addAttribute("contratsAttente", contratsEnAttente);

            List<EvalA> evalAS = evalAService.getByAgent(agentPro);
            model.addAttribute("evaluations", evalAS);
            return "agentPro/profil";
        }


        session.invalidate();
        return "redirect:/utilisateur/connexion";
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

    @GetMapping("/consulter/{id}")
    public String consulterProfil(@PathVariable Long id, Model model) {

        Optional<AgentPro> agentProOpt = agentProService.getAgentProById(id);

        if (agentProOpt.isPresent()) {
            AgentPro agentPro = agentProOpt.get();
            model.addAttribute("utilisateur", agentPro);

            List<Vehicule> vehicules = vehiculeService.getVehiculesParAgent(id);
            model.addAttribute("vehicules", vehicules);

            return "agentPro/consulterProfil";
        }

        return "redirect:/";
    }
}
