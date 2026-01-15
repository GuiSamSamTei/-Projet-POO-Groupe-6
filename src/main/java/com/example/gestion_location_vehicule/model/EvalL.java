package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvalL extends Evaluation{

    @ManyToOne
    @JoinColumn(name="loueur_id")
    private Loueur loueur;

    @ManyToOne
    @JoinColumn(name="agent_id")
    private Agent agent;


    @OneToMany(mappedBy = "evalL")
    private List<Contratlocation> contratlocations;
}
