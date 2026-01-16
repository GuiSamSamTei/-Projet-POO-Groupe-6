package com.example.gestion_location_vehicule.service.ParrainageService;

import com.example.gestion_location_vehicule.model.Loueur;
import com.example.gestion_location_vehicule.model.Parrainage;

import java.util.List;
import java.util.Map;

public interface IParrainageService {
    

    Parrainage creerParrainage(Long parrainId, Long filleulId);
    

    Parrainage creerParrainageParEmail(Long parrainId, String emailFilleul);

    Parrainage creerParrainageParUsername(Long parrainId, String usernameFilleul);

    void validerParrainage(Long parrainageId);
    

    void checkEtValiderPremiereLocation(Long filleulId);

    List<Parrainage> getParrainagesByParrain(Long parrainId);

    Parrainage getParrainageByFilleul(Long filleulId);

    boolean peutParrainer(Long parrainId, Long filleulId);
    

    int getNombreFilleuls(Long parrainId);

    int getNombreParrainagesValides(Long parrainId);

    double getTotalCreditsGagnes(Long parrainId);

    Map<String, Object> getStatistiquesParrainage(Long parrainId);

    Parrainage findParrainageEnAttenteByFilleul(Long filleulId);

    void annulerParrainage(Long parrainageId);
}
