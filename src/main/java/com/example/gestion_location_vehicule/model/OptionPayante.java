package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionPayante {

    @Id
    private Long id;

    private String nom;

    private String description;

    private double prixmensuel;

    @Column(nullable = false)
    private boolean active;
}
