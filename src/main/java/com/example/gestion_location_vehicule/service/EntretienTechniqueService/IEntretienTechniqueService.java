package com.example.gestion_location_vehicule.service.EntretienTechniqueService;

import com.example.gestion_location_vehicule.model.EntretienTechnique;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.util.List;

public interface IEntretienTechniqueService {

    EntretienTechnique enregistrerEntretien(EntretienTechnique entretien);

    List<EntretienTechnique> getEntretiensByVehicule(Vehicule vehicule);
}
