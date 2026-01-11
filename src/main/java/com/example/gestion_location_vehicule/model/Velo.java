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
@DiscriminatorValue("Velo")
public class Velo extends Vehicule {

    private boolean electrique;
    private int nombreVitesses;
    private String typeVelo;
}
