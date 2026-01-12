package com.example.gestion_location_vehicule.request;

import java.util.Date;

import lombok.Data;

@Data
public class VehiculeRequest {


    private String marque;

    private String modele;

    private String couleur;

    private Long agent_id;

    private double notevehicule;

    private Boolean vehiculedispo;

    private Date datedispo;

    private String villedispo;

    private double kilometrage;

    private double prixjour;

}
