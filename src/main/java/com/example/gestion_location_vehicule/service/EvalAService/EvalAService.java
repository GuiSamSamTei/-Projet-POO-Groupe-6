package com.example.gestion_location_vehicule.service.EvalAService;

import com.example.gestion_location_vehicule.model.EvalA;
import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.repository.EvalARepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvalAService implements IEvalAService {

    private final EvalARepository evalARepository;

    public EvalAService(EvalARepository evalARepository) {
        this.evalARepository = evalARepository;
    }

    @Override
    public List<EvalA> getAllEvalA() {
        return evalARepository.findAll();
    }

    @Override
    public Optional<EvalA> getEvalAById(Long id) {
        return evalARepository.findById(id);
    }

    @Override
    public EvalA saveEvalA(EvalA evalA) {
        return evalARepository.save(evalA);
    }

    @Override
    public void deleteEvalA(Long id) {
        evalARepository.deleteById(id);
    }

    @Override
    public List<EvalA> getByAgent(Agent agent) {
        return evalARepository.findByAgent(agent);
    }

    @Override
    public List<EvalA> getByLoueur(Loueur loueur) {
        return evalARepository.findByLoueur(loueur);
    }
}
