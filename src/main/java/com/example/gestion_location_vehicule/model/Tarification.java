package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarification {

    @Id
    private Long id;

    private double prixfixe;       // ex : 2€ par jour

    private double pourcentage;    // ex : 10% de commission

    private long annee;
}
