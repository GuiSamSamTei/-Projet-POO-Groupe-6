package com.example.gestion_location_vehicule.service.MessageService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.repository.MessageRepository;

import jakarta.transaction.Transactional;

@Service
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Optional<Message> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<Message> getMessagesByUtilisateur(Utilisateur utilisateur) {
        return messageRepository.findByUtilisateursendOrUtilisateurreceive(utilisateur, utilisateur);
    }

    @Override
    public List<Message> getMessagesNonLus(Utilisateur utilisateur) {
        return messageRepository.findByUtilisateurreceiveAndLuFalse(utilisateur);
    }

    @Override
    public List<Message> getMessagesEnvoyes(Utilisateur utilisateur) {
        return messageRepository.findByUtilisateursend(utilisateur);
    }

    @Override
    public List<Message> getMessagesRecus(Utilisateur utilisateur) {
        return messageRepository.findByUtilisateurreceive(utilisateur);
    }

    @Override
    public List<Message> getMessagesEnvoyesAfter(Utilisateur utilisateur, Date date) {
        return messageRepository.findByUtilisateursendAndDateenvoiAfter(utilisateur, date);
    }

    @Override
    public List<Message> getMessagesRecusAfter(Utilisateur utilisateur, Date date) {
        return messageRepository.findByUtilisateurreceiveAndDateenvoiAfter(utilisateur, date);
    }

    @Override
    public List<Message> getMessagesBetween(Utilisateur send, Utilisateur receive, Date debut, Date fin) {
        return messageRepository.findByUtilisateursendOrUtilisateurreceiveAndDateenvoiBetween(send, receive, debut, fin);
    }

    @Override
    public List<Message> getMessagesNonLusBetween(Utilisateur send, Utilisateur receive) {
        return messageRepository.findByUtilisateursendAndUtilisateurreceiveAndLuFalse(send, receive);
    }

    @Override
    public Map<Utilisateur, List<Message>> getConversations(Utilisateur utilisateur) {

        List<Message> messages = getMessagesByUtilisateur(utilisateur);
        Map<Utilisateur, List<Message>> conversations = new HashMap<>();

        for (Message message : messages) {

            if (message.getUtilisateursend() == null || message.getUtilisateurreceive() == null) {
                continue;
            }

            Utilisateur interlocuteur
                    = message.getUtilisateursend().equals(utilisateur)
                    ? message.getUtilisateurreceive()
                    : message.getUtilisateursend();

            conversations
                    .computeIfAbsent(interlocuteur, k -> new ArrayList<>())
                    .add(message);
        }

        conversations.values().forEach(list
                -> list.sort(Comparator.comparing(Message::getDateenvoi))
        );

        return conversations;
    }

    @Override
    public List<Message> getConversation(Utilisateur a, Utilisateur b) {
        return messageRepository
                .findByUtilisateursendAndUtilisateurreceiveOrUtilisateursendAndUtilisateurreceiveOrderByDateenvoi(
                        a, b, b, a
                );
    }

    @Override
    public long countUnreadMessages(Utilisateur utilisateur) {
        return messageRepository.countByUtilisateurreceiveAndLuFalse(utilisateur);
    }

    @Override
    public long countUnreadMessagesWith(Utilisateur me, Utilisateur other) {
        return messageRepository
                .countByUtilisateurreceiveAndUtilisateursendAndLuFalse(me, other);
    }

    @Override
    @Transactional
    public void markConversationAsRead(Utilisateur me, Utilisateur other) {

        List<Message> messages =
                messageRepository
                        .findByUtilisateurreceiveAndUtilisateursendAndLuFalse(me, other);

        for (Message message : messages) {
            message.setLu(true);
        }
    }



}
