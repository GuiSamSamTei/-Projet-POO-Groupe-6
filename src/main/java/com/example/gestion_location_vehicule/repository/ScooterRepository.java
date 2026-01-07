package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Scooter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScooterRepository extends JpaRepository<Scooter, Long> {

    List<Scooter> findByVehiculeDispoTrue();

    List<Scooter> findByVilleDispo(String ville);
}
