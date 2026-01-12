package com.example.gestion_location_vehicule.service.ParkingService;


import com.example.gestion_location_vehicule.model.Parking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IParkingService {

    Parking touverParkingparNom (String nom);
    Parking trouverParkingparId(Long id);
    List<Parking> trouverParkingparVille (String ville);
}
