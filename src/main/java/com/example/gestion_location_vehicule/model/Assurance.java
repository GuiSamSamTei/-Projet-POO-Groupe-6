package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private long prix;

    private boolean assurancepardefaut;     //assurance AZA

    private boolean active;     //si l'assurance n'a plus de contrat avec la plateforme ou avec un agent


    @OneToMany(mappedBy = "assurance")
    private List<PrixAssurance> prixassurance;

    @OneToMany(mappedBy = "assurance")
    private List<Contratlocation> contratlocations;
}
