package com.example.gestion_location_vehicule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
    private String ville;

    @OneToMany(mappedBy = "loueur")
    private List<EvalA> evaldonnees;

    @OneToMany(mappedBy = "loueur")
    private List<EvalV> evalvehicules;

    @OneToMany(mappedBy = "loueur")
    private List<EvalL> evalrecues;

    @JsonIgnore
    @OneToMany(mappedBy = "loueur")
    private List<Contratlocation> contratlocations;
}
