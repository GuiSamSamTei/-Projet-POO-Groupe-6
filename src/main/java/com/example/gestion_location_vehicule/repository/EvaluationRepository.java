package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // Récupérer toutes les évaluations avec une note supérieure ou égale à noteMin
    List<Evaluation> findByNoteGreaterThanEqual(double noteMin);

    // Récupérer toutes les évaluations avec une note inférieure ou égale à noteMax
    List<Evaluation> findByNoteLessThanEqual(double noteMax);

    // Récupérer toutes les évaluations dans une plage de notes
    List<Evaluation> findByNoteBetween(double noteMin, double noteMax);

    // Récupérer toutes les évaluations à une date précise
    List<Evaluation> findByDatenote(Date date);

    // Récupérer toutes les évaluations dans une plage de dates
    List<Evaluation> findByDatenoteBetween(Date startDate, Date endDate);

    // Récupérer toutes les évaluations triées par note descendante
    List<Evaluation> findAllByOrderByNoteDesc();

    // Récupérer toutes les évaluations triées par date descendante
    List<Evaluation> findAllByOrderByDatenoteDesc();
}
