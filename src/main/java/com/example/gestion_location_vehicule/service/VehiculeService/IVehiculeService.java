package com.example.gestion_location_vehicule.service.VehiculeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.example.gestion_location_vehicule.model.Vehicule;

public interface IVehiculeService {

    Vehicule getVehiculeByid(Long id);

    List<Vehicule> getAllVehicules();

    List<Vehicule> getAllVehiculesDispo();

    List<Vehicule> getVehiculesDisponibles(LocalDate dateDebut, LocalDate dateFin);

    List<Vehicule> getVehiculesParVille(String ville);

    List<Vehicule> getVehiculesParAgent(Long agentId);

    Vehicule addVehicule(Vehicule vehicule);

    void deleteVehicule(Long id);


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
