package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.service.MessageService.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // 🔹 GET : tous les messages
    @GetMapping
    public List<Message> getAll() {
        return messageService.getAllMessages();
    }

    // 🔹 GET : message par ID
    @GetMapping("/{id}")
    public Optional<Message> getById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    // 🔹 POST : créer un message
    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    // 🔹 PUT : mettre à jour un message
    @PutMapping("/{id}")
    public Message update(@PathVariable Long id, @RequestBody Message message) {
        message.setId(id);
        return messageService.saveMessage(message);
    }

    // 🔹 DELETE : supprimer un message
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }

    // 🔹 GET : messages envoyés ou reçus par un utilisateur
    @GetMapping("/utilisateur/{id}")
    public List<Message> getByUtilisateur(@PathVariable Long id) {
        Utilisateur user = new Utilisateur();
        return messageService.getMessagesByUtilisateur(user);
    }

    // 🔹 GET : messages non lus pour un utilisateur
    @GetMapping("/utilisateur/{id}/non-lus")
    public List<Message> getNonLus(@PathVariable Long id) {
        Utilisateur user = new Utilisateur();
        return messageService.getMessagesNonLus(user);
    }

    // 🔹 GET : messages envoyés après une date
    @GetMapping("/envoyes/{id}/apres")
    public List<Message> getEnvoyesAfter(
            @PathVariable Long id,
            @RequestParam Date date
    ) {
        Utilisateur user = new Utilisateur();
        return messageService.getMessagesEnvoyesAfter(user, date);
    }

    // 🔹 GET : messages reçus après une date
    @GetMapping("/recus/{id}/apres")
    public List<Message> getRecusAfter(
            @PathVariable Long id,
            @RequestParam Date date
    ) {
        Utilisateur user = new Utilisateur();
        return messageService.getMessagesRecusAfter(user, date);
    }
}
