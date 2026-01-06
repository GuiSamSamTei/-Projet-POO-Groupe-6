package com.example.gestion_location_vehicule.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loueur extends Utilisateur{
    private String dateNaissance;
    private String numeroPermis;
    private String dateExpirationPermis;
    private String pieceIdentite;
    private String nom;
    private String prenom;

}
