package com.example.gestion_location_vehicule.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Voiture")
public class Voiture extends Vehicule {

    private int nombreportes;

    private int nombreplaces;

    private Boolean automatique;

    private String carburant;

    private double coffrevolume;

    private int nbchevaux;

    private Boolean gps;
}
