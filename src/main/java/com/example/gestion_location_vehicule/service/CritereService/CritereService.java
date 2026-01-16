package com.example.gestion_location_vehicule.service.CritereService;

import com.example.gestion_location_vehicule.model.Critere;
import com.example.gestion_location_vehicule.enums.TypeCritere;
import com.example.gestion_location_vehicule.repository.CritereRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CritereService implements ICritereService {

    private final CritereRepository critereRepository;

    public CritereService(CritereRepository critereRepository) {
        this.critereRepository = critereRepository;
    }

    @Override
    public List<Critere> getAllCriteres() {
        return critereRepository.findAll();
    }

    @Override
    public Optional<Critere> getCritereById(Long id) {
        return critereRepository.findById(id);
    }

    @Override
    public Critere saveCritere(Critere critere) {
        return critereRepository.save(critere);
    }

    @Override
    public void deleteCritere(Long id) {
        critereRepository.deleteById(id);
    }

    @Override
    public Critere getByNom(String nom) {
        return critereRepository.findByNom(nom);
    }

    @Override
    public List<Critere> getByType(TypeCritere type) {
        return critereRepository.findByType(type);
    }
}
