package com.example.gestion_location_vehicule.service.TarificationService;

import com.example.gestion_location_vehicule.model.Tarification;

import java.util.List;
import java.util.Optional;

public interface ITarificationService {

    // 🔹 CRUD
    List<Tarification> getAllTarifications();

    Optional<Tarification> getTarificationById(Long id);

    Tarification saveTarification(Tarification tarification);

    void deleteTarification(Long id);

    // 🔹 Recherches spécifiques
    Optional<Tarification> getByAnnee(long annee);

    List<Tarification> getByPrixfixeMin(double prixMin);

    List<Tarification> getByPrixfixeMax(double prixMax);

    List<Tarification> getByPourcentageMin(double pourcentageMin);

    List<Tarification> getByPourcentageMax(double pourcentageMax);

    List<Tarification> getByPrixfixeAndPourcentageMax(double prixMax, double pourcentageMax);
}
