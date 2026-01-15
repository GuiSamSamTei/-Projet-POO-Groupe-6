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
@NoArgsConstructor
@AllArgsConstructor
public class EvalV extends Evaluation{

    @ManyToOne
    @JoinColumn(name="loueur_id")
    private Loueur loueur;

    @ManyToOne
    @JoinColumn(name="vehicule_id")
    private Vehicule vehicule;

    @OneToMany(mappedBy = "evalV")
    private List<Contratlocation> contratlocations;
}
