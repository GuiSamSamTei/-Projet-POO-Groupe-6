package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    List<Vehicule> findByVehiculeDispoTrue();

    List<Vehicule> findByVilleDispo(String villeDispo);
}
