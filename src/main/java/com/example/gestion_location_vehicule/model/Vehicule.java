package com.example.gestion_location_vehicule.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    @JsonIgnoreProperties({"vehicules", "evalrecues", "contratlocations"})
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
    private List<DisponibiliteVehicule> disponibilites; // <-- relation vers disponibilités

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL)
    private List<ControleTechnique> controlesTechniques;

    public String getTypeVehicule() {
        return this.getClass().getSimpleName();
    }

    public List<ControleTechnique> getControlesTechniques() {
        return this.controlesTechniques;
    }
}
