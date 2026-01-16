package com.example.gestion_location_vehicule.service.EvalVService;

import com.example.gestion_location_vehicule.model.EvalV;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.repository.EvalVRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvalVService implements IEvalVService {

    private final EvalVRepository evalVRepository;

    public EvalVService(EvalVRepository evalVRepository) {
        this.evalVRepository = evalVRepository;
    }

    @Override
    public List<EvalV> getAllEvalV() {
        return evalVRepository.findAll();
    }

    @Override
    public Optional<EvalV> getEvalVById(Long id) {
        return evalVRepository.findById(id);
    }

    @Override
    public EvalV saveEvalV(EvalV evalV) {
        return evalVRepository.save(evalV);
    }

    @Override
    public void deleteEvalV(Long id) {
        evalVRepository.deleteById(id);
    }

    @Override
    public List<EvalV> getByLoueur(Loueur loueur) {
        return evalVRepository.findByLoueur(loueur);
    }

    @Override
    public List<EvalV> getByVehicule(Vehicule vehicule) {
        return evalVRepository.findByVehicule(vehicule);
    }

    @Override
    public List<EvalV> getByVehiculeAndLoueur(Vehicule vehicule, Loueur loueur) {
        return evalVRepository.findByVehiculeAndLoueur(vehicule, loueur);
    }

    @Override
    public List<EvalV> getByVehiculeAndNoteMin(Vehicule vehicule, double noteMin) {
        return evalVRepository.findByVehiculeAndNoteGreaterThanEqual(vehicule, noteMin);
    }
}
