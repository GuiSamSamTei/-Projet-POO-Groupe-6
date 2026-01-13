package com.example.gestion_location_vehicule.service.ParkingService;


import com.example.gestion_location_vehicule.model.Parking;
import com.example.gestion_location_vehicule.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService implements IParkingService{

    private final ParkingRepository parkingRepository;

    @Override
    public List<Parking> getAllParking() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking touverParkingparNom(String nom) {
        return parkingRepository.findByNomparking(nom);
    }

    @Override
    public Parking trouverParkingparId(Long id) {
        return parkingRepository.findById(id).get();
    }

    @Override
    public List<Parking> trouverParkingparVille(String ville) {
        return parkingRepository.findByVille(ville);
    }
}
