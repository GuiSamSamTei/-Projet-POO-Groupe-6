package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    // Filtrer les motos disponibles
    List<Moto> findByVehiculeDispoTrue();

    // Filtrer par ville de disponibilité
    List<Moto> findByVilleDispo(String ville);

    // Filtrer par cylindrée minimale ou maximale
    List<Moto> findByCylindreeGreaterThanEqual(int cylindreeMin);
    List<Moto> findByCylindreeLessThanEqual(int cylindreeMax);
    List<Moto> findByCylindreeBetween(int min, int max);

    // Filtrer par nombre de chevaux
    List<Moto> findByNbchevauxGreaterThanEqual(int nbChevauxMin);
    List<Moto> findByNbchevauxLessThanEqual(int nbChevauxMax);
    List<Moto> findByNbchevauxBetween(int min, int max);

    // Combinaison possible : ville + dispo + cylindrée
    List<Moto> findByVilleDispoAndVehiculeDispoTrueAndCylindreeGreaterThanEqual(String ville, int cylindreeMin);

    // Combinaison possible : ville + dispo + nbchevaux
    List<Moto> findByVilleDispoAndVehiculeDispoTrueAndNbchevauxGreaterThanEqual(String ville, int nbChevauxMin);
}
