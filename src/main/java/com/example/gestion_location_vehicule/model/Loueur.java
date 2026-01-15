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
    private List<EvalA> evaldonnees;

    @OneToMany(mappedBy = "loueur")
    private List<EvalV> evalvehicules;

    @OneToMany(mappedBy = "loueur")
    private List<EvalL> evalrecues;

    @JsonIgnore
    @OneToMany(mappedBy = "loueur")
    private List<Contratlocation> contratlocations;

    // Parrainages où ce loueur est le parrain
    @OneToMany(mappedBy = "parrain", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Parrainage> parrainages;

    // Parrainage où ce loueur est le filleul (max 1)
    @OneToOne(mappedBy = "filleul")
    @JsonIgnore
    private Parrainage parrainageRecu;
}
