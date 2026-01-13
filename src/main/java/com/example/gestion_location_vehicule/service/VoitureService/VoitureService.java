package com.example.gestion_location_vehicule.service.VoitureService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.repository.AgentRepository;
import com.example.gestion_location_vehicule.model.Voiture;
import com.example.gestion_location_vehicule.repository.VoitureRepository;
import com.example.gestion_location_vehicule.request.VoitureRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class VoitureService implements IVoitureService{
    private final VoitureRepository voitureRepository;
    private final AgentRepository agentRepository;
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
        voiture.setMarque(voitureRequest.getMarque());
        voiture.setModele(voitureRequest.getModele());
        voiture.setPrixjour(voitureRequest.getPrixjour());
        voiture.setCouleur(voitureRequest.getCouleur());
        voiture.setVehiculedispo(voitureRequest.getVehiculedispo());
        voiture.setVilledispo(voitureRequest.getVilledispo());

        if (voitureRequest.getAgent_id() != null) {
            Optional<Agent> agentOptional = agentRepository.findById(voitureRequest.getAgent_id());
            if (agentOptional.isPresent()) {
                voiture.setAgent(agentOptional.get());
            } else {
                System.out.println("Attention : il n'y a pas un agent avec id :  " + voitureRequest.getAgent_id());
            }
        }

        return  voitureRepository.save(voiture);
    }

    @Override
    public Voiture modifierVoiture(VoitureRequest voitureRequest, Long voiture_id) {
        Optional<Voiture> voitureOptional = voitureRepository.findById(voiture_id);

        if (voitureOptional.isEmpty()) {
            throw new IllegalArgumentException("Voiture non trouvée avec l'ID: " + voitureRequest.getAgent_id());
        }

        Voiture voitureExistante = voitureOptional.get();
        if (voitureRequest.getPrixjour() != 0) {
            voitureExistante.setPrixjour(voitureRequest.getPrixjour());
        }

        if (voitureRequest.getVilledispo() != null) {
            voitureExistante.setVilledispo(voitureRequest.getVilledispo());
        }

        if (voitureRequest.getVehiculedispo() != null) {
            voitureExistante.setVehiculedispo(voitureRequest.getVehiculedispo());
        }


        if (voitureRequest.getMarque() != null) {
            voitureExistante.setMarque(voitureRequest.getMarque());
        }

        if (voitureRequest.getModele() != null) {
            voitureExistante.setModele(voitureRequest.getModele());
        }

        if (voitureRequest.getCouleur() != null) {
            voitureExistante.setCouleur(voitureRequest.getCouleur());
        }

        if (voitureRequest.getNombreportes() != 0) {
            voitureExistante.setNombreportes(voitureRequest.getNombreportes());
        }

        if (voitureRequest.getNombreplaces() != 0) {
            voitureExistante.setNombreplaces(voitureRequest.getNombreplaces());
        }

        if (voitureRequest.getAutomatique() != null) {
            voitureExistante.setAutomatique(voitureRequest.getAutomatique());
        }

        if (voitureRequest.getCarburant() != null) {
            voitureExistante.setCarburant(voitureRequest.getCarburant());
        }

        if (voitureRequest.getCoffrevolume() != 0) {
            voitureExistante.setCoffrevolume(voitureRequest.getCoffrevolume());
        }

        if (voitureRequest.getNbchevaux() != 0) {
            voitureExistante.setNbchevaux(voitureRequest.getNbchevaux());
        }

        if (voitureRequest.getGps() != null) {
            voitureExistante.setGps(voitureRequest.getGps());
        }

        return voitureRepository.save(voitureExistante);
    }

    @Override
    public void supprimerVoiture(Long voiture_id) {

        voitureRepository.deleteById(voiture_id);

    }

    public Optional<Voiture> getVoitureById(Long id) {
        return voitureRepository.findById(id);
    }
}
