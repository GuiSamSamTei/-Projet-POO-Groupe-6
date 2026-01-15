package com.example.gestion_location_vehicule.service.ParrainageService;

import com.example.gestion_location_vehicule.enums.StatutParrainage;
import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Parrainage;
import com.example.gestion_location_vehicule.repository.ContratlocationRepository;
import com.example.gestion_location_vehicule.repository.LoueurRepository;
import com.example.gestion_location_vehicule.repository.ParrainageRepository;
import com.example.gestion_location_vehicule.service.PorteMonnaieService.IPorteMonnaieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ParrainageService implements IParrainageService {

    private final ParrainageRepository parrainageRepository;
    private final LoueurRepository loueurRepository;
    private final ContratlocationRepository contratlocationRepository;
    private final IPorteMonnaieService porteMonnaieService;

    private static final double MONTANT_CREDIT_PARRAINAGE = 20.0; // Configurable

    @Override
    @Transactional
    public Parrainage creerParrainage(Long parrainId, Long filleulId) {
        // Vérifications
        if (!peutParrainer(parrainId, filleulId)) {
            throw new IllegalArgumentException("Le parrainage n'est pas possible");
        }

        Loueur parrain = loueurRepository.findById(parrainId)
                .orElseThrow(() -> new IllegalArgumentException("Parrain introuvable"));
        Loueur filleul = loueurRepository.findById(filleulId)
                .orElseThrow(() -> new IllegalArgumentException("Filleul introuvable"));

        // Créer le parrainage
        Parrainage parrainage = new Parrainage();
        parrainage.setParrain(parrain);
        parrainage.setFilleul(filleul);
        parrainage.setDateParrainage(LocalDateTime.now());
        parrainage.setStatut(StatutParrainage.EN_ATTENTE);
        parrainage.setCreditAttribue(false);
        parrainage.setMontantCredit(MONTANT_CREDIT_PARRAINAGE);

        return parrainageRepository.save(parrainage);
    }

    @Override
    @Transactional
    public Parrainage creerParrainageParEmail(Long parrainId, String emailFilleul) {
        Loueur filleul = loueurRepository.findByEmail(emailFilleul)
                .orElseThrow(() -> new IllegalArgumentException("Aucun loueur trouvé avec cet email"));
        return creerParrainage(parrainId, filleul.getId());
    }

    @Override
    @Transactional
    public Parrainage creerParrainageParUsername(Long parrainId, String usernameFilleul) {
        Loueur filleul = loueurRepository.findByUsername(usernameFilleul)
                .orElseThrow(() -> new IllegalArgumentException("Aucun loueur trouvé avec ce nom d'utilisateur"));
        return creerParrainage(parrainId, filleul.getId());
    }

    @Override
    @Transactional
    public void validerParrainage(Long parrainageId) {
        Parrainage parrainage = parrainageRepository.findById(parrainageId)
                .orElseThrow(() -> new IllegalArgumentException("Parrainage introuvable"));

        if (parrainage.isCreditAttribue()) {
            throw new IllegalStateException("Le crédit a déjà été attribué pour ce parrainage");
        }

        if (parrainage.getStatut() != StatutParrainage.EN_ATTENTE) {
            throw new IllegalStateException("Le parrainage n'est pas en attente");
        }

        // Créditer le porte-monnaie du parrain
        porteMonnaieService.crediterParrainage(
                parrainage.getParrain().getId(),
                parrainage.getMontantCredit(),
                parrainage
        );

        // Marquer le parrainage comme validé
        parrainage.valider();
        parrainageRepository.save(parrainage);
    }

    @Override
    @Transactional
    public void checkEtValiderPremiereLocation(Long filleulId) {
        // Vérifier si le filleul a un parrainage en attente
        parrainageRepository.findParrainageEnAttenteByFilleul(filleulId)
                .ifPresent(parrainage -> {
                    // Vérifier si c'est bien la première location
                    long nombreLocations = contratlocationRepository.countByLoueurId(filleulId);
                    if (nombreLocations == 1) {
                        // C'est la première location, valider le parrainage
                        validerParrainage(parrainage.getId());
                    }
                });
    }

    @Override
    public List<Parrainage> getParrainagesByParrain(Long parrainId) {
        return parrainageRepository.findByParrainId(parrainId);
    }

    @Override
    public Parrainage getParrainageByFilleul(Long filleulId) {
        return parrainageRepository.findByFilleulId(filleulId)
                .orElse(null);
    }

    @Override
    public boolean peutParrainer(Long parrainId, Long filleulId) {
        // Vérifier que le parrain et le filleul sont différents
        if (parrainId.equals(filleulId)) {
            return false;
        }

        // Vérifier que les deux sont des loueurs
        if (!loueurRepository.existsById(parrainId) || !loueurRepository.existsById(filleulId)) {
            return false;
        }

        // Vérifier que le filleul n'a pas déjà un parrain
        if (parrainageRepository.countByFilleulId(filleulId) > 0) {
            return false;
        }

        // Vérifier que le parrain n'a pas déjà parrainé ce filleul
        if (parrainageRepository.countByParrainIdAndFilleulId(parrainId, filleulId) > 0) {
            return false;
        }

        return true;
    }

    @Override
    public int getNombreFilleuls(Long parrainId) {
        return (int) parrainageRepository.countByParrainId(parrainId);
    }

    @Override
    public int getNombreParrainagesValides(Long parrainId) {
        return (int) parrainageRepository.countByParrainIdAndStatut(parrainId, StatutParrainage.VALIDE);
    }

    @Override
    public double getTotalCreditsGagnes(Long parrainId) {
        List<Parrainage> parrainagesValides = parrainageRepository.findByParrainIdAndStatut(
                parrainId, StatutParrainage.VALIDE);
        return parrainagesValides.stream()
                .mapToDouble(Parrainage::getMontantCredit)
                .sum();
    }

    @Override
    public Map<String, Object> getStatistiquesParrainage(Long parrainId) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("nombreFilleuls", getNombreFilleuls(parrainId));
        stats.put("nombreParrainagesValides", getNombreParrainagesValides(parrainId));
        stats.put("nombreParrainagesEnAttente", 
                (int) parrainageRepository.countByParrainIdAndStatut(parrainId, StatutParrainage.EN_ATTENTE));
        stats.put("totalCreditsGagnes", getTotalCreditsGagnes(parrainId));
        stats.put("soldePorteMonnaie", porteMonnaieService.getSolde(parrainId));
        return stats;
    }

    @Override
    public Parrainage findParrainageEnAttenteByFilleul(Long filleulId) {
        return parrainageRepository.findParrainageEnAttenteByFilleul(filleulId)
                .orElse(null);
    }

    @Override
    @Transactional
    public void annulerParrainage(Long parrainageId) {
        Parrainage parrainage = parrainageRepository.findById(parrainageId)
                .orElseThrow(() -> new IllegalArgumentException("Parrainage introuvable"));

        if (parrainage.isCreditAttribue()) {
            throw new IllegalStateException("Impossible d'annuler un parrainage dont le crédit a déjà été attribué");
        }

        parrainage.annuler();
        parrainageRepository.save(parrainage);
    }
}
