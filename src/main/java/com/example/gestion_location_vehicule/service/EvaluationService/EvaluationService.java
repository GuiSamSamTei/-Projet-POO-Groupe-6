package com.example.gestion_location_vehicule.service.EvaluationService;

import com.example.gestion_location_vehicule.model.Evaluation;
import com.example.gestion_location_vehicule.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationService implements IEvaluationService {

    private final EvaluationRepository evaluationRepository;


    @Override
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    @Override
    public Optional<Evaluation> getEvaluationById(Long id) {
        return evaluationRepository.findById(id);
    }

    @Override
    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }

    @Override
    public List<Evaluation> getByNoteMin(double noteMin) {
        return evaluationRepository.findByNoteGreaterThanEqual(noteMin);
    }

    @Override
    public List<Evaluation> getByNoteMax(double noteMax) {
        return evaluationRepository.findByNoteLessThanEqual(noteMax);
    }

    @Override
    public List<Evaluation> getByNoteBetween(double noteMin, double noteMax) {
        return evaluationRepository.findByNoteBetween(noteMin, noteMax);
    }

    @Override
    public List<Evaluation> getByDate(Date date) {
        return evaluationRepository.findByDatenote(date);
    }

    @Override
    public List<Evaluation> getByDateBetween(Date startDate, Date endDate) {
        return evaluationRepository.findByDatenoteBetween(startDate, endDate);
    }

    @Override
    public List<Evaluation> getAllOrderByNoteDesc() {
        return evaluationRepository.findAllByOrderByNoteDesc();
    }

    @Override
    public List<Evaluation> getAllOrderByDateDesc() {
        return evaluationRepository.findAllByOrderByDatenoteDesc();
    }
}
