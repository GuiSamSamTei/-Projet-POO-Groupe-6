package com.example.gestion_location_vehicule.service.ControleTechniqueService;

import com.example.gestion_location_vehicule.model.ControleTechnique;

import java.util.List;

public interface IControleTechniqueService {

    ControleTechnique enregistrerControleTechnique(ControleTechnique controleTechnique);

    ControleTechnique getControleTechniqueByVehiculeId(Long vehiculeId);

    List<ControleTechnique> getControlesByVehiculeID(Long vehicule_id);
}
