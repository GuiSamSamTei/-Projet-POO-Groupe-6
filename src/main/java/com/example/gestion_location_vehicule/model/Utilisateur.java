package com.example.gestion_location_vehicule.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(unique = true, nullable = false)
    private String username; //nom pour se connecter

    @Column(nullable = false)
    private String mdp;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String telephone;
    private String ville;

    // information général pour noter
    private double notemoyenne = 0.0; // recevoir
    private int nombreevaluations = 0; // recevoir

    //messages
    @OneToMany(mappedBy = "utilisateurreceive")
    private List<Message> messagerecus;

    // Messages envoyés → l'utilisateur est l'expéditeur
    @OneToMany(mappedBy = "utilisateursend")
    private List<Message> messagesenvoyes;

    private boolean isAdmin = false;


}
