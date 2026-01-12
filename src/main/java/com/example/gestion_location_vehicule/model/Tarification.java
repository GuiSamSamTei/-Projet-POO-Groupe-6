package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tarification {

    @Id
    private Long id;

    private double prixfixe;       // ex : 2€ par jour

    private double pourcentage;  // ex : 10% de commission

    private long annee;
}
