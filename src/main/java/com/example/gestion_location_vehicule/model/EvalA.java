package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

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


}
