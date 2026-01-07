package com.example.gestion_location_vehicule.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
public class Loueur extends Utilisateur{
    private String datenaissance;
    private String numeropermis;
    private String dateexpirationpermis;
    private String pieceidentite;
    private String nom;
    private String prenom;


    @OneToMany(mappedBy = "loueur")
    private List<EvalA> evaldonnes;

}
