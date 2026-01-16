package com.example.gestion_location_vehicule.service.OptionPayanteService;

import com.example.gestion_location_vehicule.model.OptionPayante;
import com.example.gestion_location_vehicule.repository.OptionPayanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionPayanteService implements IOptionPayanteService {

    private final OptionPayanteRepository optionPayanteRepository;

    public OptionPayanteService(OptionPayanteRepository optionPayanteRepository) {
        this.optionPayanteRepository = optionPayanteRepository;
    }

    @Override
    public List<OptionPayante> getAllOptions() {
        return optionPayanteRepository.findAll();
    }

    @Override
    public Optional<OptionPayante> getOptionById(Long id) {
        return optionPayanteRepository.findById(id);
    }

    @Override
    public OptionPayante saveOption(OptionPayante option) {
        return optionPayanteRepository.save(option);
    }

    @Override
    public void deleteOption(Long id) {
        optionPayanteRepository.deleteById(id);
    }

    @Override
    public List<OptionPayante> getActiveOptions() {
        return optionPayanteRepository.findByActiveTrue();
    }

    @Override
    public List<OptionPayante> getInactiveOptions() {
        return optionPayanteRepository.findByActiveFalse();
    }

    @Override
    public List<OptionPayante> getByNom(String nom) {
        return optionPayanteRepository.findByNom(nom);
    }

    @Override
    public List<OptionPayante> getByNomContaining(String nom) {
        return optionPayanteRepository.findByNomContainingIgnoreCase(nom);
    }

    @Override
    public List<OptionPayante> getByPrixMin(double prixMin) {
        return optionPayanteRepository.findByPrixmensuelGreaterThanEqual(prixMin);
    }

    @Override
    public List<OptionPayante> getByPrixMax(double prixMax) {
        return optionPayanteRepository.findByPrixmensuelLessThanEqual(prixMax);
    }

    @Override
    public List<OptionPayante> getByPrixBetween(double min, double max) {
        return optionPayanteRepository.findByPrixmensuelBetween(min, max);
    }
}
