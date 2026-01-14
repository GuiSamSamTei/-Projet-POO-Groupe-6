package com.example.gestion_location_vehicule.service.CalculPrixService;

import com.example.gestion_location_vehicule.model.Assurance;
import com.example.gestion_location_vehicule.model.Tarification;
import com.example.gestion_location_vehicule.model.Vehicule;
import com.example.gestion_location_vehicule.service.TarificationService.ITarificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CalculPrixService implements ICalculPrixService {

    private final ITarificationService tarificationService;

    @Override
    public double calculerPrixGlobal(Vehicule vehicule, Assurance assurance, LocalDate dateDebut, LocalDate dateFin) {
        long jours = ChronoUnit.DAYS.between(dateDebut, dateFin) + 1;
        if (jours <= 0) jours = 1;

        double prixVehicule = vehicule.getPrixjour() * jours;
        double prixAssurance = getPrixAssurance(vehicule, assurance) * jours;

        // Tarification plateforme
        int annee = dateDebut.getYear();
        Tarification tarification = tarificationService.getByAnnee(annee).orElse(null);
        double prixFixe = tarification != null ? tarification.getPrixfixe() * jours : 0;
        double pourcentage = tarification != null ? tarification.getPourcentage() : 0;

        double commission = prixVehicule * (pourcentage / 100.0) + prixFixe;

        return prixVehicule + prixAssurance + commission;
    }

    @Override
    public double getPrixAssurance(Vehicule vehicule, Assurance assurance) {
        if (assurance == null) return 0;
        return assurance.getPrixFixe() + vehicule.getPrixjour() * (assurance.getPourcentage() / 100.0);
    }
}
