package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assurance {

    @Id
    private Long id;

    @Column(nullable = false)
    private String nom; // Par Défaut, Basique, Premium

    @Column(name = "ASSURANCE_PAR_DEFAUT")
    private boolean assuranceParDefaut;

    @Column(nullable = false)
    private boolean active; // Si l'assurance est active sur la plateforme

    @Column(nullable = false)
    private double prixFixe; // Prix fixe par jour

    @Column(nullable = false)
    private double pourcentage; // Pourcentage sur le prix du véhicule
}
