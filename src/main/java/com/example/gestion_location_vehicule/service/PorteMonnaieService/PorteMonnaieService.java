package com.example.gestion_location_vehicule.service.PorteMonnaieService;

import com.example.gestion_location_vehicule.enums.TypeTransaction;
import com.example.gestion_location_vehicule.model.*;
import com.example.gestion_location_vehicule.repository.PorteMonnaieRepository;
import com.example.gestion_location_vehicule.repository.TransactionPorteMonnaieRepository;
import com.example.gestion_location_vehicule.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PorteMonnaieService implements IPorteMonnaieService {

    private final PorteMonnaieRepository porteMonnaieRepository;
    private final TransactionPorteMonnaieRepository transactionRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public PorteMonnaie creerPorteMonnaie(Utilisateur utilisateur) {
        // Vérifier si un porte-monnaie existe déjà (compatibilité Oracle 11g)
        if (porteMonnaieRepository.countByUtilisateurId(utilisateur.getId()) > 0) {
            throw new IllegalStateException("Un porte-monnaie existe déjà pour cet utilisateur");
        }

        PorteMonnaie porteMonnaie = new PorteMonnaie();
        porteMonnaie.setUtilisateur(utilisateur);
        porteMonnaie.setSolde(0.0);
        porteMonnaie.setDateCreation(LocalDateTime.now());
        porteMonnaie.setDateDerniereModification(LocalDateTime.now());

        return porteMonnaieRepository.save(porteMonnaie);
    }

    @Override
    public PorteMonnaie getByUtilisateur(Long utilisateurId) {
        return porteMonnaieRepository.findByUtilisateurId(utilisateurId)
                .orElseGet(() -> {
                    // Si le porte-monnaie n'existe pas, on le crée à la volée
                    // Cela évite l'erreur 500 pour les utilisateurs existants
                    Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                            .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID " + utilisateurId));
                    return creerPorteMonnaie(utilisateur);
                });
    }

    @Override
    public double getSolde(Long utilisateurId) {
        PorteMonnaie porteMonnaie = getByUtilisateur(utilisateurId);
        return porteMonnaie.getSolde();
    }

    @Override
    @Transactional
    public TransactionPorteMonnaie crediter(Long utilisateurId, double montant, TypeTransaction type, String description) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à créditer doit être positif");
        }

        PorteMonnaie porteMonnaie = getByUtilisateur(utilisateurId);
        double soldeAvant = porteMonnaie.getSolde();

        // Créditer le porte-monnaie
        porteMonnaie.crediter(montant);
        porteMonnaieRepository.save(porteMonnaie);

        // Créer la transaction
        TransactionPorteMonnaie transaction = new TransactionPorteMonnaie();
        transaction.setPorteMonnaie(porteMonnaie);
        transaction.setType(type);
        transaction.setMontant(montant); // Positif pour crédit
        transaction.setSoldeAvant(soldeAvant);
        transaction.setSoldeApres(porteMonnaie.getSolde());
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setDescription(description);

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public TransactionPorteMonnaie crediterParrainage(Long utilisateurId, double montant, Parrainage parrainage) {
        TransactionPorteMonnaie transaction = crediter(
                utilisateurId,
                montant,
                TypeTransaction.CREDIT_PARRAINAGE,
                "Crédit de parrainage pour le filleul " + parrainage.getFilleul().getUsername()
        );
        transaction.setParrainage(parrainage);
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public TransactionPorteMonnaie debiter(Long utilisateurId, double montant, TypeTransaction type, String description) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à débiter doit être positif");
        }

        PorteMonnaie porteMonnaie = getByUtilisateur(utilisateurId);
        double soldeAvant = porteMonnaie.getSolde();

        // Vérifier le solde
        if (!porteMonnaie.peutPayer(montant)) {
            throw new IllegalStateException("Solde insuffisant. Solde actuel: " + soldeAvant + "€, montant demandé: " + montant + "€");
        }

        // Débiter le porte-monnaie
        porteMonnaie.debiter(montant);
        porteMonnaieRepository.save(porteMonnaie);

        // Créer la transaction
        TransactionPorteMonnaie transaction = new TransactionPorteMonnaie();
        transaction.setPorteMonnaie(porteMonnaie);
        transaction.setType(type);
        transaction.setMontant(-montant); // Négatif pour débit
        transaction.setSoldeAvant(soldeAvant);
        transaction.setSoldeApres(porteMonnaie.getSolde());
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setDescription(description);

        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public TransactionPorteMonnaie debiterPourLocation(Long utilisateurId, double montant, Contratlocation contrat) {
        TransactionPorteMonnaie transaction = debiter(
                utilisateurId,
                montant,
                TypeTransaction.DEBIT_LOCATION,
                "Paiement location véhicule " + contrat.getVehicule().getMarque() + " " + contrat.getVehicule().getModele()
        );
        transaction.setContratLocation(contrat);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionPorteMonnaie> getHistorique(Long utilisateurId) {
        PorteMonnaie porteMonnaie = getByUtilisateur(utilisateurId);
        return transactionRepository.findByPorteMonnaieIdOrderByDateTransactionDesc(porteMonnaie.getId());
    }

    @Override
    public List<TransactionPorteMonnaie> getHistoriqueParType(Long utilisateurId, TypeTransaction type) {
        PorteMonnaie porteMonnaie = getByUtilisateur(utilisateurId);
        return transactionRepository.findByPorteMonnaieIdAndTypeOrderByDateTransactionDesc(porteMonnaie.getId(), type);
    }

    @Override
    public boolean peutPayer(Long utilisateurId, double montant) {
        try {
            PorteMonnaie porteMonnaie = getByUtilisateur(utilisateurId);
            return porteMonnaie.peutPayer(montant);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public double calculerMontantAUtiliser(Long utilisateurId, double montantTotal) {
        try {
            double solde = getSolde(utilisateurId);
            return Math.min(solde, montantTotal);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
