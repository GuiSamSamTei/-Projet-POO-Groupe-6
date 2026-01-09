package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.service.LoueurService.ILoueurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loueur")
public class LoueurMVCController {

    private final ILoueurService loueurService;

    public LoueurMVCController(ILoueurService loueurService) {
        this.loueurService = loueurService;
    }

    // Afficher le formulaire d'inscription
    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("loueur", new Loueur());
        return "loueur/inscription";
    }

    // Traiter le formulaire d'inscription
    @PostMapping("/inscription")
    public String submitForm(@ModelAttribute Loueur loueur, HttpSession session) {

        // Initialisation des valeurs par défaut
        loueur.setNotemoyenne(0.0);
        loueur.setNombreevaluations(0);

        // Création en base
        loueurService.create(loueur);

        // Stocker le loueur en session
        session.setAttribute("user", loueur);

        // Redirection vers le dashboard
        return "redirect:/loueur/success";
    }

    // Déconnexion
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // détruit la session
        return "redirect:/loueur/inscription";
    }

    @GetMapping("/profil")
    public String success(HttpSession session, Model model) {
        // Récupère le loueur depuis la session
        Loueur loueur = (Loueur) session.getAttribute("user");
        if (loueur != null) {
            model.addAttribute("loueur", loueur);
        }
        return "loueur/profil";
    }
}
