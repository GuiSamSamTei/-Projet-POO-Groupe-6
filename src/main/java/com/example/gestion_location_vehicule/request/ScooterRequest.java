package com.example.gestion_location_vehicule.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ScooterRequest extends VehiculeRequest{
    private Integer cylindree;
    private Boolean electrique;
}
