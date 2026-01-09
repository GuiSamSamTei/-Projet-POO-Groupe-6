package com.example.gestion_location_vehicule.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Utilisateur {
    @Id
    private Long id;
    private String username; //nom pour se connecter
    private String mdp;
    private String email;
    private String telephone;

    // information général pour noter
    private double notemoyenne; // recevoir
    private int nombreevaluations; // recevoir

    //messages
    @OneToMany(mappedBy = "utilisateurreceive")
    private List<Message> messagerecus;

    // Messages envoyés → l'utilisateur est l'expéditeur
    @OneToMany(mappedBy = "utilisateursend")
    private List<Message> messagesenvoyes;


}
