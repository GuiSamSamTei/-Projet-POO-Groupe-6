package com.example.gestion_location_vehicule.model;

public class Agent extends Utilisateur {

    private String adreese;
    private String telephonePro;
    // vehicule avoir
    private List<Vehicule> vehicules;
    // noter
    private List<Evaluation> evaluationsRecues;
    // obtenir l'argent
    private String iban;
    private String bic;
    private List<OptionPayante> optionsActives;
    //statistic
    private int nombreVehicules;
    private double revenusTotaux;


}
