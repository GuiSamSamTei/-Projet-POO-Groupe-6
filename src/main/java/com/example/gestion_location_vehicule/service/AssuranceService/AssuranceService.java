package com.example.gestion_location_vehicule.service.AssuranceService;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.repository.AssuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssuranceService implements IAssuranceService {

    private final AssuranceRepository assuranceRepository;

    public AssuranceService(AssuranceRepository assuranceRepository) {
        this.assuranceRepository = assuranceRepository;
    }

    // 🔹 CRUD

    @Override
    public List<Assurance> getAllAssurances() {
        return assuranceRepository.findAll();
    }

    @Override
    public Optional<Assurance> getAssuranceById(Long id) {
        return assuranceRepository.findById(id);
    }

    @Override
    public Assurance saveAssurance(Assurance assurance) {
        return assuranceRepository.save(assurance);
    }

    @Override
    public void deleteAssurance(Long id) {
        assuranceRepository.deleteById(id);
    }

    // 🔹 Métier

    @Override
    public List<Assurance> getAssurancesActives() {
        return assuranceRepository.findByActiveTrue();
    }

    @Override
    public Assurance getAssuranceParDefaut() {
        return assuranceRepository.findByAssurancepardefautTrue();
    }

    @Override
    public Assurance getByNom(String nom) {
        return assuranceRepository.findByNom(nom);
    }
}
