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

    List<Parrainage> findByParrainId(Long parrainId);

    List<Parrainage> findByParrainIdAndStatut(Long parrainId, StatutParrainage statut);

    Optional<Parrainage> findByFilleulId(Long filleulId);

    long countByFilleulId(Long filleulId);

    long countByParrainIdAndFilleulId(Long parrainId, Long filleulId);

    long countByParrainId(Long parrainId);

    long countByParrainIdAndStatut(Long parrainId, StatutParrainage statut);

    List<Parrainage> findByStatut(StatutParrainage statut);

    @Query("SELECT p FROM Parrainage p WHERE p.filleul.id = :filleulId AND p.statut = 'EN_ATTENTE' AND p.creditAttribue = false")
    Optional<Parrainage> findParrainageEnAttenteByFilleul(@Param("filleulId") Long filleulId);
}
