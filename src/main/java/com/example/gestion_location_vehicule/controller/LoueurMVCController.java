package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.service.LoueurService.ILoueurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loueur")
public class LoueurMVCController {

    private final ILoueurService loueurService;

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Controller MVC détecté";
    }

    public LoueurMVCController(ILoueurService loueurService) {
        this.loueurService = loueurService;
    }

    // Afficher le formulaire
    @GetMapping("/inscription")
    public String showForm(Model model) {
        model.addAttribute("loueur", new Loueur());
        return "loueur/inscription";
    }

    // Traiter le formulaire
    @PostMapping("/inscription")
    public String submitForm(@ModelAttribute Loueur loueur) {
        loueurService.create(loueur);
        return "redirect:/loueur/success";
    }

    @GetMapping("/success")
    public String success() {
        return "loueur/success";
    }
}

