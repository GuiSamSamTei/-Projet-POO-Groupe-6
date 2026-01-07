package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Loueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoueurRepository extends JpaRepository<Loueur, Long> {

    // Trouver par nom exact
    List<Loueur> findByNom(String nom);

    // Trouver par prénom exact
    List<Loueur> findByPrenom(String prenom);

    // Trouver par nom ou prénom partiel (insensible à la casse)
    List<Loueur> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

    // Trouver par date de naissance exacte
    List<Loueur> findByDatenaissance(String datenaissance);

    // Combinaison nom + prénom
    Loueur findByNomAndPrenom(String nom, String prenom);

    // Filtrer par nombre d’évaluations données (via noteMoyenne / nombreEvaluations hérité de Utilisateur)
    List<Loueur> findByNombreEvaluationsGreaterThan(int minEvaluations);

    // Filtrer par note moyenne
    List<Loueur> findByNoteMoyenneGreaterThanEqual(double noteMin);
}
