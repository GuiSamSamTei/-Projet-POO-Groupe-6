package com.example.gestion_location_vehicule.service.LoueurService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.repository.LoueurRepository;

@Service
public class LoueurService implements ILoueurService {

    private final LoueurRepository loueurRepository;

    public LoueurService(LoueurRepository loueurRepository) {
        this.loueurRepository = loueurRepository;
    }

    @Override
    public List<Loueur> getAll() {
        return loueurRepository.findAll();
    }

    @Override
    public Loueur getById(Long id) {
        return loueurRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Loueur introuvable avec id " + id));
    }

    @Override
    public Loueur create(Loueur loueur) {
        return loueurRepository.save(loueur);
    }

    @Override
    public List<Loueur> searchByNomOrPrenom(String keyword) {
        return loueurRepository
                .findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(
                        keyword, keyword);
    }

    @Override
    public Loueur getByNomAndPrenom(String nom, String prenom) {
        return loueurRepository.findByNomAndPrenom(nom, prenom);
    }

    @Override
    public void ajouterListLoueur(List<Loueur> loueurList)
    {
        loueurRepository.saveAll(loueurList);
    }
}
