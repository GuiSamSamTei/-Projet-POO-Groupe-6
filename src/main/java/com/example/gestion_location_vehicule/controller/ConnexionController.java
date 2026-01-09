package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.request.ConnexionRequest;
import com.example.gestion_location_vehicule.service.UtilisateurService.IUtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConnexionController {

    private final IUtilisateurService utilisateurService;

    public ConnexionController(IUtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "connexion/login";
    }

    @PostMapping("/login")
    public String doLogin(
            @ModelAttribute ConnexionRequest connexionRequest,
            Model model
    ) {
        boolean ok = utilisateurService.connexionUser(connexionRequest);

        if (ok) {
            return "redirect:/vehicules/liste";
        }

        model.addAttribute("error", true);
        return "connexion/login";
    }
}
