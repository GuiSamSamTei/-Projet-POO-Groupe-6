package com.example.gestion_location_vehicule.service.ControleTechniqueService;

import com.example.gestion_location_vehicule.model.ControleTechnique;

public interface IControleTechniqueService {

    ControleTechnique enregistrerControleTechnique(ControleTechnique controleTechnique);

    ControleTechnique getControleTechniqueByVehiculeId(Long vehiculeId);
}
