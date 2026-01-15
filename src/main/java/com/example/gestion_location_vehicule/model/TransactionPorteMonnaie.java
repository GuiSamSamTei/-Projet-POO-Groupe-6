package com.example.gestion_location_vehicule.model;

import com.example.gestion_location_vehicule.enums.TypeTransaction;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionPorteMonnaie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_pm_seq")
    @SequenceGenerator(name = "transaction_pm_seq", sequenceName = "TRANSACTION_PM_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "porte_monnaie_id", nullable = false)
    private PorteMonnaie porteMonnaie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TypeTransaction type;

    @Column(nullable = false)
    private double montant; // Positif pour crédit, négatif pour débit

    @Column(nullable = false)
    private double soldeAvant;

    @Column(nullable = false)
    private double soldeApres;

    @Column(nullable = false)
    private LocalDateTime dateTransaction;

    @Column(length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "parrainage_id")
    private Parrainage parrainage; // Si transaction liée à un parrainage

    @ManyToOne
    @JoinColumn(name = "contrat_location_id")
    private Contratlocation contratLocation; // Si transaction liée à une location

    @PrePersist
    protected void onCreate() {
        if (dateTransaction == null) {
            dateTransaction = LocalDateTime.now();
        }
    }
}
