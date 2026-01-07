package com.example.gestion_location_vehicule.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Evaluation {

    @Id
    private Long id;

    private double note;
    private Date datenote;
}
