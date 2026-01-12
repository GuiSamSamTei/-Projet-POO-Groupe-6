package com.example.gestion_location_vehicule.request;


import lombok.Data;
import lombok.Getter;

@Data
@Getter

public class CamionRequest extends VehiculeRequest {
    private double chargemax;
    private double volume;
}