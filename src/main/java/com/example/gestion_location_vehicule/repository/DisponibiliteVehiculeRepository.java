package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.DisponibiliteVehicule;
import com.example.gestion_location_vehicule.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DisponibiliteVehiculeRepository
        extends JpaRepository<DisponibiliteVehicule, Long> {

    // Pour afficher TOUTES les disponibilités d’un véhicule
    List<DisponibiliteVehicule> findByVehicule(Vehicule vehicule);

    // Pour les recherches avec filtre de dates
    List<DisponibiliteVehicule>
    findByVehiculeAndDateDebutLessThanEqualAndDateFinGreaterThanEqual(
            Vehicule vehicule,
            LocalDate dateFin,
            LocalDate dateDebut
    );
}
