package com.example.gestion_location_vehicule.service.CalculPrixService;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.time.LocalDate;

public interface ICalculPrixService {

    /**
     * Calcule le prix global d'une location
     *
     * @param vehicule   Véhicule loué
     * @param assurance  Assurance choisie
     * @param dateDebut  Date de début de location
     * @param dateFin    Date de fin de location
     * @return prix global (double)
     */
    double calculerPrixGlobal(Vehicule vehicule, Assurance assurance, LocalDate dateDebut, LocalDate dateFin);
    double getPrixAssurance(Vehicule vehicule, Assurance assurance);

}
