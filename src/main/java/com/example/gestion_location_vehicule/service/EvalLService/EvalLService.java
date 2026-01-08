package com.example.gestion_location_vehicule.service.EvalLService;

import com.example.gestion_location_vehicule.model.EvalL;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.repository.EvalLRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvalLService implements IEvalLService {

    private final EvalLRepository evalLRepository;

    public EvalLService(EvalLRepository evalLRepository) {
        this.evalLRepository = evalLRepository;
    }

    // 🔹 CRUD
    @Override
    public List<EvalL> getAllEvalL() {
        return evalLRepository.findAll();
    }

    @Override
    public Optional<EvalL> getEvalLById(Long id) {
        return evalLRepository.findById(id);
    }

    @Override
    public EvalL saveEvalL(EvalL evalL) {
        return evalLRepository.save(evalL);
    }

    @Override
    public void deleteEvalL(Long id) {
        evalLRepository.deleteById(id);
    }

    // 🔹 Recherches spécifiques
    @Override
    public List<EvalL> getByLoueur(Loueur loueur) {
        return evalLRepository.findByLoueur(loueur);
    }

    @Override
    public List<EvalL> getByAgent(Agent agent) {
        return evalLRepository.findByAgent(agent);
    }

    @Override
    public List<EvalL> getByLoueurAndAgent(Loueur loueur, Agent agent) {
        return evalLRepository.findByLoueurAndAgent(loueur, agent);
    }

    @Override
    public List<EvalL> getByNoteMin(double noteMin) {
        return evalLRepository.findByNoteGreaterThanEqual(noteMin);
    }
}
