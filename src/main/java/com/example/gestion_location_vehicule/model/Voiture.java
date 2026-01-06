package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;

@Entity
public class Voiture extends Vehicule {

    private int nombrePortes;

    private int nombrePlaces;

    private boolean automatique;

    private String carburant;

    private double coffreVolume;

    private int nbChevaux;

    private boolean gps;
}
