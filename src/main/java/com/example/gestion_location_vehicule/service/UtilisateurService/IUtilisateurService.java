package com.example.gestion_location_vehicule.service.UtilisateurService;

import com.example.gestion_location_vehicule.model.Message;
import com.example.gestion_location_vehicule.request.ConnexionRequest;

public interface IUtilisateurService{

    Boolean connexionUser(ConnexionRequest connexionRequest);

}
