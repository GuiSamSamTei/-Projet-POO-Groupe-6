package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

    // Factures non payées
    List<Facture> findByPayeeFalse();

    // Factures payées
    List<Facture> findByPayeeTrue();

    // Factures dans une plage de dates
    List<Facture> findByDateFactureBetween(LocalDate debut, LocalDate fin);

    // Factures avec montant supérieur ou égal
    List<Facture> findByMontantGreaterThanEqual(double montantMin);

    // Factures avec montant inférieur ou égal
    List<Facture> findByMontantLessThanEqual(double montantMax);

    // Factures avec montant entre deux valeurs
    List<Facture> findByMontantBetween(double min, double max);

    // Factures triées par date descendante (les plus récentes en premier)
    List<Facture> findAllByOrderByDateFactureDesc();

    // Factures triées par montant décroissant
    List<Facture> findAllByOrderByMontantDesc();
}
