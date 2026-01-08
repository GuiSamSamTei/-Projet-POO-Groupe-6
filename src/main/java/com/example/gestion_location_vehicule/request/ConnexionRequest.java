package com.example.gestion_location_vehicule.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ConnexionRequest {

    private String username;
    private String password;
}
