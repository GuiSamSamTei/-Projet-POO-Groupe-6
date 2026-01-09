package com.example.gestion_location_vehicule.service.LoueurService;

import com.example.gestion_location_vehicule.model.Loueur;

import java.util.List;

public interface ILoueurService {

    public List<Loueur> getAll();

    public Loueur getById(Long id);

    public Loueur create(Loueur loueur);

    public List<Loueur> searchByNomOrPrenom(String keyword);

    public Loueur getByNomAndPrenom(String nom, String prenom);
}
