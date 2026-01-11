package com.example.gestion_location_vehicule.request;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

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
