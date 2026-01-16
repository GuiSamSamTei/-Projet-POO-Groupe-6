package com.example.gestion_location_vehicule.service.EvalLService;

import com.example.gestion_location_vehicule.model.EvalL;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Agent;

import java.util.List;
import java.util.Optional;

public interface IEvalLService {

    List<EvalL> getAllEvalL();

    Optional<EvalL> getEvalLById(Long id);

    EvalL saveEvalL(EvalL evalL);

    void deleteEvalL(Long id);

    List<EvalL> getByLoueur(Loueur loueur);

    List<EvalL> getByAgent(Agent agent);

    List<EvalL> getByLoueurAndAgent(Loueur loueur, Agent agent);

    List<EvalL> getByNoteMin(double noteMin);
}
