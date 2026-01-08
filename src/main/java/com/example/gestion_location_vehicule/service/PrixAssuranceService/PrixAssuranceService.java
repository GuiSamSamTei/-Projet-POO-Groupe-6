package com.example.gestion_location_vehicule.service.PrixAssuranceService;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.model.PrixAssurance;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.PrixAssuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrixAssuranceService implements IPrixAssuranceService {

    private final PrixAssuranceRepository prixAssuranceRepository;

    public PrixAssuranceService(PrixAssuranceRepository prixAssuranceRepository) {
        this.prixAssuranceRepository = prixAssuranceRepository;
    }

    // 🔹 CRUD
    @Override
    public List<PrixAssurance> getAllPrixAssurances() {
        return prixAssuranceRepository.findAll();
    }

    @Override
    public Optional<PrixAssurance> getPrixAssuranceById(Long id) {
        return prixAssuranceRepository.findById(id);
    }

    @Override
    public PrixAssurance savePrixAssurance(PrixAssurance prixAssurance) {
        return prixAssuranceRepository.save(prixAssurance);
    }

    @Override
    public void deletePrixAssurance(Long id) {
        prixAssuranceRepository.deleteById(id);
    }

    // 🔹 Recherches spécifiques
    @Override
    public List<PrixAssurance> getByVehicule(Vehicule vehicule) {
        return prixAssuranceRepository.findByVehicule(vehicule);
    }

    @Override
    public List<PrixAssurance> getByAssurance(Assurance assurance) {
        return prixAssuranceRepository.findByAssurance(assurance);
    }

    @Override
    public PrixAssurance getByVehiculeAndAssurance(Vehicule vehicule, Assurance assurance) {
        return prixAssuranceRepository.findByVehiculeAndAssurance(vehicule, assurance);
    }

    @Override
    public List<PrixAssurance> getByPrixMin(double prixMin) {
        return prixAssuranceRepository.findByPrixGreaterThanEqual(prixMin);
    }

    @Override
    public List<PrixAssurance> getByPrixMax(double prixMax) {
        return prixAssuranceRepository.findByPrixLessThanEqual(prixMax);
    }

    @Override
    public List<PrixAssurance> getByPrixBetween(double min, double max) {
        return prixAssuranceRepository.findByPrixBetween(min, max);
    }

    @Override
    public List<PrixAssurance> getByVehiculeAndPrixMax(Vehicule vehicule, double prixMax) {
        return prixAssuranceRepository.findByVehiculeAndPrixLessThanEqual(vehicule, prixMax);
    }

    @Override
    public List<PrixAssurance> getByAssuranceAndPrixMax(Assurance assurance, double prixMax) {
        return prixAssuranceRepository.findByAssuranceAndPrixLessThanEqual(assurance, prixMax);
    }
}
