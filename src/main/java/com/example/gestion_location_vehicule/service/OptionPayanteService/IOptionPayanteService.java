package com.example.gestion_location_vehicule.service.OptionPayanteService;

import com.example.gestion_location_vehicule.model.OptionPayante;

import java.util.List;
import java.util.Optional;

public interface IOptionPayanteService {

    // 🔹 CRUD
    List<OptionPayante> getAllOptions();

    Optional<OptionPayante> getOptionById(Long id);

    OptionPayante saveOption(OptionPayante option);

    void deleteOption(Long id);

    // 🔹 Recherches spécifiques
    List<OptionPayante> getActiveOptions();

    List<OptionPayante> getInactiveOptions();

    List<OptionPayante> getByNom(String nom);

    List<OptionPayante> getByNomContaining(String nom);

    List<OptionPayante> getByPrixMin(double prixMin);

    List<OptionPayante> getByPrixMax(double prixMax);

    List<OptionPayante> getByPrixBetween(double min, double max);
}
