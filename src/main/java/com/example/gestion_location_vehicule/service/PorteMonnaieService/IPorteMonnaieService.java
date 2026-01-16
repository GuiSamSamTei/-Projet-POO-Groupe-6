package com.example.gestion_location_vehicule.service.PorteMonnaieService;

import com.example.gestion_location_vehicule.enums.TypeTransaction;
import com.example.gestion_location_vehicule.model.Contratlocation;
import com.example.gestion_location_vehicule.model.Parrainage;
import com.example.gestion_location_vehicule.model.PorteMonnaie;
import com.example.gestion_location_vehicule.model.TransactionPorteMonnaie;
import com.example.gestion_location_vehicule.model.Utilisateur;

import java.util.List;

public interface IPorteMonnaieService {

    PorteMonnaie creerPorteMonnaie(Utilisateur utilisateur);

    PorteMonnaie getByUtilisateur(Long utilisateurId);

    double getSolde(Long utilisateurId);

    TransactionPorteMonnaie crediter(Long utilisateurId, double montant, TypeTransaction type, String description);

    TransactionPorteMonnaie crediterParrainage(Long utilisateurId, double montant, Parrainage parrainage);

    TransactionPorteMonnaie debiter(Long utilisateurId, double montant, TypeTransaction type, String description);

    TransactionPorteMonnaie debiterPourLocation(Long utilisateurId, double montant, Contratlocation contrat);

    List<TransactionPorteMonnaie> getHistorique(Long utilisateurId);

    List<TransactionPorteMonnaie> getHistoriqueParType(Long utilisateurId, TypeTransaction type);

    boolean peutPayer(Long utilisateurId, double montant);

    double calculerMontantAUtiliser(Long utilisateurId, double montantTotal);
}
