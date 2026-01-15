package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.TransactionPorteMonnaie;
import com.example.gestion_location_vehicule.enums.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionPorteMonnaieRepository extends JpaRepository<TransactionPorteMonnaie, Long> {
    
    /**
     * Trouve toutes les transactions d'un porte-monnaie
     */
    List<TransactionPorteMonnaie> findByPorteMonnaieIdOrderByDateTransactionDesc(Long porteMonnaieId);
    
    /**
     * Trouve les transactions par type
     */
    List<TransactionPorteMonnaie> findByPorteMonnaieIdAndTypeOrderByDateTransactionDesc(Long porteMonnaieId, TypeTransaction type);
    
    /**
     * Trouve les transactions entre deux dates
     */
    List<TransactionPorteMonnaie> findByPorteMonnaieIdAndDateTransactionBetweenOrderByDateTransactionDesc(
            Long porteMonnaieId, LocalDateTime dateDebut, LocalDateTime dateFin);
    
    /**
     * Trouve les transactions liées à un parrainage
     */
    List<TransactionPorteMonnaie> findByParrainageId(Long parrainageId);
    
    /**
     * Trouve les transactions liées à un contrat de location
     */
    List<TransactionPorteMonnaie> findByContratLocationId(Long contratLocationId);
}
