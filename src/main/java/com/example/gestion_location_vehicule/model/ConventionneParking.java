package com.example.gestion_location_vehicule.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConventionneParking {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conv_seq"
    )
    @SequenceGenerator(
            name = "conv_seq",
            sequenceName = "CONV_SEQ",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;
}
