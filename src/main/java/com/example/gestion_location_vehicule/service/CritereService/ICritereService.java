package com.example.gestion_location_vehicule.service.CritereService;

import com.example.gestion_location_vehicule.model.Critere;
import com.example.gestion_location_vehicule.enums.TypeCritere;

import java.util.List;
import java.util.Optional;

public interface ICritereService {

    // CRUD
    List<Critere> getAllCriteres();

    Optional<Critere> getCritereById(Long id);

    Critere saveCritere(Critere critere);

    void deleteCritere(Long id);

    Critere getByNom(String nom);

    List<Critere> getByType(TypeCritere type);
}
