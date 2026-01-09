package com.example.gestion_location_vehicule.controller;


import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import com.example.gestion_location_vehicule.request.ConnexionRequest;
import com.example.gestion_location_vehicule.service.UtilisateurService.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/utilisateur")
@RequiredArgsConstructor
public class UtilisateurMVCController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;


    // Afficher le formulaire
    @GetMapping("/connexion")
    public String showForm(Model model) {
        model.addAttribute("connexionRequest", new ConnexionRequest());

        return "utilisateur/connexion/login";
    }

    @PostMapping ("/connexion")
    public String connexion(@ModelAttribute ConnexionRequest connexionRequest) {

        boolean connexionOk = utilisateurService.connexionUser(connexionRequest);

        if (connexionOk) {
            return "redirect:/loueur/success";
        } else {
            return "redirect:/utilisateur/connexion?error=true";
        }

    }
}
