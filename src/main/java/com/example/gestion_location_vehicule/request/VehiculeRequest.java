package com.example.gestion_location_vehicule.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VehiculeRequest {


    private String marque;

    private String modele;

    private String couleur;

    @JsonProperty("agentId")
    private Long agent_id;

    private double notevehicule;

    private Boolean vehiculedispo;

    private Date datedispo;

    private String villedispo;

    private double kilometrage;

    private double prixjour;

}
