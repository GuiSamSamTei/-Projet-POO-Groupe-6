package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.service.UtilisateurService.UtilisateurService;
import com.example.gestion_location_vehicule.service.VehiculeService.VehiculeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vehicule")
@RequiredArgsConstructor
public class VehiculePageController {

    private final UtilisateurService utilisateurService;
    private final VehiculeService vehiculeService;

    /**
     * Méthode privée pour vérifier si l'utilisateur est un agent connecté.
     * Vérifie les attributs de session définis dans UtilisateurMVCController.
     */
    private boolean isAgentLoggedIn(HttpSession session) {
        // Récupérer le rôle et l'ID utilisateur depuis la session
        String role = (String) session.getAttribute("role");
        Long userId = (Long) session.getAttribute("user");

        // Vérifier si l'utilisateur est connecté et s'il a le rôle d'agent
        return userId != null && role != null &&
                (role.equals("AGENT_PAR") || role.equals("AGENT_PRO"));
    }

    /**
     * Afficher la page de sélection du type de véhicule.
     * Accessible uniquement aux agents.
     */
    @GetMapping("/type-select")
    public String showTypeSelectPage(HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        // 1. Vérification de sécurité : est-ce un agent ?
        if (!isAgentLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("error", "Accès refusé. Vous devez être connecté en tant qu'agent.");
            return "redirect:/utilisateur/connexion";
        }

        // 2. Récupération des informations de l'agent
        Long userId = (Long) session.getAttribute("user");
        Utilisateur user = utilisateurService.getUserbyID(userId);

        Long agentId = user.getId();
        String agentName = user.getUsername();

        // 3. Ajout au modèle pour l'affichage
        model.addAttribute("agentId", agentId);
        model.addAttribute("agentName", agentName);

        return "vehicule/type-select";
    }

    /**
     * Afficher le formulaire d'ajout de véhicule.
     * Accessible uniquement aux agents.
     */
    @GetMapping("/formulaire/ajouter/{type}")
    public String showAddForm(@PathVariable String type,
                              HttpSession session,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        // 1. Vérification de sécurité
        if (!isAgentLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("error", "Accès refusé. Vous devez être connecté en tant qu'agent.");
            return "redirect:/utilisateur/connexion";
        }

        // 2. Récupération des données utilisateur
        Long userId = (Long) session.getAttribute("user");
        Utilisateur user = utilisateurService.getUserbyID(userId);

        Long agentId = user.getId();
        String agentName = user.getUsername();

        // 3. Préparation du modèle pour la vue Thymeleaf
        model.addAttribute("type", type);
        model.addAttribute("agentId", agentId); // Important pour le champ caché dans le HTML
        model.addAttribute("agentName", agentName);

        String role = (String) session.getAttribute("role");
        String redirectUrl = "/agent-par/profil"; // Default

        if ("AGENT_PRO".equals(role)) {
            redirectUrl = "/agent-pro/profil";
        }
        model.addAttribute("redirectUrl", redirectUrl);

        return "vehicule/ajouter-vehicule";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteVehicule(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        // 1. Vérification de sécurité
        if (!isAgentLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("error", "Accès refusé.");
            return "redirect:/utilisateur/connexion";
        }

        try {
            // 2. Suppression
            // Optionnel : Vous pouvez vérifier ici si le véhicule appartient bien à l'agent connecté
            vehiculeService.deleteVehicule(id);
            redirectAttributes.addFlashAttribute("success", "Véhicule supprimé avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la suppression.");
        }

        // 3. Redirection selon le rôle
        String role = (String) session.getAttribute("role");
        if ("AGENT_PRO".equals(role)) {
            return "redirect:/agent-pro/profil";
        } else {
            return "redirect:/agent-par/profil";
        }
    }

    /**
     * Afficher le formulaire de modification.
     * Accessible uniquement aux agents.
     */
    @GetMapping("/formulaire/modifier/{type}/{id}")
    public String showEditForm(@PathVariable String type,
                               @PathVariable Long id,
                               HttpSession session,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (!isAgentLoggedIn(session)) {
            redirectAttributes.addFlashAttribute("error", "Accès refusé. Connectez-vous pour modifier.");
            return "redirect:/utilisateur/connexion";
        }

        model.addAttribute("type", type);
        model.addAttribute("id", id);

        String role = (String) session.getAttribute("role");
        String redirectUrl = "/agent-par/profil"; // Par défaut

        if ("AGENT_PRO".equals(role)) {
            redirectUrl = "/agent-pro/profil";
        }

        model.addAttribute("redirectUrl", redirectUrl);

        return "vehicule/modifier-vehicule";
    }

    /**
     * Afficher les détails d'un véhicule.
     * Accessible à tout le monde (pas de vérification de session requise).
     */
    @GetMapping("/details/{type}/{id}")
    public String showDetails(@PathVariable String type,
                              @PathVariable Long id,
                              Model model) {
        model.addAttribute("type", type);
        model.addAttribute("id", id);
        return "vehicule/vehicule-details";
    }
}