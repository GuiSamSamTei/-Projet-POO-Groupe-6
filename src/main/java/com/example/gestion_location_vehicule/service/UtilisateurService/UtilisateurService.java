package com.example.gestion_location_vehicule.service.UtilisateurService;

import com.example.gestion_location_vehicule.model.Utilisateur;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import com.example.gestion_location_vehicule.request.ConnexionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements IUtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public Boolean connexionUser(ConnexionRequest connexionRequest) {

        Optional<Utilisateur> utilisateurOpt =
                utilisateurRepository.findByUsername(connexionRequest.getUsername());

        if (utilisateurOpt.isEmpty()) {
            return false;
        }

        Utilisateur utilisateur = utilisateurOpt.get();

        return utilisateur.getMdp().equals(connexionRequest.getPassword());
    }
}
