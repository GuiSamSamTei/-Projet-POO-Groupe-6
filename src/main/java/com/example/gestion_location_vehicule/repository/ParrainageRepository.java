package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.Parrainage;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.enums.StatutParrainage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParrainageRepository extends JpaRepository<Parrainage, Long> {
    
    /**
     * Trouve tous les parrainages d'un parrain
     */
    List<Parrainage> findByParrainId(Long parrainId);
    
    /**
     * Trouve tous les parrainages d'un parrain par statut
     */
    List<Parrainage> findByParrainIdAndStatut(Long parrainId, StatutParrainage statut);
    
    /**
     * Trouve le parrainage d'un filleul (il ne peut y en avoir qu'un)
     */
    Optional<Parrainage> findByFilleulId(Long filleulId);
    
    /**
     * Vérifie si un filleul a déjà un parrain (count > 0)
     */
    long countByFilleulId(Long filleulId);
    
    /**
     * Vérifie si un parrain a déjà parrainé un filleul (count > 0)
     */
    long countByParrainIdAndFilleulId(Long parrainId, Long filleulId);
    
    /**
     * Compte le nombre de filleuls d'un parrain
     */
    long countByParrainId(Long parrainId);
    
    /**
     * Compte le nombre de parrainages validés d'un parrain
     */
    long countByParrainIdAndStatut(Long parrainId, StatutParrainage statut);
    
    /**
     * Trouve tous les parrainages en attente de validation
     */
    List<Parrainage> findByStatut(StatutParrainage statut);
    
    /**
     * Trouve les parrainages en attente pour un filleul spécifique
     */
    @Query("SELECT p FROM Parrainage p WHERE p.filleul.id = :filleulId AND p.statut = 'EN_ATTENTE' AND p.creditAttribue = false")
    Optional<Parrainage> findParrainageEnAttenteByFilleul(@Param("filleulId") Long filleulId);
}
