package com.example.gestion_location_vehicule.service.UtilisateurService;

import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import com.example.gestion_location_vehicule.request.ConnexionRequest;
import org.apache.catalina.User;

public class UtilisateurService implements IUtilisateurService{

    private final UtilisateurRepository utilisateurRepository;
    @Override
    public Boolean connexionUser(ConnexionRequest connexionRequest) {
        if (connexionRequest==null)
            return false;

        Utilisateur utilisateur = utilisateurRepository.findByUsername(connexionRequest.getUsername());

        if(utilisateur==null)
            return false;

        if(utilisateur.getMdp()==connexionRequest.getPassword())
            return true;
        else
            return false;
    }

}
