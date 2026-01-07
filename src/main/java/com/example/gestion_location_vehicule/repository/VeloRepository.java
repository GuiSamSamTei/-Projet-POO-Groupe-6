package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Velo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeloRepository extends JpaRepository<Velo, Long> {

    // Vélos disponibles
    List<Velo> findByVehiculeDispoTrue();

    // Vélos par ville
    List<Velo> findByVilleDispo(String ville);

    // Vélos électriques
    List<Velo> findByElectriqueTrue();

    // Vélos par nombre de vitesses minimum
    List<Velo> findByNombreVitessesGreaterThanEqual(int vitessesMin);

    // Vélos par type exact (ex: route, VTT)
    List<Velo> findByTypeVelo(String typeVelo);

    // Vélos par agent propriétaire
    List<Velo> findByAgent(Agent agent);

    // Combinaison : disponible + ville + électrique
    List<Velo> findByVehiculeDispoTrueAndVilleDispoAndElectriqueTrue(String ville);

    // Combinaison : ville + vitesses minimum
    List<Velo> findByVilleDispoAndNombreVitessesGreaterThanEqual(String ville, int vitessesMin);
}
