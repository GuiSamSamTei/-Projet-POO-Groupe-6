package com.example.gestion_location_vehicule.service.UtilisateurService;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.request.ConnexionRequest;
import com.example.gestion_location_vehicule.request.UtilisateurRequest;

public interface IUtilisateurService{

    long connexionUser(ConnexionRequest connexionRequest);


}
