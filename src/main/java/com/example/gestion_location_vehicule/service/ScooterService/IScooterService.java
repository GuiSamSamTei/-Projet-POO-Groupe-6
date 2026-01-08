package com.example.gestion_location_vehicule.service.ScooterService;

import com.example.gestion_location_vehicule.model.Scooter;

import java.util.List;
import java.util.Optional;

public interface IScooterService {

    // 🔹 CRUD
    List<Scooter> getAllScooters();

    Optional<Scooter> getScooterById(Long id);

    Scooter saveScooter(Scooter scooter);

    void deleteScooter(Long id);

    // 🔹 Recherches spécifiques
    List<Scooter> getAvailableScooters();

    List<Scooter> getByVille(String ville);

    List<Scooter> getByCylindreeMin(int cylindreeMin);

    List<Scooter> getByCylindreeMax(int cylindreeMax);

    List<Scooter> getByCylindreeBetween(int min, int max);

    List<Scooter> getElectrique();

    List<Scooter> getNonElectrique();

    List<Scooter> getByVilleAndCylindreeMin(String ville, int cylindreeMin);

    List<Scooter> getByVilleAndElectrique(String ville);
}
