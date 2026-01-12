package com.example.gestion_location_vehicule.service.KilometrageVehiculeService;


import com.example.gestion_location_vehicule.model.KilometrageVehicule;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.KilometrageVehiculeRepository;
import com.example.gestion_location_vehicule.service.KilometrageVehiculeService.IKilometrageVehiculeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KilometrageVehiculeService implements IKilometrageVehiculeService {

    private final KilometrageVehiculeRepository repository;

    public KilometrageVehiculeService(KilometrageVehiculeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void enregistrerKilometrage(KilometrageVehicule kmVehicule) {
        repository.save(kmVehicule);
    }

    @Override
    public List<KilometrageVehicule> getKilometragesByVehicule(Vehicule vehicule) {
        return repository.findByVehicule(vehicule);
    }
}
