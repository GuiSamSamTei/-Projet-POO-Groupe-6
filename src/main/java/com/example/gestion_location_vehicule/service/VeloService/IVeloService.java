package com.example.gestion_location_vehicule.service.VeloService;

import java.util.List;
import java.util.Optional;

import com.example.gestion_location_vehicule.model.Velo;
import com.example.gestion_location_vehicule.request.VeloRequest;

public interface IVeloService {
    List<Velo> getAllVelo();
    Velo ajouterVelo(VeloRequest veloRequest);
    Velo modifierVelo(VeloRequest veloRequest, Long velo_id);
    void supprimerVelo(Long velo_id);
    Optional<Velo> getVeloById(Long id);


}

