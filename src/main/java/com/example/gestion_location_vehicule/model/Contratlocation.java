package com.example.gestion_location_vehicule.model;


import java.time.LocalDate;

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
public class Contratlocation {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contratlocation_seq"
    )
    @SequenceGenerator(
            name = "contratlocation_seq",
            sequenceName = "CONTRATLOCATION_SEQ",
            allocationSize = 1
    )
    private Long id;
    private LocalDate datedebut;
    private LocalDate  datefin;
    private String lieudepot;
    //les attributs liées au classes

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "assurance_id")
    private Assurance assurance;

    @ManyToOne
    @JoinColumn(name = "loueur_id")
    private Loueur loueur;





}
