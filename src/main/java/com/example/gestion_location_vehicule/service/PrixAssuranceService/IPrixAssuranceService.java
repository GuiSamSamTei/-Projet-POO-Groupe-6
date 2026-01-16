package com.example.gestion_location_vehicule.service.PrixAssuranceService;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.model.PrixAssurance;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IPrixAssuranceService {


    List<PrixAssurance> getAllPrixAssurances();

    Optional<PrixAssurance> getPrixAssuranceById(Long id);

    PrixAssurance savePrixAssurance(PrixAssurance prixAssurance);

    void deletePrixAssurance(Long id);

    List<PrixAssurance> getByVehicule(Vehicule vehicule);

    List<PrixAssurance> getByAssurance(Assurance assurance);

    PrixAssurance getByVehiculeAndAssurance(Vehicule vehicule, Assurance assurance);

    List<PrixAssurance> getByPrixMin(double prixMin);

    List<PrixAssurance> getByPrixMax(double prixMax);

    List<PrixAssurance> getByPrixBetween(double min, double max);

    List<PrixAssurance> getByVehiculeAndPrixMax(Vehicule vehicule, double prixMax);

    List<PrixAssurance> getByAssuranceAndPrixMax(Assurance assurance, double prixMax);
}
