package com.example.gestion_location_vehicule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PorteMonnaie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "porte_monnaie_seq")
    @SequenceGenerator(name = "porte_monnaie_seq", sequenceName = "PORTE_MONNAIE_SEQ", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "utilisateur_id", unique = true, nullable = false)
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private double solde = 0.0; // Solde en euros

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    @Column(nullable = false)
    private LocalDateTime dateDerniereModification;

    @OneToMany(mappedBy = "porteMonnaie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<TransactionPorteMonnaie> transactions = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        dateDerniereModification = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateDerniereModification = LocalDateTime.now();
    }

    /**
     * Crédite le porte-monnaie
     */
    public void crediter(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à créditer doit être positif");
        }
        this.solde += montant;
    }

    /**
     * Débite le porte-monnaie
     */
    public void debiter(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à débiter doit être positif");
        }
        if (this.solde < montant) {
            throw new IllegalStateException("Solde insuffisant");
        }
        this.solde -= montant;
    }

    /**
     * Vérifie si le solde est suffisant
     */
    public boolean peutPayer(double montant) {
        return this.solde >= montant;
    }
}
