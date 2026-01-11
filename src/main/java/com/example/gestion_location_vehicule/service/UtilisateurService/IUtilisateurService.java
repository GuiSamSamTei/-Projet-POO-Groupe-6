package com.example.gestion_location_vehicule.service.UtilisateurService;

import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.request.ConnexionRequest;

import java.util.Optional;

public interface IUtilisateurService{

    Optional<Utilisateur> connexionUser(ConnexionRequest connexionRequest);
    Utilisateur getUserbyID (Long id);


}
