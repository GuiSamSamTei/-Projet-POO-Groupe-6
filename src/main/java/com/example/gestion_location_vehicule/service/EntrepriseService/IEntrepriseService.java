package com.example.gestion_location_vehicule.service.EntrepriseService;

import com.example.gestion_location_vehicule.model.Entreprise;

import java.util.List;
import java.util.Optional;

public interface IEntrepriseService {

    // CRUD
    List<Entreprise> getAllEntreprises();

    Optional<Entreprise> getEntrepriseById(String nsiret);

    Entreprise saveEntreprise(Entreprise entreprise);

    void deleteEntreprise(String nsiret);

    List<Entreprise> getActiveEntreprises();

    List<Entreprise> getInactiveEntreprises();

    List<Entreprise> getEntreprisesByVille(String ville);

    Entreprise getByRaisonSoc(String raisonSoc);

    Entreprise getByEmail(String email);

    List<Entreprise> getActiveEntreprisesByVille(String ville);
}
