package com.example.gestion_location_vehicule.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Message {
    private String contenu;
    private Date date;
    private boolean lu;
    private Utilisateur utilisateursend;
    private Utilisateur utilisateurreceive;

}
