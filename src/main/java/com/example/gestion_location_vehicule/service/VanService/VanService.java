package com.example.gestion_location_vehicule.service.VanService;

import com.example.gestion_location_vehicule.model.Van;
import com.example.gestion_location_vehicule.repository.VanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VanService implements IVanService {

    private final VanRepository vanRepository;

    public VanService(VanRepository vanRepository) {
        this.vanRepository = vanRepository;
    }

    // 🔹 CRUD
    @Override
    public List<Van> getAllVans() {
        return vanRepository.findAll();
    }

    @Override
    public Optional<Van> getVanById(Long id) {
        return vanRepository.findById(id);
    }

    @Override
    public Van saveVan(Van van) {
        return vanRepository.save(van);
    }

    @Override
    public void deleteVan(Long id) {
        vanRepository.deleteById(id);
    }

    // 🔹 Recherches spécifiques
    @Override
    public List<Van> getVansDispo() {
        return vanRepository.findByVehiculedispoTrue();
    }

    @Override
    public List<Van> getVansByVille(String ville) {
        return vanRepository.findByVilledispo(ville);
    }

    @Override
    public List<Van> getVansByNombrePlacesMin(int minPlaces) {
        return vanRepository.findByNombreplacesGreaterThanEqual(minPlaces);
    }

    @Override
    public List<Van> getVansByNombrePlacesMax(int maxPlaces) {
        return vanRepository.findByNombreplacesLessThanEqual(maxPlaces);
    }

    @Override
    public List<Van> getVansByNombrePlacesBetween(int minPlaces, int maxPlaces) {
        return vanRepository.findByNombreplacesBetween(minPlaces, maxPlaces);
    }

    @Override
    public List<Van> getVansByVilleDispoEtMinPlaces(String ville, int minPlaces) {
        return vanRepository.findByVehiculedispoTrueAndVilledispoAndNombreplacesGreaterThanEqual(ville, minPlaces);
    }
}
