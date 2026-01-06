package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
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

    private int noteVehicule;

    private boolean vehiculeDispo;

    private Date dateDispo;

    private String villeDispo;

    private double kilometrage;

}
