package com.example.gestion_location_vehicule.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("AgentPar")
public class AgentPar extends Agent{
    private String nom;
    private String prenom;
}
