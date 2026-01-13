package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DISPONIBILITE_VEHICULE")
public class DisponibiliteVehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disp_seq")
    @SequenceGenerator(name = "disp_seq", sequenceName = "DISP_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @Column(name = "disponible", nullable = false)
    private Boolean disponible = true; // true = véhicule disponible

    @Column(name = "description", length = 255)
    private String description; // champ texte libre pour le formulaire
}
