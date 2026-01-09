package com.example.gestion_location_vehicule.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(
        name = "DTYPE",
        discriminatorType = DiscriminatorType.STRING,
        length = 20
)
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "utilisateur_seq"
    )
    @SequenceGenerator(
            name = "utilisateur_seq",
            sequenceName = "UTILISATEUR_SEQ",
            allocationSize = 1
    )
    private Long id;
    private String username; //nom pour se connecter
    private String mdp;
    private String email;
    private String telephone;

    // information général pour noter
    private double notemoyenne = 0.0; // recevoir
    private int nombreevaluations = 0; // recevoir

    //messages
    @OneToMany(mappedBy = "utilisateurreceive")
    private List<Message> messagerecus;

    // Messages envoyés → l'utilisateur est l'expéditeur
    @OneToMany(mappedBy = "utilisateursend")
    private List<Message> messagesenvoyes;


}
