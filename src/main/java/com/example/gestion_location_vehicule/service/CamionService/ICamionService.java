package com.example.gestion_location_vehicule.service.CamionService;

import com.example.gestion_location_vehicule.model.Camion;

import java.util.List;
import java.util.Optional;

public interface ICamionService {

    // CRUD
    List<Camion> getAllCamions();

    Optional<Camion> getCamionById(Long id);

    Camion saveCamion(Camion camion);

    void deleteCamion(Long id);

    // 🔹 Recherches spécifiques
    List<Camion> getCamionsDispo();

    List<Camion> getCamionsByVille(String ville);

    List<Camion> getCamionsByChargemax(double minCharge);

    List<Camion> getCamionsByVolume(double minVolume);

    List<Camion> getCamionsByVilleDispoAndCharge(String ville, double minCharge);
}
