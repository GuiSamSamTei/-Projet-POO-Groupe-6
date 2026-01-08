package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicule {


    @Id
    private Long id;

    private String marque;

    private String modele;

    private String couleur;

    private double notevehicule;

    private boolean vehiculedispo;

    private Date datedispo;

    private String villedispo;

    private double kilometrage;

    private double prixjour;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @OneToMany(mappedBy = "vehicule")
    private List<EvalV> evalrecues;

    @OneToMany(mappedBy = "vehicule")
    private List<PrixAssurance> prixassurance;

    @OneToMany(mappedBy = "vehicule")
    private List<Contratlocation> contratlocations;
}
