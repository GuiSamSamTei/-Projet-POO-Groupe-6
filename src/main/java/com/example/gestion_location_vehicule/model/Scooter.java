package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Scooter extends Vehicule {

    private int cylindree;

    @Column(nullable = false)
    private boolean electrique;
}
