package com.example.gestion_location_vehicule.service.VehiculeService;

import com.example.gestion_location_vehicule.model.Vehicule;

import com.example.gestion_location_vehicule.request.VehiculeRequest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IVehiculeService {

    List<Vehicule> getAllVehicules();

    List<Vehicule> getVehiculesDisponibles(LocalDate dateDebut, LocalDate dateFin);

    List<Vehicule> getVehiculesParVille(String ville);

    List<Vehicule> getVehiculesParAgent(Long agentId);

    Vehicule addVehicule(Vehicule vehicule);


    List<Vehicule> filtrer(Map<String, String> filters);

    /**
     * Calcule le prix total d'une location pour un véhicule donné.
     * @param vehiculeId Identifiant du véhicule
     * @param dateDebut Date de début de location
     * @param dateFin Date de fin de location
     * @param avecAssurance Inclure le coût de l'assurance
     * @param options Liste des options payantes sélectionnées
     * @return Prix total de la location
     */
    Double calculePrixLocation(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin,
                               boolean avecAssurance, List<String> options);


    boolean verifierDisponibilite(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin);
    Double getNoteMoyenne(Long vehiculeId);
    Vehicule mettreAJourDisponibilite(Long vehiculeId, boolean disponible);


}
