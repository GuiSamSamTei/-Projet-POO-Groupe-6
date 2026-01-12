package com.example.gestion_location_vehicule.controller;

import java.util.Date;

import org.hibernate.metamodel.mapping.internal.IdClassEmbeddable;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import com.example.gestion_location_vehicule.service.MessageService.IMessageService;

@Controller
@RequestMapping("/messages")
public class MessageMVCController {

    private final UtilisateurRepository utilisateurRepository;
    private final IMessageService messageService;

    public MessageMVCController(UtilisateurRepository utilisateurRepository,
                                IMessageService messageService) {
        this.utilisateurRepository = utilisateurRepository;
        this.messageService = messageService;
    }

    // 🔹 Page de recherche d'utilisateurs
    @GetMapping("/nouveau")
    public String rechercherUtilisateur(@RequestParam(required = false) String q,
                                       Model model,
                                       HttpSession session) {

        Long idMe = (Long) session.getAttribute("user");
        if (idMe == null) {
            return "redirect:/utilisateur/connexion";
        }

        Utilisateur user = utilisateurRepository.findById(idMe).orElseThrow();

        if (q != null && !q.isBlank()) {
            model.addAttribute("resultats",
                    utilisateurRepository.findByUsernameContainingIgnoreCase(q));
        }
        return "messages/recherche";
    }

    // 🔹 Liste des conversations
    @GetMapping
    public String conversations(Model model, HttpSession session) {

        Long idMe = (Long) session.getAttribute("user");
        if (idMe == null) {
            return "redirect:/utilisateur/connexion";
        }

        Utilisateur user = utilisateurRepository.findById(idMe).orElseThrow();
        model.addAttribute("conversations", messageService.getConversations(user));
        return "messages/index";
    }

    // 🔹 Afficher conversation avec un utilisateur
    @GetMapping("/{id}")
    public String conversation(@PathVariable Long id,
                               Model model,
                               HttpSession session) {

        Long idMe = (Long) session.getAttribute("user");
        if (idMe == null) {
            return "redirect:/utilisateur/connexion";
        }

        Utilisateur me = utilisateurRepository.findById(idMe).orElseThrow();

        Utilisateur other = utilisateurRepository.findById(id).orElseThrow();

        // Empêcher de s'écrire à soi-même
        if (me.getId().equals(other.getId())) {
            return "redirect:/messages";
        }

        model.addAttribute("other", other);
        model.addAttribute("messages",
                messageService.getConversation(me, other));

        return "messages/conversation";
    }

    // 🔹 Envoyer un message
    @PostMapping("/envoyer")
    public String envoyerMessage(@RequestParam Long destinataireId,
                                 @RequestParam String contenu,
                                 HttpSession session) {

        Long senderId = (Long) session.getAttribute("user");
        if (senderId == null) {
            return "redirect:/utilisateur/connexion";
        }

        Utilisateur sender = utilisateurRepository.findById(senderId).orElseThrow();

        Utilisateur receiver = utilisateurRepository.findById(destinataireId).orElseThrow();

        // Empêcher de s'écrire à soi-même
        if (sender.getId().equals(receiver.getId())) {
            return "redirect:/messages";
        }

        Message message = new Message();
        message.setUtilisateursend(sender);
        message.setUtilisateurreceive(receiver);
        message.setContenu(contenu);
        message.setDateenvoi(new Date());
        message.setLu(false);

        messageService.saveMessage(message);

        return "redirect:/messages/" + receiver.getId();
    }
}
