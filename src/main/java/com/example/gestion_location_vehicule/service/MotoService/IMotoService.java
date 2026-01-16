package com.example.gestion_location_vehicule.service.MotoService;

import com.example.gestion_location_vehicule.model.Moto;

import java.util.List;
import java.util.Optional;

public interface IMotoService {

    List<Moto> getAllMotos();

    Optional<Moto> getMotoById(Long id);

    Moto saveMoto(Moto moto);

    void deleteMoto(Long id);

    List<Moto> getMotosDispo();

    List<Moto> getMotosByVille(String ville);

    List<Moto> getMotosByCylindreeMin(int cylindreeMin);

    List<Moto> getMotosByCylindreeMax(int cylindreeMax);

    List<Moto> getMotosByCylindreeBetween(int min, int max);

    List<Moto> getMotosByNbChevauxMin(int nbChevauxMin);

    List<Moto> getMotosByNbChevauxMax(int nbChevauxMax);

    List<Moto> getMotosByNbChevauxBetween(int min, int max);

    List<Moto> getMotosByVilleAndCylindreeMin(String ville, int cylindreeMin);

    List<Moto> getMotosByVilleAndNbChevauxMin(String ville, int nbChevauxMin);
}
