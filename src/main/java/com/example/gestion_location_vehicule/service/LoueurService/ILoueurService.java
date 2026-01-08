package com.example.gestion_location_vehicule.service.LoueurService;

import com.example.gestion_location_vehicule.model.Loueur;

import java.util.List;

public interface ILoueurService {

    List<Loueur> getAll();

    Loueur getById(Long id);

    Loueur create(Loueur loueur);

    List<Loueur> searchByNomOrPrenom(String keyword);

    Loueur getByNomAndPrenom(String nom, String prenom);
}
