package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Message {


    @Id
    private Long id;
    private String contenu;
    private Date dateenvoi;
    private boolean lu;

    @ManyToOne
    @JoinColumn(name = "utilisateursend_id")
    private Utilisateur utilisateursend;

    @ManyToOne
    @JoinColumn(name = "utilisateurreceive_id")
    private Utilisateur utilisateurreceive;

}
