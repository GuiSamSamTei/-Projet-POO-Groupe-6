package com.example.gestion_location_vehicule.repository;

import com.example.gestion_location_vehicule.model.TransactionPorteMonnaie;
import com.example.gestion_location_vehicule.enums.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionPorteMonnaieRepository extends JpaRepository<TransactionPorteMonnaie, Long> {

    List<TransactionPorteMonnaie> findByPorteMonnaieIdOrderByDateTransactionDesc(Long porteMonnaieId);

    List<TransactionPorteMonnaie> findByPorteMonnaieIdAndTypeOrderByDateTransactionDesc(Long porteMonnaieId, TypeTransaction type);

    List<TransactionPorteMonnaie> findByPorteMonnaieIdAndDateTransactionBetweenOrderByDateTransactionDesc(
            Long porteMonnaieId, LocalDateTime dateDebut, LocalDateTime dateFin);

    List<TransactionPorteMonnaie> findByParrainageId(Long parrainageId);

    List<TransactionPorteMonnaie> findByContratLocationId(Long contratLocationId);
}
