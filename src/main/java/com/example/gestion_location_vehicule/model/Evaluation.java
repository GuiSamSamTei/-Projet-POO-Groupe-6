package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Evaluation {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "evaluation_seq"
    )
    @SequenceGenerator(
            name = "evaluation_seq_seq",
            sequenceName = "EVALUATION_SEQ",
            allocationSize = 1
    )
    private Long id;

    private double note;
    private LocalDate datenote;
    private String commentaire;


}
