package com.example.gestion_location_vehicule.request;


import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MotoRequest extends VehiculeRequest {
    private Integer cylindree;
    private Integer nbchevaux;
}
