package com.example.gestion_location_vehicule.controller;

import com.example.gestion_location_vehicule.model.Loueur;
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

    @GetMapping
    public List<Message> getAll() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Optional<Message> getById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return messageService.saveMessage(message);
    }

    @PutMapping("/{id}")
    public Message update(@PathVariable Long id, @RequestBody Message message) {
        message.setId(id);
        return messageService.saveMessage(message);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }

    @GetMapping("/utilisateur/{id}")
    public List<Message> getByUtilisateur(@PathVariable Long id) {
        Utilisateur user = new Loueur();
        return messageService.getMessagesByUtilisateur(user);
    }

    @GetMapping("/utilisateur/{id}/non-lus")
    public List<Message> getNonLus(@PathVariable Long id) {
        Utilisateur user = new Loueur();
        return messageService.getMessagesNonLus(user);
    }

    @GetMapping("/envoyes/{id}/apres")
    public List<Message> getEnvoyesAfter(
            @PathVariable Long id,
            @RequestParam Date date
    ) {
        Utilisateur user = new Loueur();
        return messageService.getMessagesEnvoyesAfter(user, date);
    }

    @GetMapping("/recus/{id}/apres")
    public List<Message> getRecusAfter(
            @PathVariable Long id,
            @RequestParam Date date
    ) {
        Utilisateur user = new Loueur();
        return messageService.getMessagesRecusAfter(user, date);
    }
}
