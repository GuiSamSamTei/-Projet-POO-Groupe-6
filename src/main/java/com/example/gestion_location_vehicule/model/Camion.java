package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;

@Entity
public class Camion extends Vehicule {

    private double chargeMax; // kg
    private double longueur;   // m
    private double volume;     // m3

}
