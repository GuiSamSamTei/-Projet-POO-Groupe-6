package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long > {

    Parking findByNomparking (String  nomparking);
    List<Parking> findByVille(String ville);



}
