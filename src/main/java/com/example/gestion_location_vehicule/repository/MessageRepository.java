package com.example.gestion_location_vehicule.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    // Tous les messages envoyés ou reçus par un utilisateur
    List<Message> findByUtilisateursendOrUtilisateurreceive(Utilisateur send, Utilisateur receive);

    // Tous les messages non lus pour un utilisateur
    List<Message> findByUtilisateurreceiveAndLuFalse(Utilisateur utilisateur);

    // Tous les messages envoyés par un utilisateur
    List<Message> findByUtilisateursend(Utilisateur send);

    // Tous les messages reçus par un utilisateur
    List<Message> findByUtilisateurreceive(Utilisateur receive);

    // Messages envoyés par un utilisateur après une certaine date
    List<Message> findByUtilisateursendAndDateenvoiAfter(Utilisateur send, Date date);

    // Messages reçus par un utilisateur après une certaine date
    List<Message> findByUtilisateurreceiveAndDateenvoiAfter(Utilisateur receive, Date date);

    // Messages envoyés ou reçus par un utilisateur dans une période
    List<Message> findByUtilisateursendOrUtilisateurreceiveAndDateenvoiBetween(Utilisateur send, Utilisateur receive, Date debut, Date fin);

    // Messages non lus envoyés par un utilisateur spécifique à un destinataire
    List<Message> findByUtilisateursendAndUtilisateurreceiveAndLuFalse(Utilisateur send, Utilisateur receive);

    // Conversation entre deux utilisateurs, ordonnée par date d'envoi
    List<Message> findByUtilisateursendAndUtilisateurreceiveOrUtilisateursendAndUtilisateurreceiveOrderByDateenvoi(
            Utilisateur send1, Utilisateur receive1,
            Utilisateur send2, Utilisateur receive2
    );

    // Compter les messages non lus pour un utilisateur
    long countByUtilisateurreceiveAndLuFalse(Utilisateur utilisateur);

    long countByUtilisateurreceiveAndUtilisateursendAndLuFalse(Utilisateur me, Utilisateur other);

    List<Message> findByUtilisateurreceiveAndUtilisateursendAndLuFalse(Utilisateur me, Utilisateur other);
}
