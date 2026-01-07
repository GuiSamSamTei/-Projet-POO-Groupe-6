package com.example.gestion_location_vehicule.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Van extends Vehicule {

    private int nombreplaces;

}
