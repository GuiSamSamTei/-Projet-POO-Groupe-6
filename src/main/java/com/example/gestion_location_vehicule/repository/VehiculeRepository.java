package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    // Filtrer les véhicules disponibles
    List<Vehicule> findByVehiculedispoTrue();

    // Filtrer par ville de disponibilité
    List<Vehicule> findByVilledispo(String villeDispo);

    // Filtrer par marque exacte
    List<Vehicule> findByMarque(String marque);

    // Filtrer par modèle exact
    List<Vehicule> findByModele(String modele);

    // Filtrer par marque ou modèle partiel (insensible à la casse)
    List<Vehicule> findByMarqueContainingIgnoreCaseOrModeleContainingIgnoreCase(String marque, String modele);

    // Filtrer par note minimum
    List<Vehicule> findByNotevehiculeGreaterThanEqual(int noteMin);

    // Filtrer par note maximum
    List<Vehicule> findByNotevehiculeLessThanEqual(int noteMax);

    // Filtrer par kilométrage maximum
    List<Vehicule> findByKilometrageLessThanEqual(double kmMax);

    // Filtrer par agent propriétaire
    List<Vehicule> findByAgent(Agent agent);

    // Combinaison : disponible + ville + note minimum
    List<Vehicule> findByVehiculedispoTrueAndVilledispoAndNotevehiculeGreaterThanEqual(String ville, int noteMin);

    List<Vehicule> findByAgentId(Long agent_id);
}
