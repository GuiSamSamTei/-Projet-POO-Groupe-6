package com.example.gestion_location_vehicule.service.MessageService;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;

public interface IMessageService {

    // 🔹 CRUD
    List<Message> getAllMessages();

    Optional<Message> getMessageById(Long id);

    Message saveMessage(Message message);

    void deleteMessage(Long id);

    // 🔹 Recherches spécifiques
    List<Message> getMessagesByUtilisateur(Utilisateur utilisateur);

    List<Message> getMessagesNonLus(Utilisateur utilisateur);

    List<Message> getMessagesEnvoyes(Utilisateur utilisateur);

    List<Message> getMessagesRecus(Utilisateur utilisateur);

    List<Message> getMessagesEnvoyesAfter(Utilisateur utilisateur, Date date);

    List<Message> getMessagesRecusAfter(Utilisateur utilisateur, Date date);

    List<Message> getMessagesBetween(Utilisateur send, Utilisateur receive, Date debut, Date fin);

    List<Message> getMessagesNonLusBetween(Utilisateur send, Utilisateur receive);

    Map<Utilisateur, List<Message>> getConversations(Utilisateur utilisateur);

    List<Message> getConversation(Utilisateur a, Utilisateur b);
}
