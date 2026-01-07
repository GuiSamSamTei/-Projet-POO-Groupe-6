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
@AllArgsConstructor
@NoArgsConstructor
public class EvalA extends Evaluation {

    @ManyToOne
    @JoinColumn(name="loueur_id")
    private Loueur loueur;

    @ManyToOne
    @JoinColumn(name="agent_id")
    private Agent agent;

    private double note;
    private Date date;
}
