package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.service.UtilisateurService.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final UtilisateurService utilisateurService;

    public HomeController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        try {
            model.addAttribute("pageTitle", "AutoPartage - Location de véhicules");

            model.addAttribute("popularCities", List.of("Toulouse", "Paris", "Lyon", "Marseille", "Bordeaux", "Lille"));

            Long userId = (Long) session.getAttribute("user");
            System.out.println("DEBUG - User ID from session: " + userId);

            if (userId != null) {
                try {
                    Utilisateur user = utilisateurService.getUserbyID(userId);
                    System.out.println("DEBUG - Found user: " + user.getUsername()); // 调试信息

                    model.addAttribute("currentUser", user);
                    if (user instanceof Loueur) {
                        model.addAttribute("profileUrl", "/loueur/profil");
                        System.out.println("DEBUG - User is Loueur");
                    } else if (user instanceof AgentPar) {
                        model.addAttribute("profileUrl", "/agent-par/profil");
                        System.out.println("DEBUG - User is AgentPar");
                    } else if (user instanceof AgentPro) {
                        model.addAttribute("profileUrl", "/agent-pro/profil");
                        System.out.println("DEBUG - User is AgentPro");
                    }
                } catch (Exception e) {
                    System.err.println("DEBUG - Error getting user: " + e.getMessage());
                    e.printStackTrace();
                    session.removeAttribute("user");
                    session.removeAttribute("role");
                }
            } else {
                System.out.println("DEBUG - No user in session");
            }

            return "home/home";

        } catch (Exception e) {
            System.err.println("DEBUG - Error in home controller: " + e.getMessage());
            e.printStackTrace();
            return "error/500";
        }
    }

    @GetMapping("/map")
    public String showMap() {
        return "map";
    }
}