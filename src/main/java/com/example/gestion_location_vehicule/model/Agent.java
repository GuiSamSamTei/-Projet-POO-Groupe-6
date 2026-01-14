package com.example.gestion_location_vehicule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
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
    private Integer nombrevehicules;
    private Double revenustotaux;

    @OneToMany(mappedBy = "agent")
    @ToString.Exclude
    @JsonIgnore
    private List<Vehicule> vehicules;

    @OneToMany(mappedBy = "agent")
    @ToString.Exclude
    @JsonIgnore
    private List<EvalA> evalrecues;

    @OneToMany(mappedBy = "agent")
    @ToString.Exclude
    @JsonIgnore
    private List<EvalL> evaldonnees;

    @OneToMany(mappedBy = "agent")
    @JsonIgnore
    private List<ConventionneParking> conventionneParkingList;


    @JsonIgnore
    public List<Long> getParkingConvIDs()
    {
        List<Long> parkings = new ArrayList<>();

        for(ConventionneParking con : this.conventionneParkingList)
        {
            parkings.add(con.getParking().getId());
        }


        return parkings;
    }
    @JsonIgnore
    public List<Parking> getParkingConv()
    {
        List<Parking> parkings = new ArrayList<>();

        for(ConventionneParking con : this.conventionneParkingList)
        {
            parkings.add(con.getParking());
        }


        return parkings;
    }

    public List<ControleTechnique> getAllControlesTechniques()
    {
        List<ControleTechnique> controles = new ArrayList<>();

        for(Vehicule v : this.vehicules)
        {
            controles.addAll(v.getControlesTechniques());
        }

        return controles;
    }
}
