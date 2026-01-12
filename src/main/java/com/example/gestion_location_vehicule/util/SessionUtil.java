package com.example.gestion_location_vehicule.util;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.AgentPar;
import com.example.gestion_location_vehicule.model.AgentPro;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.service.UtilisateurService.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionUtil {

    private final UtilisateurService utilisateurService;

    public SessionUtil(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    public boolean isAgentLoggedIn(HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        String role = (String) session.getAttribute("role");

        return userId != null && (role != null &&
                (role.equals("AGENT_PAR") || role.equals("AGENT_PRO")));
    }

    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    public String getUserRole(HttpSession session) {
        return (String) session.getAttribute("role");
    }

    public Long getUserId(HttpSession session) {
        return (Long) session.getAttribute("user");
    }

    public Utilisateur getLoggedInUser(HttpSession session) {
        Long userId = getUserId(session);
        if (userId != null) {
            return utilisateurService.getUserbyID(userId);
        }
        return null;
    }

    public Agent getLoggedInAgent(HttpSession session) {
        Utilisateur user = getLoggedInUser(session);
        if (user instanceof Agent) {
            return (Agent) user;
        }
        return null;
    }
}