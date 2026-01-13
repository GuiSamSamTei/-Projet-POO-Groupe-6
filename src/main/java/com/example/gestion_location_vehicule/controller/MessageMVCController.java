package com.example.gestion_location_vehicule.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import com.example.gestion_location_vehicule.service.MessageService.IMessageService;

import jakarta.servlet.http.HttpSession;

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

    /* ============================
       Page messagerie + recherche
       ============================ */
    @GetMapping
    public String messagerie(@RequestParam(required = false) String q,
                             Model model,
                             HttpSession session) {

        Long idMe = (Long) session.getAttribute("user");
        if (idMe == null) {
            return "redirect:/utilisateur/connexion";
        }

        Utilisateur me = utilisateurRepository.findById(idMe).orElseThrow();

        // Conversations existantes
        Map<Utilisateur, List<Message>> conversations =
                messageService.getConversations(me);

        // Recherche utilisateurs (hors soi-même)
        if (q != null && !q.isBlank()) {
            List<Utilisateur> resultats =
                    utilisateurRepository.findByUsernameContainingIgnoreCaseAndIsAdminFalse(q);

            resultats.removeIf(u -> u.getId().equals(me.getId()));
            model.addAttribute("resultatsRecherche", resultats);
            model.addAttribute("q", q);
        }

        // Non-lus par conversation
        Map<Long, Long> unreadByUser = new HashMap<>();
        for (Utilisateur other : conversations.keySet()) {
            unreadByUser.put(
                    other.getId(),
                    messageService.countUnreadMessagesWith(me, other)
            );
        }

        model.addAttribute("conversations", conversations);
        model.addAttribute("unreadByUser", unreadByUser);
        model.addAttribute("totalUnread",
                messageService.countUnreadMessages(me));

        return "messages/messagerie";
    }

    /* ============================
       Ouvrir une conversation
       ============================ */
    @GetMapping("/{id}")
    public String messagerieAvecConversation(@PathVariable Long id,
                                             Model model,
                                             HttpSession session) {

        Long idMe = (Long) session.getAttribute("user");
        if (idMe == null) {
            return "redirect:/utilisateur/connexion";
        }

        Utilisateur me = utilisateurRepository.findById(idMe).orElseThrow();
        Utilisateur other = utilisateurRepository.findById(id).orElseThrow();

        if (me.getId().equals(other.getId())) {
            return "redirect:/messages";
        }

        messageService.markConversationAsRead(me, other);

        Map<Utilisateur, List<Message>> conversations =
                messageService.getConversations(me);

        // Ajouter l'utilisateur même sans historique
        conversations.putIfAbsent(other, new ArrayList<>());

        Map<Long, Long> unreadByUser = new HashMap<>();
        for (Utilisateur u : conversations.keySet()) {
            unreadByUser.put(
                    u.getId(),
                    messageService.countUnreadMessagesWith(me, u)
            );
        }

        model.addAttribute("conversations", conversations);
        model.addAttribute("unreadByUser", unreadByUser);
        model.addAttribute("totalUnread",
                messageService.countUnreadMessages(me));

        model.addAttribute("other", other);
        model.addAttribute("messages",
                messageService.getConversation(me, other));

        return "messages/messagerie";
    }

    /* ============================
       Envoyer un message
       ============================ */
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
