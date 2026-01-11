package com.example.gestion_location_vehicule.controller;


import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import com.example.gestion_location_vehicule.request.ConnexionRequest;
import com.example.gestion_location_vehicule.service.UtilisateurService.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/utilisateur")
@RequiredArgsConstructor
public class UtilisateurMVCController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;


    // Afficher le formulaire
    @GetMapping("/connexion")
    public String showForm(Model model, HttpSession session) {

        Long userid = (Long) session.getAttribute("user");
        if(session.getAttribute("user")!=null)
        {
            Utilisateur user = utilisateurService.getUserbyID(userid);
            if (user instanceof Loueur) {

                return "redirect:/loueur/profil";
            }

            if (user instanceof AgentPar) {

                return "redirect:/agent-par/profil";
            }

            if (user instanceof AgentPro) {

                return "redirect:/agent-pro/profil";
            }

        }


        model.addAttribute("connexionRequest", new ConnexionRequest());

        return "utilisateur/connexion/login";
    }

    @PostMapping("/connexion")
    public String connexion(@ModelAttribute ConnexionRequest connexionRequest, HttpSession session) {

        Optional<Utilisateur> userOpt = utilisateurService.connexionUser(connexionRequest);

        if (userOpt.isEmpty()) {
            // Login échoué
            return "redirect:/utilisateur/connexion?error=true";
        }

        Utilisateur user = userOpt.get();
        session.setAttribute("user", user.getId());

        // 🔥 Redirection selon le type réel
        if (user instanceof Loueur) {
            session.setAttribute("role", "LOUEUR");
            return "redirect:/loueur/profil";
        }

        if (user instanceof AgentPar) {
            session.setAttribute("role", "AGENT_PAR");
            return "redirect:/agent-par/profil";
        }

        if (user instanceof AgentPro) {
            session.setAttribute("role", "AGENT_PRO");
            return "redirect:/agent-pro/profil";
        }

        // Sécurité : type inconnu
        session.invalidate();
        return "redirect:/utilisateur/connexion?error=true";
    }


    // Déconnexion
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/utilisateur/connexion";
    }
}
