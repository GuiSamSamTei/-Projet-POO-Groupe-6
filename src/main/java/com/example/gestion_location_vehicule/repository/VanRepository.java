package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Van;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VanRepository extends JpaRepository<Van, Long> {

    // Filtrer les vans disponibles
    List<Van> findByVehiculeDispoTrue();

    // Filtrer par ville de disponibilité
    List<Van> findByVilleDispo(String ville);

    // Filtrer par nombre de places minimum
    List<Van> findByNombreplacesGreaterThanEqual(int minPlaces);

    // Filtrer par nombre de places maximum
    List<Van> findByNombreplacesLessThanEqual(int maxPlaces);

    // Filtrer par nombre de places dans une plage
    List<Van> findByNombreplacesBetween(int minPlaces, int maxPlaces);

    // Combinaison : van disponible dans une ville avec nombre de places minimum
    List<Van> findByVehiculeDispoTrueAndVilleDispoAndNombreplacesGreaterThanEqual(
            String ville, int minPlaces
    );
}
