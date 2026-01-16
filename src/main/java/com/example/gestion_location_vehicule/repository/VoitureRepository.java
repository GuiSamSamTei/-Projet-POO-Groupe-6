package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    // disponible
    List<Voiture> findByVehiculedispoTrue();

    // Par ville
    List<Voiture> findByVilledispo(String ville);

    // Par ville et dispo
    List<Voiture> findByVilledispoAndVehiculedispoTrue(String ville);

    // Par nombre de portes
    List<Voiture> findByNombreportes(int nbPortes);

    List<Voiture> findByNombreportesGreaterThanEqual(int nbPortesMin);

    // Par nombre de places
    List<Voiture> findByNombreplaces(int nbPlaces);

    List<Voiture> findByNombreplacesGreaterThanEqual(int nbPlacesMin);

    // Automatique ou non
    List<Voiture> findByAutomatiqueTrue();

    List<Voiture> findByAutomatiqueFalse();

    // Par type de carburant
    List<Voiture> findByCarburant(String carburant);

    // Par coffre volume minimum
    List<Voiture> findByCoffrevolumeGreaterThanEqual(double volumeMin);

    // Par puissance (nb de chevaux)
    List<Voiture> findByNbchevauxGreaterThanEqual(int nbChevauxMin);

    // GPS
    List<Voiture> findByGpsTrue();

    List<Voiture> findByGpsFalse();

    // Filtrer par agent propriétaire
    List<Voiture> findByAgent(Agent agent);

    List<Voiture> findByVehiculedispoTrueAndVilledispoAndAutomatiqueTrue(String ville);

    List<Voiture> findByVilledispoAndNombreplacesGreaterThanEqualAndGpsTrue(String ville, int nbPlacesMin);
}
