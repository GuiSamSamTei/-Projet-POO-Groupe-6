package com.example.gestion_location_vehicule.service.MessageService;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // 🔹 CRUD
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

    // 🔹 Recherches spécifiques
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
}
