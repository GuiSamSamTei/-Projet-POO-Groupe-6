package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends Utilisateur {


    private String adresse;
    private String telephonepro;
    // obtenir l'argent
    private String iban;
    private String bic;
    //private List<OptionPayante> optionsActives;
    //statistic
    private int nombrevehicules;
    private double revenustotaux;

    @OneToMany(mappedBy = "agent")
    private List<Vehicule> vehicules;

    @OneToMany(mappedBy = "agent")
    private List<EvalA> evalrecues;

    @OneToMany(mappedBy = "agent")
    private List<EvalL> evaldonnees;

    @OneToMany(mappedBy = "agent")
    private List<ConventionneParking> conventionneParkingList;
}
