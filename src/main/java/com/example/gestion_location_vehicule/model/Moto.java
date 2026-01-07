package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moto extends Vehicule {

    private int cylindree;

    private int nbchevaux;

}
