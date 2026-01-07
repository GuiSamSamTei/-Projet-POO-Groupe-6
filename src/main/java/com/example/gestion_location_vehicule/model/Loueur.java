package com.example.gestion_location_vehicule.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loueur extends Utilisateur{
    private String datenaissance;
    private String numeropermis;
    private String dateexpirationpermis;
    private String pieceidentite;
    private String nom;
    private String prenom;


    @OneToMany(mappedBy = "loueur")
    private List<EvalA> evaldonnees;

    @OneToMany(mappedBy = "loueur")
    private List<EvalV> evalvehicules;

    @OneToMany(mappedBy = "loueur")
    private List<EvalL> evalrecues;

    @OneToMany(mappedBy = "loueur")
    private List<Contratlocation> contratlocations;

}
