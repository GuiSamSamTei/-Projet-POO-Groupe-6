package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Assurance {

    @Id
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
