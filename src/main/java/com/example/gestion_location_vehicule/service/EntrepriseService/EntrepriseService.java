package com.example.gestion_location_vehicule.service.EntrepriseService;

import com.example.gestion_location_vehicule.model.Entreprise;
import com.example.gestion_location_vehicule.repository.EntrepriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepriseService implements IEntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    // 🔹 CRUD
    @Override
    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    @Override
    public Optional<Entreprise> getEntrepriseById(String nsiret) {
        return entrepriseRepository.findById(nsiret);
    }

    @Override
    public Entreprise saveEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public void deleteEntreprise(String nsiret) {
        entrepriseRepository.deleteById(nsiret);
    }

    // 🔹 Recherches spécifiques
    @Override
    public List<Entreprise> getActiveEntreprises() {
        return entrepriseRepository.findByActiveTrue();
    }

    @Override
    public List<Entreprise> getInactiveEntreprises() {
        return entrepriseRepository.findByActiveFalse();
    }

    @Override
    public List<Entreprise> getEntreprisesByVille(String ville) {
        return entrepriseRepository.findByVille(ville);
    }

    @Override
    public Entreprise getByRaisonSoc(String raisonSoc) {
        return entrepriseRepository.findByRaisonSoc(raisonSoc);
    }

    @Override
    public Entreprise getByEmail(String email) {
        return entrepriseRepository.findByEmail(email);
    }

    @Override
    public List<Entreprise> getActiveEntreprisesByVille(String ville) {
        return entrepriseRepository.findByVilleAndActiveTrue(ville);
    }
}
