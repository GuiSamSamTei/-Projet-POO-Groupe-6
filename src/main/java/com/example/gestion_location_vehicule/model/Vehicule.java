package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Vehicule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;

    private String modele;

    private String couleur;

    private int notevehicule;

    private boolean vehiculedispo;

    private Date datedispo;

    private String villeDispo;

    private double kilometrage;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @OneToMany(mappedBy = "vehicule")
    private List<PrixAssurance> prixassurance;
}
