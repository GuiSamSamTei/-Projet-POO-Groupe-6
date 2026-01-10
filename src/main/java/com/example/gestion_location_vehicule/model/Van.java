package com.example.gestion_location_vehicule.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("Van")
public class Van extends Vehicule {

    private int nombreplaces;

}
