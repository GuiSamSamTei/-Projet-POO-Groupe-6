package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ENTRETIEN_TECHNIQUE")
public class EntretienTechnique {

    @Id
    private Long id;  // ID fourni manuellement

    @Column(name = "type_entretien", length = 255)
    private String typeEntretien;

    @Column(name = "date_entretien")
    private LocalDate dateEntretien;

    @Column(length = 255)
    private String commentaire;

    @ManyToOne
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;
}
