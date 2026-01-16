package com.example.gestion_location_vehicule.service.CalculPrixService;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.time.LocalDate;

public interface ICalculPrixService {

    double calculerPrixGlobal(Vehicule vehicule, Assurance assurance, LocalDate dateDebut, LocalDate dateFin);
    double getPrixAssurance(Vehicule vehicule, Assurance assurance);

}
