package com.example.gestion_location_vehicule.service.AssuranceService;

import com.example.gestion_location_vehicule.model.Assurance;

import java.util.List;
import java.util.Optional;

public interface IAssuranceService {

    // CRUD
    List<Assurance> getAllAssurances();

    Optional<Assurance> getAssuranceById(Long id);

    Assurance saveAssurance(Assurance assurance);

    void deleteAssurance(Long id);

    // Méthodes métier
    List<Assurance> getAssurancesActives();

    Assurance getAssuranceParDefaut();

    Assurance getByNom(String nom);

    List<Assurance> getByPrixMax(long prixMax);

    List<Assurance> getByPrixMin(long prixMin);

    List<Assurance> getByPrixBetween(long prixMin, long prixMax);
}
