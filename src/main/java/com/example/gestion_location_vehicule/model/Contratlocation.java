package com.example.gestion_location_vehicule.model;


import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data // Remplace Getter, Setter, RequiredArgsConstructor, etc.
@NoArgsConstructor
@AllArgsConstructor
public class Contratlocation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contratlocation_seq")
    @SequenceGenerator(name = "contratlocation_seq", sequenceName = "CONTRATLOCATION_SEQ", allocationSize = 1)
    private Long id;

    private LocalDate datedebut;
    private LocalDate datefin;
    private String lieudepot;

    // --- Amélioration des prix ---
    private Double prixLocationJour; // Le prix du véhicule par jour au moment de la loc
    private Double prixAssuranceApplique; // Le coût calculé de l'assurance pour ce contrat
    private Double fraisServiceApplique; // Les frais de service au moment de la loc
    private Double prixtotal; // (prixLocationJour * jours) + prixAssuranceApplique + fraisServiceApplique

    private Integer nombreJours; // Stocker la durée calculée

    @ManyToOne
    @JoinColumn(name = "vehicule_id", nullable = false) // On ajoute nullable = false pour la sécurité
    private Vehicule vehicule;

    @ManyToOne
    @JoinColumn(name = "assurance_id")
    private Assurance assurance;

    @ManyToOne
    @JoinColumn(name = "loueur_id", nullable = false)
    private Loueur loueur;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @Column(name = "montant_paye_porte_monnaie")
    private Double montantPayePorteMonnaie = 0.0; // Montant payé avec le porte-monnaie

    @Column(name = "montant_paye_autre")
    private Double montantPayeAutre = 0.0; // Montant payé par autre moyen (CB, etc.)

}
