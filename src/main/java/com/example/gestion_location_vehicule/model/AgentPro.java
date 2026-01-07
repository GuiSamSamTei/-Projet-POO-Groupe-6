package com.example.gestion_location_vehicule.model;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentPro extends Agent{
    private String raisonsociale;
    private String siret;

}
