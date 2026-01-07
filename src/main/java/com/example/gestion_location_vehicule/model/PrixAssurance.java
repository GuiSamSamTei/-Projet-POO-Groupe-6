package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrixAssurance {

    @Id
    private Long id;

    private double prix;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "assurance_id")
    private Assurance assurance;
}
