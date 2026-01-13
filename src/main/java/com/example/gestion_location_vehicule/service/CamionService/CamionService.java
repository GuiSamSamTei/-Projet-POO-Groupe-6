package com.example.gestion_location_vehicule.service.CamionService;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Camion;
import com.example.gestion_location_vehicule.repository.AgentRepository;
import com.example.gestion_location_vehicule.repository.CamionRepository;
import com.example.gestion_location_vehicule.request.CamionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CamionService implements ICamionService {

    private final CamionRepository camionRepository;
    private final AgentRepository agentRepository;


    // 🔹 CRUD

    @Override
    public List<Camion> getAllCamions() {
        return camionRepository.findAll();
    }

    @Override
    public Optional<Camion> getCamionById(Long id) {
        return camionRepository.findById(id);
    }

    @Override
    public Camion saveCamion(Camion camion) {
        return camionRepository.save(camion);
    }
    public Camion ajouterCamion(CamionRequest request) {
        Camion camion = new Camion();

        camion.setMarque(request.getMarque());
        camion.setModele(request.getModele());
        camion.setPrixjour(request.getPrixjour());
        camion.setCouleur(request.getCouleur());
        camion.setVilledispo(request.getVilledispo());
        camion.setVehiculedispo(request.getVehiculedispo());

        camion.setChargemax(request.getChargemax());
        camion.setVolume(request.getVolume());

        if (request.getAgent_id() != null) {
            Optional<Agent> agentOptional = agentRepository.findById(request.getAgent_id());
            if (agentOptional.isPresent()) {
                camion.setAgent(agentOptional.get());
            } else {
                throw new IllegalArgumentException("Agent non trouvé avec l'ID : " + request.getAgent_id());
            }
        }

        return camionRepository.save(camion);
    }

    public Camion modifierCamion(Long id, CamionRequest request) {
        return camionRepository.findById(id).map(camion -> {
            // Champs communs
            if (request.getMarque() != null) camion.setMarque(request.getMarque());
            if (request.getModele() != null) camion.setModele(request.getModele());
            if (request.getPrixjour() != 0) camion.setPrixjour(request.getPrixjour());
            if (request.getCouleur() != null) camion.setCouleur(request.getCouleur());
            if (request.getVilledispo() != null) camion.setVilledispo(request.getVilledispo());
            if (request.getVehiculedispo() != null) camion.setVehiculedispo(request.getVehiculedispo());

            // Champs spécifiques Camion
            if (request.getChargemax() != 0) camion.setChargemax(request.getChargemax());
            if (request.getVolume() != 0) camion.setVolume(request.getVolume());

            return camionRepository.save(camion);
        }).orElseThrow(() -> new IllegalArgumentException("Camion non trouvé avec l'ID " + id));
    }

    @Override
    public void deleteCamion(Long id) {
        camionRepository.deleteById(id);
    }

    // 🔹 Recherches spécifiques

    @Override
    public List<Camion> getCamionsDispo() {
        return camionRepository.findByVehiculedispoTrue();
    }

    @Override
    public List<Camion> getCamionsByVille(String ville) {
        return camionRepository.findByVilledispo(ville);
    }

    @Override
    public List<Camion> getCamionsByChargemax(double minCharge) {
        return camionRepository.findByChargemaxGreaterThanEqual(minCharge);
    }

    @Override
    public List<Camion> getCamionsByVolume(double minVolume) {
        return camionRepository.findByVolumeGreaterThanEqual(minVolume);
    }

    @Override
    public List<Camion> getCamionsByVilleDispoAndCharge(String ville, double minCharge) {
        return camionRepository.findByVilledispoAndVehiculedispoTrueAndChargemaxGreaterThanEqual(ville, minCharge);
    }
}
