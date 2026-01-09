package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.request.ConnexionRequest;
import com.example.gestion_location_vehicule.service.UtilisateurService.IUtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/connexion")
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
            Model model,
            HttpSession session
    ) {
        long loueur = utilisateurService.connexionUser(connexionRequest);

        if (loueur != -1) {
            // Guardar en sesión exactamente igual que en inscripción
            session.setAttribute("user", loueur);
            return "redirect:/vehicules/liste";
        }

        model.addAttribute("error", true);
        return "connexion/login";
    }

}
