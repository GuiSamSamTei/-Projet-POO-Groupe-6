package com.example.gestion_location_vehicule.request;

import com.example.gestion_location_vehicule.model.Vehicule;
import lombok.Data;
import lombok.Getter;

@Data
@Getter

public class VoitureRequest extends Vehicule {
    private int nombreportes;

    private int nombreplaces;

    private Boolean automatique;

    private String carburant;

    private double coffrevolume;

    private int nbchevaux;

    private Boolean gps;
}
