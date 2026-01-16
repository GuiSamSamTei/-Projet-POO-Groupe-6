package com.example.gestion_location_vehicule.service.FactureService;

import com.example.gestion_location_vehicule.model.Facture;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IFactureService {

    List<Facture> getAllFactures();

    Optional<Facture> getFactureById(Long id);

    Facture saveFacture(Facture facture);

    void deleteFacture(Long id);

    List<Facture> getFacturesPayees();

    List<Facture> getFacturesNonPayees();

    List<Facture> getFacturesByDateBetween(LocalDate debut, LocalDate fin);

    List<Facture> getFacturesByMontantMin(double montantMin);

    List<Facture> getFacturesByMontantMax(double montantMax);

    List<Facture> getFacturesByMontantBetween(double min, double max);

    List<Facture> getFacturesOrderByDateDesc();

    List<Facture> getFacturesOrderByMontantDesc();
}
