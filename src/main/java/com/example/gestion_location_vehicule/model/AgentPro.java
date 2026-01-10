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
@DiscriminatorValue("AgentPro")
public class AgentPro extends Agent{
    private String raisonsociale;
    private String siret;

}
