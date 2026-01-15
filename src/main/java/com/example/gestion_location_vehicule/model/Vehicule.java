package com.example.gestion_location_vehicule.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(
        name = "DTYPE",
        discriminatorType = DiscriminatorType.STRING,
        length = 20
)
public class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicule_seq")
    @SequenceGenerator(name = "vehicule_seq", sequenceName = "VEHICULE_SEQ", allocationSize = 1)
    private Long id;

    private String marque;
    private String modele;
    private String couleur;
    private double notevehicule;

    @Column(nullable = false)
    private Boolean vehiculedispo;

    @Temporal(TemporalType.DATE)
    private Date datedispo;

    private String villedispo;
    private double kilometrage;
    private double prixjour;


    @ManyToOne
    @JoinColumn(name = "agent_id")
    @ToString.Exclude
    @JsonIgnore
    private Agent agent;

    @OneToMany(mappedBy = "vehicule")
    @ToString.Exclude
    @JsonIgnore
    private List<EvalV> evalrecues;

    @OneToMany(mappedBy = "vehicule")
    @ToString.Exclude
    @JsonIgnore
    private List<PrixAssurance> prixassurance;

    @OneToMany(mappedBy = "vehicule")
    @ToString.Exclude
    @JsonIgnore
    private List<Contratlocation> contratlocations;

    @OneToMany(mappedBy = "vehicule")
    @JsonIgnore
    private List<DisponibiliteVehicule> disponibilites = new ArrayList<>(); // <-- relation vers disponibilités

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    private List<ControleTechnique> controlesTechniques;

    public String getTypeVehicule() {
        return this.getClass().getSimpleName();
    }

    public List<ControleTechnique> getControlesTechniques() {
        return this.controlesTechniques;
    }

    public Boolean getDispopardates(LocalDate dateDebut, LocalDate dateFin) {

        if (!this.vehiculedispo)
            return false;

        if(this.disponibilites==null)
        {
            return false;
        }

        if(this.disponibilites.size()==0)
            return false;


        for (DisponibiliteVehicule dispo : this.disponibilites) {

            boolean debutOK =
                    !dateDebut.isBefore(dispo.getDateDebut());
            // dateDebut >= dispo.dateDebut

            boolean finOK =
                    !dateFin.isAfter(dispo.getDateFin());
            // dateFin <= dispo.dateFin

            if (debutOK && finOK) {
                return true;
            }
        }
        return false;
    }

}