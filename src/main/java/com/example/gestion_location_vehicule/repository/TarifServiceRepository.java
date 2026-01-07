package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.TarifService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifServiceRepository extends JpaRepository<TarifService, Long> {

    // Récupérer tous les tarifs supérieurs ou égaux à un montant
    List<TarifService> findByPrixentretienGreaterThanEqual(long prixMin);

    // Récupérer tous les tarifs inférieurs ou égaux à un montant
    List<TarifService> findByPrixentretienLessThanEqual(long prixMax);

    // Récupérer tous les tarifs dans une plage
    List<TarifService> findByPrixentretienBetween(long min, long max);
}
