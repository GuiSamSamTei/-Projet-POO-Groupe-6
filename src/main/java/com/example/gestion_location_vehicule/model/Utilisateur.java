package com.example.gestion_location_vehicule.model;


import jakarta.persistence.*;
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
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username; //nom pour se connecter
    private String mdp;
    private String email;
    private String telephone;

    // information général pour noter
    private double notemoyenne; // recevoir
    private int nombreevaluations; // recevoir

    //messages
    @OneToMany(mappedBy = "utilisateur")
    private List<Message> messagerecus;

    @OneToMany(mappedBy = "utilisateur")
    private List<Message> messagesend;



}
