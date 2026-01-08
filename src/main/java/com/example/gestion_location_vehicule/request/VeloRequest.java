package com.example.gestion_location_vehicule.request;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class VeloRequest extends VehiculeRequest{

    private Boolean electrique;
    private int nombrevitesses;
    private String typeVelo;
}
