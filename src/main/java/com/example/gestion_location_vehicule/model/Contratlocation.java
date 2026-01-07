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
public class Contratlocation {
    @Id
    private Long id;
    private Date datedebut;
    private Date datefin;
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
