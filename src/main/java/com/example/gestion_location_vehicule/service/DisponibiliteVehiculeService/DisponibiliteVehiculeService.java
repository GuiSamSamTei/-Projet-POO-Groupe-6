package com.example.gestion_location_vehicule.service.DisponibiliteVehiculeService;

import com.example.gestion_location_vehicule.model.DisponibiliteVehicule;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.DisponibiliteVehiculeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DisponibiliteVehiculeService
        implements IDisponibiliteVehiculeService {

    private final DisponibiliteVehiculeRepository repository;

    public DisponibiliteVehiculeService(DisponibiliteVehiculeRepository repository) {
        this.repository = repository;
    }

    @Override
    public DisponibiliteVehicule save(DisponibiliteVehicule disponibilite) {
        return repository.save(disponibilite);
    }

    @Override
    public List<DisponibiliteVehicule> getDisponibilitesPourVehicule(Vehicule vehicule) {
        // ✅ PLUS DE LocalDate.MIN / MAX
        return repository.findByVehicule(vehicule);
    }
}
