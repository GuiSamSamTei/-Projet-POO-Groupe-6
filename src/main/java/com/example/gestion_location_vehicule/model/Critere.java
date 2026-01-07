package com.example.gestion_location_vehicule.model;

import com.example.gestion_location_vehicule.enums.TypeCritere;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Critere {

    @Id
    private Long id;

    private String nom;
    private String description;
    private TypeCritere type;

}
