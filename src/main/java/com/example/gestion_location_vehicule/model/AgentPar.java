package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AgentPar extends Agent{
    private String nom;
    private String prenom;
}
