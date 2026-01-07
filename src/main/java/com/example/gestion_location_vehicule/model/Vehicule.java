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
public abstract class Vehicule {


    @Id
    private Long id;

    private String marque;

    private String modele;

    private String couleur;

    private int notevehicule;

    private boolean vehiculeDispo;

    private Date datedispo;

    private String villeDispo;

    private double kilometrage;

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
