package com.example.gestion_location_vehicule.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VanRequest extends VehiculeRequest {

    private int nombreplaces;
}
