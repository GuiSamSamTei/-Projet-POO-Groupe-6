package com.example.gestion_location_vehicule.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@Getter
public class VanRequest extends VehiculeRequest {

    private int nombreplaces;
}
