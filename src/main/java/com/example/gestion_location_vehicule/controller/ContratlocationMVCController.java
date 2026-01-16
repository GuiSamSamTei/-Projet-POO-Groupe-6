package com.example.gestion_location_vehicule.controller;


import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.EvalL;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.AgentService.AgentService;
import com.example.gestion_location_vehicule.service.ContratlocationService.ContratlocationService;
import com.example.gestion_location_vehicule.service.EvalLService.EvalLService;
import com.example.gestion_location_vehicule.service.LoueurService.LoueurService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/historique-location")
@RequiredArgsConstructor
public class ContratlocationMVCController {

    private final ContratlocationService contratlocationService;
    private final LoueurService loueurService;
    private final VehiculeService vehiculeService;
    private final EvalLService evalLService;
    private final AgentService agentService;


    @GetMapping
    public String contratlocation(Model model, HttpSession session){

        if(session.getAttribute("user")==null){
            return "redirect:utilisateur/connexion/login";
        }

        Long id = (Long)session.getAttribute("user");

        Loueur loueur = loueurService.getById(id);

        List<Contratlocation> contratlocationList = contratlocationService.trouverContraByLoueurId(id);


        model.addAttribute("contratlocationList",contratlocationList);



        return "loueur/historiquelocation";


    }

    @GetMapping("/contrat/terminer/{id}")
    public String pageTerminerContrat(@PathVariable Long id, Model model) {
        Contratlocation contrat = contratlocationService.trouverContraById(id);
        model.addAttribute("contrat", contrat);
        return "agent/cloture_contrat";
    }

    @PostMapping("/contrat/valider-cloture")
    public String validerCloture(
                    @RequestParam Long contratId,          
            @RequestParam boolean disponibilite,
            @RequestParam Integer note,           
            @RequestParam String commentaire,
                    @RequestParam Double nouveauKilometrage,
            HttpSession session
            ) {
        Long agentId = (Long) session.getAttribute("user");
        if (agentId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Agent agent = agentService.getAgentById(agentId).get();


        Contratlocation contrat = contratlocationService.trouverContraById(contratId);

        Vehicule v = contrat.getVehicule();

        v.setVehiculedispo(true);
        v.setKilometrage(nouveauKilometrage);
        vehiculeService.addVehicule(v);


        EvalL evalL = new EvalL();
        evalL.setAgent(agent);
        evalL.setLoueur(contrat.getLoueur());
        evalL.setNote(note);
        evalL.setDatenote(LocalDate.now());
        evalL.setCommentaire(commentaire);

        evalLService.saveEvalL(evalL);


        contrat.setEvalL(evalL);
        contratlocationService.ajouterContralocation(contrat);


        if(agent instanceof AgentPar)
            return "redirect:/agent-par/historique-location?success=true";
        else
            return "redirect:/agent-pro/historique-location?success=true";
    }

    @PostMapping("/valider-contrat")
    public String validerContrat(@RequestParam Long idContrat,HttpSession session) {
        Contratlocation contrat = contratlocationService.trouverContraById(idContrat);


        Long agentId = (Long) session.getAttribute("user");
        if (agentId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Agent agent = agentService.getAgentById(agentId).get();



        if (contrat != null) {
            contrat.setValidee(true);


            contratlocationService.ajouterContralocation(contrat);
        }
        if (agent instanceof AgentPro)
          return "redirect:/agent-pro/profil?success=Validated";
        else
            return "redirect:/agent-par/profil?success=Validated";
    }

    @PostMapping("/refuser-contrat")
    public String refuserContract(@RequestParam Long idContrat,HttpSession session)
    {
        Contratlocation contrat = contratlocationService.trouverContraById(idContrat);

        Long agentId = (Long) session.getAttribute("user");
        if (agentId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Agent agent = agentService.getAgentById(agentId).get();
        if (contrat != null) {

            contratlocationService.supprimerContralocation(contrat.getId());
        }
        if (agent instanceof AgentPro)
            return "redirect:/agent-pro/profil?success=Deleted";
        else
            return "redirect:/agent-par/profil?success=Deleted";

    }

}
