package com.example.gestion_location_vehicule.service.VanService;

import com.example.gestion_location_vehicule.model.Van;

import java.util.List;
import java.util.Optional;

public interface IVanService {

    List<Van> getAllVans();

    Optional<Van> getVanById(Long id);

    Van saveVan(Van van);

    void deleteVan(Long id);

    List<Van> getVansDispo();

    List<Van> getVansByVille(String ville);

    List<Van> getVansByNombrePlacesMin(int minPlaces);

    List<Van> getVansByNombrePlacesMax(int maxPlaces);

    List<Van> getVansByNombrePlacesBetween(int minPlaces, int maxPlaces);

    List<Van> getVansByVilleDispoEtMinPlaces(String ville, int minPlaces);
}
