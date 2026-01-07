package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Velo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeloRepository extends JpaRepository<Velo, Long> {

    List<Velo> findByVehiculeDispoTrue();

    List<Velo> findByVilleDispo(String ville);
}
