package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise {

    @Id

    private String nsiret;

    private String raisonSoc;

    private String ville;

    private String adresse;

    private String telephone;

    private String email;

    private boolean active;
}
