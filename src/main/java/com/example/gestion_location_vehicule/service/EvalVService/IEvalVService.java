package com.example.gestion_location_vehicule.service.EvalVService;

import com.example.gestion_location_vehicule.model.EvalV;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Vehicule;

import java.util.List;
import java.util.Optional;

public interface IEvalVService {

    List<EvalV> getAllEvalV();

    Optional<EvalV> getEvalVById(Long id);

    EvalV saveEvalV(EvalV evalV);

    void deleteEvalV(Long id);

    List<EvalV> getByLoueur(Loueur loueur);

    List<EvalV> getByVehicule(Vehicule vehicule);

    List<EvalV> getByVehiculeAndLoueur(Vehicule vehicule, Loueur loueur);

    List<EvalV> getByVehiculeAndNoteMin(Vehicule vehicule, double noteMin);
}
