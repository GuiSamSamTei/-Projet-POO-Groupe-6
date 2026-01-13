package com.example.gestion_location_vehicule.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CONTROLE_TECHNIQUE")
public class ControleTechnique {

    @Id
    private Long id;   // ID fourni manuellement

    @Column(name="notifie", nullable = false)
    private boolean notifie;

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
