package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Facture {

    @Id
    private Long id;

    private double montant;

    private LocalDate dateFacture;

    @Column(nullable = false)
    private boolean payee;
}
