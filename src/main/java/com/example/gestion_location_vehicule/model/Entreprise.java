package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private boolean active;
}
