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

        // 1. 通用字段
        camion.setMarque(request.getMarque());
        camion.setModele(request.getModele());
        camion.setPrixjour(request.getPrixjour());
        camion.setCouleur(request.getCouleur());
        camion.setVilledispo(request.getVilledispo());
        camion.setVehiculedispo(request.getVehiculedispo());

        // 注意：我们不再设置 notevehicule，默认为 0.0

        // 2. 卡车特有字段
        camion.setChargemax(request.getChargemax());
        camion.setVolume(request.getVolume());

        // 3. 关联 Agent
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
