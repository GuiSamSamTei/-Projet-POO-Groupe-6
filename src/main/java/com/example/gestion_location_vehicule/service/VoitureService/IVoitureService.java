package com.example.gestion_location_vehicule.service.VoitureService;


import com.example.gestion_location_vehicule.model.Voiture;
import com.example.gestion_location_vehicule.request.VoitureRequest;

import java.util.List;

public interface IVoitureService {
    List<Voiture> getAllVoiture();
    Voiture ajouterVoiture(VoitureRequest voitureRequest);
    Voiture modifierVoiture(VoitureRequest veloRequest, Long voiture_id);
    void supprimerVoiture(Long voiture_id);
}
