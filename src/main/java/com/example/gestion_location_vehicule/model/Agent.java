package com.example.gestion_location_vehicule.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Agent extends Utilisateur {


    private String adreese;
    private String telephonePro;
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
}
