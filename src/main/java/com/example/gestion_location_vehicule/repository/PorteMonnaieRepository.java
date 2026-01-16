package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.PorteMonnaie;
import com.example.gestion_location_vehicule.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PorteMonnaieRepository extends JpaRepository<PorteMonnaie, Long> {

    Optional<PorteMonnaie> findByUtilisateur(Utilisateur utilisateur);

    Optional<PorteMonnaie> findByUtilisateurId(Long utilisateurId);

    long countByUtilisateurId(Long utilisateurId);

    boolean existsByUtilisateurId(Long utilisateurId);
}
