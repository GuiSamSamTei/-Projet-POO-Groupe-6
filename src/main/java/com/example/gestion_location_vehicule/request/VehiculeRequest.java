package com.example.gestion_location_vehicule.request;

import lombok.Data;

@Data
public class VehiculeRequest {


    private String marque;

    private String modele;

    private String couleur;

    private Long agent_id;

}
