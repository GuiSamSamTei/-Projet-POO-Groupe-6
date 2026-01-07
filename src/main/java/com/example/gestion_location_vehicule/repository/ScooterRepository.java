package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    // Filtrer les scooters disponibles
    List<Scooter> findByVehiculeDispoTrue();

    // Filtrer par ville de disponibilité
    List<Scooter> findByVilleDispo(String ville);

    // Filtrer par cylindrée minimale ou maximale
    List<Scooter> findByCylindreeGreaterThanEqual(int cylindreeMin);
    List<Scooter> findByCylindreeLessThanEqual(int cylindreeMax);
    List<Scooter> findByCylindreeBetween(int min, int max);

    // Filtrer par type électrique ou non
    List<Scooter> findByElectriqueTrue();
    List<Scooter> findByElectriqueFalse();

    // Combinaison possible : ville + dispo + cylindrée
    List<Scooter> findByVilleDispoAndVehiculeDispoTrueAndCylindreeGreaterThanEqual(String ville, int cylindreeMin);

    // Combinaison possible : ville + dispo + électrique
    List<Scooter> findByVilleDispoAndVehiculeDispoTrueAndElectriqueTrue(String ville);
}
