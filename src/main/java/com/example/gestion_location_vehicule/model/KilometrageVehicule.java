package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KILOMETRAGE_VEHICULE")
public class KilometrageVehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "km_seq")
    @SequenceGenerator(name = "km_seq", sequenceName = "KM_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "km_depart")
    private int kmDepart;

    @Column(name = "km_retour")
    private int kmRetour;

    @Column(name = "date_location")
    private LocalDate dateLocation;

    @Column(name = "photo_depart", length = 255)
    private String photoDepart;

    @Column(name = "photo_retour", length = 255)
    private String photoRetour;

    // ⚡ Lien vers le véhicule
    @ManyToOne
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;
}
