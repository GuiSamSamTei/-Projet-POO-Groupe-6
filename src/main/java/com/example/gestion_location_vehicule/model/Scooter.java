package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;

@Entity
public class Scooter extends Vehicule {

    private int cylindree;

    private boolean electrique;
}
