package com.example.gestion_location_vehicule.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "controle_technique_seq")
    @SequenceGenerator(
        name = "controle_technique_seq",
        sequenceName = "SEQ_CONTROLE_TECHNIQUE",
        allocationSize = 1
    )
    private Long id;

    @Column(name="notifie", nullable = false)
    private boolean notifie = false;

    @Column(name = "date_controle", nullable = false)
    private LocalDate dateControle;

    @Column(name = "date_expiration", nullable = false)
    private LocalDate dateExpiration;

    @Column(nullable = false)
    private boolean valide;

    @Column(length = 255)
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;

    // Méthode pour vérifier si le contrôle technique va expirer dans un certain nombre de jours
    public boolean isExpiringSoon(int days) {
        LocalDate now = LocalDate.now();
        LocalDate thresholdDate = now.plusDays(days);
        return dateExpiration.isBefore(thresholdDate) || dateExpiration.isEqual(thresholdDate);
    }
}
