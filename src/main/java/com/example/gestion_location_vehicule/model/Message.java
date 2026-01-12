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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    @SequenceGenerator(
        name = "message_seq",
        sequenceName = "MESSAGE_SEQ",
        allocationSize = 1
    )
    private Long id;
    private String contenu;
    private Date dateenvoi;

    @Column(nullable = false)
    private boolean lu;

    @ManyToOne
    @JoinColumn(name = "utilisateursend_id")
    private Utilisateur utilisateursend;

    @ManyToOne
    @JoinColumn(name = "utilisateurreceive_id")
    private Utilisateur utilisateurreceive;

}
