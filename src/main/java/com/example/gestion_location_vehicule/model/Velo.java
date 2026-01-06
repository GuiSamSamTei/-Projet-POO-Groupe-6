package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;

@Entity
public class Velo extends Vehicule {

    private boolean electrique;
    private int nombreVitesses;
    private String typeVelo;
}
