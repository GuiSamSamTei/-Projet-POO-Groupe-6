package com.example.gestion_location_vehicule.service.CamionService;

import com.example.gestion_location_vehicule.model.Camion;
import com.example.gestion_location_vehicule.repository.CamionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CamionService implements ICamionService {

    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

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
