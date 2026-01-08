package com.example.gestion_location_vehicule.request;


import lombok.Data;

@Data
public class UtilisateurRequest {

    private String username; //nom pour se connecter
    private String mdp;
    private String email;
    private String telephone;

}
