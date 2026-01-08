package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {

    List<Camion> findByVehiculedispoTrue();

    List<Camion> findByVilledispo(String ville);

    List<Camion> findByChargemaxGreaterThanEqual(double chargeMin);

    List<Camion> findByVolumeGreaterThanEqual(double volumeMin);

    List<Camion> findByVilledispoAndVehiculedispoTrueAndChargemaxGreaterThanEqual(String ville, double chargeMin);
}
