package com.example.gestion_location_vehicule.service.EvalAService;

import com.example.gestion_location_vehicule.model.EvalA;
import com.example.gestion_location_vehicule.model.Agent;
import com.example.gestion_location_vehicule.model.Loueur;

import java.util.List;
import java.util.Optional;

public interface IEvalAService {

    // 🔹 CRUD
    List<EvalA> getAllEvalA();

    Optional<EvalA> getEvalAById(Long id);

    EvalA saveEvalA(EvalA evalA);

    void deleteEvalA(Long id);

    // 🔹 Recherches spécifiques
    List<EvalA> getByAgent(Agent agent);

    List<EvalA> getByLoueur(Loueur loueur);
}
