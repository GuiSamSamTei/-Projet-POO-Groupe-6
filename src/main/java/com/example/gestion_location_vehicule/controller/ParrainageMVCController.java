package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Parrainage;
import com.example.gestion_location_vehicule.service.ParrainageService.IParrainageService;
import com.example.gestion_location_vehicule.service.PorteMonnaieService.IPorteMonnaieService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/loueur/parrainage")
@RequiredArgsConstructor
public class ParrainageMVCController {

    private final IParrainageService parrainageService;
    private final IPorteMonnaieService porteMonnaieService;
    private final com.example.gestion_location_vehicule.repository.LoueurRepository loueurRepository;

    @GetMapping
    public String afficherPageParrainage(HttpSession session, Model model) {
        Long loueurId = (Long) session.getAttribute("user");
        if (loueurId == null) {
            return "redirect:/connexion/login";
        }

        Loueur loueur = loueurRepository.findById(loueurId)
                .orElseThrow(() -> new IllegalArgumentException("Loueur introuvable"));

        double solde = porteMonnaieService.getSolde(loueurId);
        List<Parrainage> parrainages = parrainageService.getParrainagesByParrain(loueurId);
        Map<String, Object> stats = parrainageService.getStatistiquesParrainage(loueurId);
        Parrainage monParrain = parrainageService.getParrainageByFilleul(loueurId);

        model.addAttribute("loueur", loueur);
        model.addAttribute("solde", solde);
        model.addAttribute("parrainages", parrainages);
        model.addAttribute("stats", stats);
        model.addAttribute("monParrain", monParrain);

        return "loueur/parrainage";
    }

    @PostMapping("/inviter")
    public String inviterFilleul(@RequestParam String usernameFilleul, 
                                 HttpSession session, 
                                 RedirectAttributes redirectAttributes) {
        Long loueurId = (Long) session.getAttribute("user");
        if (loueurId == null) {
            return "redirect:/connexion/login";
        }

        try {
            parrainageService.creerParrainageParUsername(loueurId, usernameFilleul);
            redirectAttributes.addFlashAttribute("success", "Invitation envoyée avec succès à " + usernameFilleul);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erreur : " + e.getMessage());
        }

        return "redirect:/loueur/parrainage";
    }
}
