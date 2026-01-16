package com.example.gestion_location_vehicule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Loueur")
public class Loueur extends Utilisateur {

    private String nom;
    private String prenom;
    private String typepermis;

    @OneToMany(mappedBy = "loueur")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<EvalA> evaldonnees;

    @OneToMany(mappedBy = "loueur")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<EvalV> evalvehicules;

    @OneToMany(mappedBy = "loueur")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<EvalL> evalrecues;

    @JsonIgnore
    @OneToMany(mappedBy = "loueur")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Contratlocation> contratlocations;

    // Parrainages où ce loueur est le parrain
    @OneToMany(mappedBy = "parrain", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Parrainage> parrainages;

    // Parrainage où ce loueur est le filleul
    @OneToOne(mappedBy = "filleul")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Parrainage parrainageRecu;
}
