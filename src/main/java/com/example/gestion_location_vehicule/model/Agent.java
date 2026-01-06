package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends Utilisateur {


    private String adreese;
    private String telephonePro;
    // vehicule avoir
    private List<Vehicule> vehicules;
    // noter
    private List<Evaluation> evaluationsRecues;
    // obtenir l'argent
    private String iban;
    private String bic;
    private List<OptionPayante> optionsActives;
    //statistic
    private int nombreVehicules;
    private double revenusTotaux;


}
