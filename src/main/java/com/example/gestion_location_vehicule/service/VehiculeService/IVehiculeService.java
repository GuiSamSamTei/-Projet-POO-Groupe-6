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

    Double calculePrixLocation(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin,
                               boolean avecAssurance, List<String> options);


    boolean verifierDisponibilite(Long vehiculeId, LocalDate dateDebut, LocalDate dateFin);
    Double getNoteMoyenne(Long vehiculeId);
    Vehicule mettreAJourDisponibilite(Long vehiculeId, boolean disponible);


}
