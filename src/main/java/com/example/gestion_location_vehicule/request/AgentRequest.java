package com.example.gestion_location_vehicule.request;


import lombok.Data;

@Data
public class AgentRequest extends UtilisateurRequest{

    private String adreese;
    private String telephonePro;
    // obtenir l'argent
    private String iban;
    private String bic;
}
