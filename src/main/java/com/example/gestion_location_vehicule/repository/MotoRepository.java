package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Moto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {

    List<Moto> findByVehiculeDispoTrue();

    List<Moto> findByVilleDispo(String ville);
}
