package com.example.gestion_location_vehicule.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestion_location_vehicule.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    // Récupérer un utilisateur par username exact
    Optional<Utilisateur> findByUsername(String username);

    // Récupérer un utilisateur par email exact
    Optional<Utilisateur> findByEmail(String email);

    // Récupérer tous les utilisateurs dont le username contient une chaîne (insensible à la casse)
    List<Utilisateur> findByUsernameContainingIgnoreCaseAndIsAdminFalse(String usernamePart);

    // Récupérer tous les utilisateurs dont l'email contient une chaîne
    List<Utilisateur> findByEmailContainingIgnoreCaseAndIsAdminFalse(String emailPart);

    // Récupérer tous les utilisateurs ayant une note moyenne supérieure ou égale à une valeur
    List<Utilisateur> findByNotemoyenneGreaterThanEqualAndIsAdminFalse(double noteMin);

    // Récupérer tous les utilisateurs ayant une note moyenne inférieure ou égale à une valeur
    List<Utilisateur> findByNotemoyenneLessThanEqualAndIsAdminFalse(double noteMax);

    // Récupérer tous les utilisateurs ayant reçu un certain nombre minimum d'évaluations
    List<Utilisateur> findByNombreevaluationsGreaterThanEqualAndIsAdminFalse(int minEvaluations);

    Optional<Utilisateur> findByIsAdminTrue();
}
