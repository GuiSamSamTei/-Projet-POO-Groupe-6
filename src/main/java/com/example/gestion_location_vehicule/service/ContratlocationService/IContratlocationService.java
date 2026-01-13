package com.example.gestion_location_vehicule.service.ContratlocationService;


import java.util.List;

import com.example.gestion_location_vehicule.model.Contratlocation;


public interface IContratlocationService {

    Contratlocation ajouterContralocation (Contratlocation contratlocation);
    Contratlocation modifierContralocation(Long contra_id, Contratlocation contratlocation);
    void supprimerContralocation (Long contra_id);

    List<Contratlocation> getAllContralocations();
    Contratlocation trouverContratByLoueurId(Long loueur_id);
    Contratlocation trouverContraByAssurence(Long assurance_id);
    Contratlocation trouverContraByVehiculeId(Long vehicule_id);





}
