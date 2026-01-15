package com.example.gestion_location_vehicule.service.ParrainageService;

import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Parrainage;

import java.util.List;
import java.util.Map;

public interface IParrainageService {
    
    /**
     * Crée un parrainage entre un parrain et un filleul
     */
    Parrainage creerParrainage(Long parrainId, Long filleulId);
    
    /**
     * Crée un parrainage par email du filleul
     */
    Parrainage creerParrainageParEmail(Long parrainId, String emailFilleul);
    
    /**
     * Valide un parrainage et attribue le crédit au parrain
     * Appelé automatiquement après la première location du filleul
     */
    void validerParrainage(Long parrainageId);
    
    /**
     * Vérifie et valide le parrainage après la première location d'un filleul
     */
    void checkEtValiderPremiereLocation(Long filleulId);
    
    /**
     * Récupère tous les parrainages d'un parrain
     */
    List<Parrainage> getParrainagesByParrain(Long parrainId);
    
    /**
     * Récupère le parrainage d'un filleul (s'il existe)
     */
    Parrainage getParrainageByFilleul(Long filleulId);
    
    /**
     * Vérifie si un parrain peut parrainer un filleul
     */
    boolean peutParrainer(Long parrainId, Long filleulId);
    
    /**
     * Compte le nombre de filleuls d'un parrain
     */
    int getNombreFilleuls(Long parrainId);
    
    /**
     * Compte le nombre de parrainages validés d'un parrain
     */
    int getNombreParrainagesValides(Long parrainId);
    
    /**
     * Calcule le total des crédits gagnés par parrainage
     */
    double getTotalCreditsGagnes(Long parrainId);
    
    /**
     * Récupère les statistiques de parrainage d'un parrain
     */
    Map<String, Object> getStatistiquesParrainage(Long parrainId);
    
    /**
     * Trouve le parrainage en attente pour un filleul donné
     */
    Parrainage findParrainageEnAttenteByFilleul(Long filleulId);

    /**
     * Annule un parrainage
     */
    void annulerParrainage(Long parrainageId);
}
