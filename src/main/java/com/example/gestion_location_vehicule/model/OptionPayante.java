package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
