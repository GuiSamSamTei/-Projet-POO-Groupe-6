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

    @GetMapping
    public String messagerie(@RequestParam(required = false) String q,
                             Model model,
                             HttpSession session) {

        Long idMe = (Long) session.getAttribute("user");
        if (idMe == null) {
            return "redirect:/utilisateur/connexion";
        }

        Utilisateur me = utilisateurRepository.findById(idMe).orElseThrow();

        Map<Utilisateur, List<Message>> conversations =
                messageService.getConversations(me);

        if (q != null && !q.isBlank()) {
            List<Utilisateur> resultats =
                    utilisateurRepository.findByUsernameContainingIgnoreCase(q);

            resultats.removeIf(u -> u.getId().equals(me.getId()));
            model.addAttribute("resultatsRecherche", resultats);
            model.addAttribute("q", q);
        }

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

    @PostMapping("/envoyer")
    public String envoyerMessage(@RequestParam Long destinataireId,
                                 @RequestParam String contenu,
                                 HttpSession session) {

        Long senderId = (Long) session.getAttribute("user");
        if (senderId == null) {
            return "redirect:/utilisateur/connexion";
        }

        if (contientEmailOuTelephone(contenu)) {
            return "redirect:/messages/" + destinataireId + "?error=contact_interdit";
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

    private boolean contientEmailOuTelephone(String texte) {
        String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b";
        String phoneRegex = "\\b(\\+?\\d{1,3}[\\s.-]?)?(\\(?\\d{1,4}\\)?[\\s.-]?){2,}\\d{2,4}\\b";

        return texte.matches(".*(" + emailRegex + "|" + phoneRegex + ").*");
    }

}
