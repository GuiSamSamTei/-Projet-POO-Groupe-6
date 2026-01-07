package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "utilisateursend_id")
    private Utilisateur utilisateursend;

    @ManyToOne
    @JoinColumn(name = "utilisateurreceive_id")
    private Utilisateur utilisateurreceive;

}
