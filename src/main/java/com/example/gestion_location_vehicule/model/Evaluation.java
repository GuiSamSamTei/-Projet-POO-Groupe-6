package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.Getter;

@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_evaluation")
@Getter
public abstract class Evaluation {
    @Id
    @GeneratedValue
    private Long id;

    private double noteFinale;
}
