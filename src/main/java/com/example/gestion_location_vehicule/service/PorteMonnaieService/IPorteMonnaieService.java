package com.example.gestion_location_vehicule.service.PorteMonnaieService;

import com.example.gestion_location_vehicule.enums.TypeTransaction;
import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.Parrainage;
import com.example.gestion_location_vehicule.model.PorteMonnaie;
import com.example.gestion_location_vehicule.model.TransactionPorteMonnaie;
import com.example.gestion_location_vehicule.model.Utilisateur;

import java.util.List;

public interface IPorteMonnaieService {
    
    /**
     * Crée un porte-monnaie pour un utilisateur
     */
    PorteMonnaie creerPorteMonnaie(Utilisateur utilisateur);
    
    /**
     * Récupère le porte-monnaie d'un utilisateur
     */
    PorteMonnaie getByUtilisateur(Long utilisateurId);
    
    /**
     * Récupère le solde d'un utilisateur
     */
    double getSolde(Long utilisateurId);
    
    /**
     * Crédite le porte-monnaie d'un utilisateur
     */
    TransactionPorteMonnaie crediter(Long utilisateurId, double montant, TypeTransaction type, String description);
    
    /**
     * Crédite le porte-monnaie avec référence à un parrainage
     */
    TransactionPorteMonnaie crediterParrainage(Long utilisateurId, double montant, Parrainage parrainage);
    
    /**
     * Débite le porte-monnaie d'un utilisateur
     */
    TransactionPorteMonnaie debiter(Long utilisateurId, double montant, TypeTransaction type, String description);
    
    /**
     * Débite le porte-monnaie pour une location
     */
    TransactionPorteMonnaie debiterPourLocation(Long utilisateurId, double montant, Contratlocation contrat);
    
    /**
     * Récupère l'historique des transactions
     */
    List<TransactionPorteMonnaie> getHistorique(Long utilisateurId);
    
    /**
     * Récupère l'historique par type de transaction
     */
    List<TransactionPorteMonnaie> getHistoriqueParType(Long utilisateurId, TypeTransaction type);
    
    /**
     * Vérifie si un utilisateur peut payer un montant
     */
    boolean peutPayer(Long utilisateurId, double montant);
    
    /**
     * Calcule le montant à prélever du porte-monnaie pour un paiement
     * Retourne min(solde, montantTotal)
     */
    double calculerMontantAUtiliser(Long utilisateurId, double montantTotal);
}
