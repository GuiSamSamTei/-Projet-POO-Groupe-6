package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Van;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VanRepository extends JpaRepository<Van, Long> {

    List<Van> findByVehiculeDispoTrue();

    List<Van> findByVilleDispo(String ville);
}
