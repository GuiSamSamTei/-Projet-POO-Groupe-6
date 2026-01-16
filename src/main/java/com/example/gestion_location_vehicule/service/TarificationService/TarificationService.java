package com.example.gestion_location_vehicule.service.TarificationService;

import com.example.gestion_location_vehicule.model.Tarification;
import com.example.gestion_location_vehicule.repository.TarificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarificationService implements ITarificationService {

    private final TarificationRepository tarificationRepository;

    public TarificationService(TarificationRepository tarificationRepository) {
        this.tarificationRepository = tarificationRepository;
    }

    @Override
    public List<Tarification> getAllTarifications() {
        return tarificationRepository.findAll();
    }

    @Override
    public Optional<Tarification> getTarificationById(Long id) {
        return tarificationRepository.findById(id);
    }

    @Override
    public Tarification saveTarification(Tarification tarification) {
        return tarificationRepository.save(tarification);
    }

    @Override
    public void deleteTarification(Long id) {
        tarificationRepository.deleteById(id);
    }

    @Override
    public Optional<Tarification> getByAnnee(long annee) {
        return tarificationRepository.findByAnnee(annee);
    }

    @Override
    public List<Tarification> getByPrixfixeMin(double prixMin) {
        return tarificationRepository.findByPrixfixeGreaterThanEqual(prixMin);
    }

    @Override
    public List<Tarification> getByPrixfixeMax(double prixMax) {
        return tarificationRepository.findByPrixfixeLessThanEqual(prixMax);
    }

    @Override
    public List<Tarification> getByPourcentageMin(double pourcentageMin) {
        return tarificationRepository.findByPourcentageGreaterThanEqual(pourcentageMin);
    }

    @Override
    public List<Tarification> getByPourcentageMax(double pourcentageMax) {
        return tarificationRepository.findByPourcentageLessThanEqual(pourcentageMax);
    }

    @Override
    public List<Tarification> getByPrixfixeAndPourcentageMax(double prixMax, double pourcentageMax) {
        return tarificationRepository.findByPrixfixeLessThanEqualAndPourcentageLessThanEqual(prixMax, pourcentageMax);
    }

    @Override
    public Tarification getbyAnnee(Long annee) {
        return tarificationRepository.findByAnnee(annee);
    }
}
