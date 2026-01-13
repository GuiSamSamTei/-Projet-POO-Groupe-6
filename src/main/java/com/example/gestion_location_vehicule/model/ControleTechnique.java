package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONTROLE_TECHNIQUE")
public class ControleTechnique {

    @Id
    private Long id;   // ID fourni manuellement

    @Column(name = "date_controle", nullable = false)
    private LocalDate dateControle;

    @Column(name = "date_expiration", nullable = false)
    private LocalDate dateExpiration;

    @Column(nullable = false)
    private boolean valide;

    @Column(length = 255)
    private String commentaire;

    @OneToOne
    @JoinColumn(name = "vehicule_id", nullable = false, unique = true)
    private Vehicule vehicule;
}
