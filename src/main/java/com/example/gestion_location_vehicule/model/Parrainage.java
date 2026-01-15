package com.example.gestion_location_vehicule.model;

import com.example.gestion_location_vehicule.enums.StatutParrainage;
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
public class Parrainage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parrainage_seq")
    @SequenceGenerator(name = "parrainage_seq", sequenceName = "PARRAINAGE_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parrain_id", nullable = false)
    private Loueur parrain;

    @ManyToOne
    @JoinColumn(name = "filleul_id", nullable = false)
    private Loueur filleul;

    @Column(nullable = false)
    private LocalDateTime dateParrainage;

    @Column(nullable = false)
    private boolean creditAttribue = false; // Le parrain a-t-il reçu son crédit ?

    @Column
    private LocalDateTime dateCreditAttribue;

    @Column(nullable = false)
    private double montantCredit = 20.0; // Montant du crédit de parrainage (configurable)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatutParrainage statut = StatutParrainage.EN_ATTENTE;

    @OneToMany(mappedBy = "parrainage", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionPorteMonnaie> transactions = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (dateParrainage == null) {
            dateParrainage = LocalDateTime.now();
        }
        if (statut == null) {
            statut = StatutParrainage.EN_ATTENTE;
        }
    }

    /**
     * Valide le parrainage et marque le crédit comme attribué
     */
    public void valider() {
        this.statut = StatutParrainage.VALIDE;
        this.creditAttribue = true;
        this.dateCreditAttribue = LocalDateTime.now();
    }

    /**
     * Annule le parrainage
     */
    public void annuler() {
        this.statut = StatutParrainage.ANNULE;
    }

    /**
     * Vérifie si le parrainage est en attente
     */
    public boolean estEnAttente() {
        return this.statut == StatutParrainage.EN_ATTENTE;
    }

    /**
     * Vérifie si le parrainage est validé
     */
    public boolean estValide() {
        return this.statut == StatutParrainage.VALIDE;
    }
}
