package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.OptionPayante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionPayanteRepository extends JpaRepository<OptionPayante, Long> {

    // Récupérer uniquement les options actives
    List<OptionPayante> findByActiveTrue();

    // Récupérer uniquement les options inactives
    List<OptionPayante> findByActiveFalse();

    // Trouver par nom exact
    List<OptionPayante> findByNom(String nom);

    // Trouver par nom partiel
    List<OptionPayante> findByNomContainingIgnoreCase(String nom);

    // Filtrer par prix mensuel supérieur ou égal
    List<OptionPayante> findByPrixmensuelGreaterThanEqual(double prixMin);

    // Filtrer par prix mensuel inférieur ou égal
    List<OptionPayante> findByPrixmensuelLessThanEqual(double prixMax);

    // Filtrer par prix mensuel entre deux valeurs
    List<OptionPayante> findByPrixmensuelBetween(double min, double max);

}
