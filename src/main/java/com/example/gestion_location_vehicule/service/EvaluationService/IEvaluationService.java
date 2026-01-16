package com.example.gestion_location_vehicule.service.EvaluationService;

import com.example.gestion_location_vehicule.model.Evaluation;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IEvaluationService {

    List<Evaluation> getAllEvaluations();

    Optional<Evaluation> getEvaluationById(Long id);

    Evaluation saveEvaluation(Evaluation evaluation);

    void deleteEvaluation(Long id);


    List<Evaluation> getByNoteMin(double noteMin);

    List<Evaluation> getByNoteMax(double noteMax);

    List<Evaluation> getByNoteBetween(double noteMin, double noteMax);

    List<Evaluation> getByDate(Date date);

    List<Evaluation> getByDateBetween(Date startDate, Date endDate);

    List<Evaluation> getAllOrderByNoteDesc();

    List<Evaluation> getAllOrderByDateDesc();
}
