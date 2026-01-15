package com.example.gestion_location_vehicule.service.ControleTechniqueService;

import com.example.gestion_location_vehicule.model.ControleTechnique;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.ControleTechniqueRepository;
import com.example.gestion_location_vehicule.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ControleTechniqueService implements com.example.gestion_location_vehicule.service.ControleTechniqueService.IControleTechniqueService {

    private final ControleTechniqueRepository controleTechniqueRepository;
    private final VehiculeRepository vehiculeRepository;

    public ControleTechniqueService(
            ControleTechniqueRepository controleTechniqueRepository,
            VehiculeRepository vehiculeRepository
    ) {
        this.controleTechniqueRepository = controleTechniqueRepository;
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public ControleTechnique enregistrerControleTechnique(ControleTechnique controleTechnique) {
        return controleTechniqueRepository.save(controleTechnique);
    }

    @Override
    public ControleTechnique getControleTechniqueByVehiculeId(Long vehiculeId) {
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        return controleTechniqueRepository.findByVehicule(vehicule)
                .orElse(null);
    }

    @Override
    public List<ControleTechnique> getControlesByVehiculeID(Long vehicule_id) {
        return controleTechniqueRepository.findByVehicule_Id(vehicule_id);
    }
}
