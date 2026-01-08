package com.example.gestion_location_vehicule.service.VoitureService;

import com.example.gestion_location_vehicule.model.Voiture;
import com.example.gestion_location_vehicule.repository.VoitureRepository;
import com.example.gestion_location_vehicule.request.VoitureRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class VoitureService implements IVoitureService{
    private final VoitureRepository voitureRepository;
    @Override
    public List<Voiture> getAllVoiture() {
        return voitureRepository.findAll();
    }

    @Override
    public Voiture ajouterVoiture(VoitureRequest voitureRequest) {
        Voiture voiture = new Voiture();

        voiture.setAutomatique(voitureRequest.getAutomatique());
        voiture.setCarburant(voitureRequest.getCarburant());
        voiture.setCoffrevolume(voitureRequest.getCoffrevolume());
        voiture.setNbchevaux(voitureRequest.getNbchevaux());
        voiture.setGps(voitureRequest.getGps());
        voiture.setNombreplaces(voitureRequest.getNombreplaces());
        voiture.setNombreportes(voitureRequest.getNombreportes());
    }

    @Override
    public Voiture modifierVoiture(VoitureRequest veloRequest, Long voiture_id) {
        return null;
    }

    @Override
    public void supprimerVoiture(Long voiture_id) {

    }
}
