package com.example.gestion_location_vehicule.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parking_seq"
    )
    @SequenceGenerator(
            name = "parking_seq",
            sequenceName = "PARKING_SEQ",
            allocationSize = 1
    )
    private Long id;
    private String nomparking;
    private String adresse;
    private String ville;
    private String codepostal;
    private Integer capacitemax;
    private Integer capacitenow;


    @OneToMany(mappedBy = "parking")
    private List<ConventionneParking> conventionneParkingList;


    public Boolean canPark()
    {
        return this.capacitenow<=capacitemax;
    }


}
