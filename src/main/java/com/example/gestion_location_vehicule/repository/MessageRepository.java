package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByUtilisateursendOrUtilisateurreceive(Utilisateur send, Utilisateur receive);

    List<Message> findByUtilisateurreceiveAndLuFalse(Utilisateur utilisateur);
}
