package com.example.gestion_location_vehicule.service.EntretienTechniqueService;

import com.example.gestion_location_vehicule.model.EntretienTechnique;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.EntretienTechniqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntretienTechniqueService implements IEntretienTechniqueService {

    private final EntretienTechniqueRepository entretienTechniqueRepository;

    @Override
    public EntretienTechnique enregistrerEntretien(EntretienTechnique entretien) {
        return entretienTechniqueRepository.save(entretien);
    }

    @Override
    public List<EntretienTechnique> getEntretiensByVehicule(Vehicule vehicule) {
        return entretienTechniqueRepository.findByVehicule(vehicule);
    }
}
