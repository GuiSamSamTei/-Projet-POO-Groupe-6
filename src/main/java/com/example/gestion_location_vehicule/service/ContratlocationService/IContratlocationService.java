package com.example.gestion_location_vehicule.service.ContratlocationService;


import com.example.gestion_location_vehicule.model.Contratlocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IContratlocationService {

    Contratlocation ajouterContralocation (Contratlocation contratlocation);
    Contratlocation modifierContralocation(Long contra_id, Contratlocation contratlocation);
    void supprimerContralocation (Long contra_id);

    List<Contratlocation> getAllContralocations();
    Contratlocation trouverContraByLoueurId( Long loueur_id);
    Contratlocation trouverContraByAssurence(Long assurance_id);
    Contratlocation trouverContraByVehiculeId(Long vehicule_id);





}
