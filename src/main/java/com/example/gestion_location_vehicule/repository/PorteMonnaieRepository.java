package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.PorteMonnaie;
import com.example.gestion_location_vehicule.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PorteMonnaieRepository extends JpaRepository<PorteMonnaie, Long> {
    
    /**
     * Trouve le porte-monnaie d'un utilisateur
     */
    Optional<PorteMonnaie> findByUtilisateur(Utilisateur utilisateur);
    
    /**
     * Trouve le porte-monnaie par ID utilisateur
     */
    Optional<PorteMonnaie> findByUtilisateurId(Long utilisateurId);

    // Utilisation de count au lieu de exists pour compatibilité Oracle 11g
    long countByUtilisateurId(Long utilisateurId);
    
    /**
     * Vérifie si un utilisateur a un porte-monnaie
     */
    boolean existsByUtilisateurId(Long utilisateurId);
}
