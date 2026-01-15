package com.example.gestion_location_vehicule.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
