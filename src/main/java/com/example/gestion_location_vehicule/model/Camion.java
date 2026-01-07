package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Camion extends Vehicule {

    private double chargemax; // kg
    private double longueur;   // m
    private double volume;     // m3

}
