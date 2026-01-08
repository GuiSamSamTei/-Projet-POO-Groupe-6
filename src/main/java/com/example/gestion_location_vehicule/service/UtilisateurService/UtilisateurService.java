package com.example.gestion_location_vehicule.service.UtilisateurService;

import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import com.example.gestion_location_vehicule.request.ConnexionRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UtilisateurService implements IUtilisateurService{

    private final UtilisateurRepository utilisateurRepository;
    @Override
    public Boolean connexionUser(ConnexionRequest connexionRequest) {
        if (connexionRequest==null)
            return false;

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByUsername(connexionRequest.getUsername());


        if(utilisateurOptional.isEmpty())
            return false;

        Utilisateur utilisateur = utilisateurOptional.get();
        if(utilisateur.getMdp()==connexionRequest.getPassword())
            return true;
        else
            return false;
    }

}
