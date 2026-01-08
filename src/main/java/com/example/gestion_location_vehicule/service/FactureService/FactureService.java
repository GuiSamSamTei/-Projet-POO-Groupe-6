package com.example.gestion_location_vehicule.service.FactureService;

import com.example.gestion_location_vehicule.model.Facture;
import com.example.gestion_location_vehicule.repository.FactureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FactureService implements IFactureService{

    private FactureRepository factureRepository;

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Optional<Facture> getFactureById(Long id) {
        return factureRepository.findById(id);
    }

    @Override
    public Facture saveFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(Long id) {
        factureRepository.deleteById(id);
    }

    @Override
    public List<Facture> getFacturesPayees() {
        return factureRepository.findByPayeeTrue();
    }

    @Override
    public List<Facture> getFacturesNonPayees() {
        return factureRepository.findByPayeeFalse();
    }

    @Override
    public List<Facture> getFacturesByDateBetween(LocalDate debut, LocalDate fin) {
        return factureRepository.findByDateFactureBetween(debut, fin);
    }

    @Override
    public List<Facture> getFacturesByMontantMin(double montantMin) {
        return factureRepository.findByMontantGreaterThanEqual(montantMin);
    }

    @Override
    public List<Facture> getFacturesByMontantMax(double montantMax) {
        return factureRepository.findByMontantLessThanEqual(montantMax);
    }

    @Override
    public List<Facture> getFacturesByMontantBetween(double min, double max) {
        return factureRepository.findByMontantBetween(min, max);
    }

    @Override
    public List<Facture> getFacturesOrderByDateDesc() {
        return factureRepository.findAllByOrderByDateFactureDesc();
    }

    @Override
    public List<Facture> getFacturesOrderByMontantDesc() {
        return factureRepository.findAllByOrderByMontantDesc();
    }
}
