package com.example.gestion_location_vehicule.service.ScooterService;

import com.example.gestion_location_vehicule.model.Scooter;
import com.example.gestion_location_vehicule.repository.ScooterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScooterService implements IScooterService {

    private final ScooterRepository scooterRepository;

    public ScooterService(ScooterRepository scooterRepository) {
        this.scooterRepository = scooterRepository;
    }

    // 🔹 CRUD
    @Override
    public List<Scooter> getAllScooters() {
        return scooterRepository.findAll();
    }

    @Override
    public Optional<Scooter> getScooterById(Long id) {
        return scooterRepository.findById(id);
    }

    @Override
    public Scooter saveScooter(Scooter scooter) {
        return scooterRepository.save(scooter);
    }

    @Override
    public void deleteScooter(Long id) {
        scooterRepository.deleteById(id);
    }

    // 🔹 Recherches spécifiques
    @Override
    public List<Scooter> getAvailableScooters() {
        return scooterRepository.findByVehiculedispoTrue();
    }

    @Override
    public List<Scooter> getByVille(String ville) {
        return scooterRepository.findByVilledispo(ville);
    }

    @Override
    public List<Scooter> getByCylindreeMin(int cylindreeMin) {
        return scooterRepository.findByCylindreeGreaterThanEqual(cylindreeMin);
    }

    @Override
    public List<Scooter> getByCylindreeMax(int cylindreeMax) {
        return scooterRepository.findByCylindreeLessThanEqual(cylindreeMax);
    }

    @Override
    public List<Scooter> getByCylindreeBetween(int min, int max) {
        return scooterRepository.findByCylindreeBetween(min, max);
    }

    @Override
    public List<Scooter> getElectrique() {
        return scooterRepository.findByElectriqueTrue();
    }

    @Override
    public List<Scooter> getNonElectrique() {
        return scooterRepository.findByElectriqueFalse();
    }

    @Override
    public List<Scooter> getByVilleAndCylindreeMin(String ville, int cylindreeMin) {
        return scooterRepository.findByVilledispoAndVehiculedispoTrueAndCylindreeGreaterThanEqual(ville, cylindreeMin);
    }

    @Override
    public List<Scooter> getByVilleAndElectrique(String ville) {
        return scooterRepository.findByVilledispoAndVehiculedispoTrueAndElectriqueTrue(ville);
    }
}
