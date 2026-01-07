package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;

@Entity
public class Voiture extends Vehicule {

    private int nombreportes;

    private int nombreplaces;

    private boolean automatique;

    private String carburant;

    private double coffrevolume;

    private int nbchevaux;

    private boolean gps;
}
