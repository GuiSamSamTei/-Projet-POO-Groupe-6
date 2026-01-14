package com.example.gestion_location_vehicule.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.model.ControleTechnique;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.model.*;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import com.example.gestion_location_vehicule.request.ConnexionRequest;
import com.example.gestion_location_vehicule.service.AgentParService.AgentParService;
import com.example.gestion_location_vehicule.service.AgentProService.AgentProService;
import com.example.gestion_location_vehicule.service.ControleTechniqueService.ControleTechniqueService;
import com.example.gestion_location_vehicule.service.LoueurService.LoueurService;
import com.example.gestion_location_vehicule.service.MessageService.MessageService;
import com.example.gestion_location_vehicule.service.UtilisateurService.UtilisateurService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/utilisateur")
@RequiredArgsConstructor
public class UtilisateurMVCController {

    private final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;
    private final AgentParService agentParService;
    private final AgentProService agentProService;
    private final LoueurService loueurService;
    private final MessageService messageService;
    private final ControleTechniqueService controleTechniqueService;
    private final VehiculeService vehiculeService;

    @GetMapping("/connexion")
    public String showForm(Model model, HttpSession session) {
        System.out.println("DEBUG - Showing login form");

        Long userid = (Long) session.getAttribute("user");
        if (session.getAttribute("user") != null) {
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

        Long pendingVehicleId = (Long) session.getAttribute("pendingVehicleId");
        if (pendingVehicleId != null) {
            session.removeAttribute("pendingVehicleId");
            return "redirect:/loueur/louer-vehicule/" + pendingVehicleId;
        }

        String redirectUrl = (String) session.getAttribute("redirectAfterLogin");
        if (redirectUrl != null && !redirectUrl.trim().isEmpty()) {
            session.removeAttribute("redirectAfterLogin");
            return "redirect:" + redirectUrl;
        }


        // 🔥 Redirection selon le type réel
        if (user instanceof Loueur) {
            session.setAttribute("role", "LOUEUR");
            return "redirect:/loueur/profil";
        }

        if (user instanceof AgentPar) {
            session.setAttribute("role", "AGENT_PAR");
            Utilisateur admin = utilisateurRepository.findById(40L).orElse(null);
            List<ControleTechnique> controles = ((AgentPar) user).getAllControlesTechniques();
            for (ControleTechnique ct : controles) {
                //if controle va expirer dans moins de 60 jours
                if (ct.isExpiringSoon(60) && !ct.isNotifie() && admin != null) {
                    //créer un message d'alerte
                    String alertContent = String.format("""
                            Message de part de l'administration:
                            Alerte: Le contrôle technique du véhicule avec l'ID %d expirera le %s. Veuillez prendre les mesures nécessaires.""",
                            ct.getVehicule().getId(), ct.getDateExpiration());
                    Message alertMessage = new Message(null, alertContent, new Date(), false, admin, user);
                    messageService.saveMessage(alertMessage);
                    //marquer le controle comme notifié
                    ct.setNotifie(true);
                    controleTechniqueService.enregistrerControleTechnique(ct);
                }   
            }
            return "redirect:/agent-par/profil";
        }

        if (user instanceof AgentPro) {
            session.setAttribute("role", "AGENT_PRO");
            Utilisateur admin = utilisateurRepository.findById(40L).orElse(null);
            List<ControleTechnique> controles = ((AgentPro) user).getAllControlesTechniques();
            for (ControleTechnique ct : controles) {
                //if controle va expirer dans moins de 60 jours
                if (ct.isExpiringSoon(60) && !ct.isNotifie() && admin != null) {
                    //créer un message d'alerte
                    String alertContent = String.format("""
                            Message de part de l'administration:
                            Alerte: Le contrôle technique du véhicule avec l'ID %d expirera le %s. Veuillez prendre les mesures nécessaires.""",
                            ct.getVehicule().getId(), ct.getDateExpiration());
                    Message alertMessage = new Message(null, alertContent, new Date(), false, admin, user);
                    messageService.saveMessage(alertMessage);
                    //marquer le controle comme notifié
                    ct.setNotifie(true);
                    controleTechniqueService.enregistrerControleTechnique(ct);
                }   
            }
            return "redirect:/agent-pro/profil";
        }

        // Sécurité : type inconnu
        session.invalidate();
        return "redirect:/utilisateur/connexion?error=true";
    }

    @GetMapping("/profile/{id}")
    public String profil(@PathVariable Long id, Model model, HttpSession session) {
        try {
            Long currentUserId = (Long) session.getAttribute("user");
            if (currentUserId == null) {
                session.setAttribute("redirectAfterLogin", "/utilisateur/profile/" + id);
                return "redirect:/utilisateur/connexion";
            }

            Utilisateur currentUser = utilisateurService.getUserbyID(currentUserId);
            if (currentUser == null) {
                session.invalidate();
                return "redirect:/utilisateur/connexion";
            }

            Utilisateur userToView = utilisateurService.getUserbyID(id);
            if (userToView == null) {
                return "redirect:/?error=user-not-found";
            }

            model.addAttribute("userToView", userToView);
            model.addAttribute("currentUser", currentUser);

            boolean isViewingOwnProfile = currentUserId.equals(id);
            model.addAttribute("isViewingOwnProfile", isViewingOwnProfile);

            if (currentUser instanceof Loueur) {
                model.addAttribute("currentUserProfileUrl", "/loueur/profil");
            } else if (currentUser instanceof AgentPar) {
                model.addAttribute("currentUserProfileUrl", "/agent-par/profil");
            } else if (currentUser instanceof AgentPro) {
                model.addAttribute("currentUserProfileUrl", "/agent-pro/profil");
            }

            if (userToView instanceof Loueur) {
                model.addAttribute("userType", "Loueur");
            } else if (userToView instanceof AgentPar) {
                model.addAttribute("userType", "Agent Particulier");
            } else if (userToView instanceof AgentPro) {
                model.addAttribute("userType", "Agent Professionnel");
            }

            System.out.println("DEBUG - Current user: " + currentUser.getUsername() +
                    " viewing profile of: " + userToView.getUsername());

            List<Vehicule> vehicles = vehiculeService.getVehiculesParAgent(id);
            System.out.println("DEBUG - Found " + vehicles.size() + " vehicles for user " + id);
            model.addAttribute("vehicles", vehicles);

            if (userToView instanceof AgentPar) {
                return "agentPar/profilVisitAgentPar";
            } else if (userToView instanceof AgentPro) {
                return "agentPro/profilVisitAgentPro";
            } else if (userToView instanceof Loueur) {
                return "loueur/profilVisitLoueur";
            }

            return "redirect:/?error=user-type-not-found";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/?error=user-profile-error";
        }
    }

    // Déconnexion
    @GetMapping("/logout")
    public String logout(HttpSession session
    ) {
        session.invalidate();
        return "redirect:/utilisateur/connexion";
    }
}
